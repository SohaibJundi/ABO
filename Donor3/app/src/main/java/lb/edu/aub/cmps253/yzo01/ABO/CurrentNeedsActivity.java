package lb.edu.aub.cmps253.yzo01.ABO;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CurrentNeedsActivity extends AppCompatActivity {
    private static final String TAG = "CurrentNeedsActivity";

    @Bind(R.id.recycler_view) RecyclerView rv;
    List<Request> requestList = new ArrayList<>();
    private String userBloodType = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentneeds);
        ButterKnife.bind(this);

        FirebaseClient.getInstance().DBGetOnce("Users/" + FirebaseClient.getInstance().getUID(), new DBCustomCodeRunner() {
            @Override
            public void run(DataSnapshot dsp) {
                for (DataSnapshot dsp1: dsp.getChildren()) {
                    if(dsp1.getKey().equals("bloodType")){
                        userBloodType = dsp1.getValue().toString();
                        break;
                    }
                }
            }
        });

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_decorator)));
        FirebaseClient.getInstance().DBGetOnce("bloodRequests/", new DBCustomCodeRunner() {
            @Override
            public void run(DataSnapshot dsp) {
                CountryAdapter ca = new CountryAdapter(requestList);
                for (DataSnapshot dspChild: dsp.getChildren()){
                    //requests.setText(requests.getText() + dspChild.getKey());
                    String mobile = dspChild.getKey();
                    String center = "", neededBloodType = "", unitsNumber = "";
                    for(DataSnapshot values: dspChild.getChildren()){
                        //requests.setText( requests.getText() + " | " +values.getValue().toString());
                        if(values.getKey().toString().equals("center")){
                            center = values.getValue().toString();
                        }
                        else if(values.getKey().toString().equals("neededBloodType")){
                            neededBloodType = values.getValue().toString();
                        }
                        else if(values.getKey().toString().equals("unitsNumber")){
                            unitsNumber = values.getValue().toString() + " unit/s";
                        }
                    }

                    if(neededBloodType.equals(userBloodType)) {
                        requestList.add(new Request(mobile, center, neededBloodType, unitsNumber));
                    }
                    //requests.setText(requests.getText() + "\n\n");
                }
                rv.setAdapter(ca);
            }
        });

        rv.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), rv,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, final int position) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which == DialogInterface.BUTTON_POSITIVE) {
                                    Toast.makeText(CurrentNeedsActivity.this, "Donation have been sent", Toast.LENGTH_SHORT).show();
                                    FirebaseClient.getInstance().DBSetValue("bloodRequests/"+requestList.get(position).getMobile()+"/Donor/UID", FirebaseClient.getInstance().getUID());
                                    FirebaseClient.getInstance().sendNotification(requestList.get(position).getMobile(), "Somebody offered a donation", "Donation");
                                    Intent intent = new Intent(CurrentNeedsActivity.this, HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                        };
                        new AlertDialog.Builder(new ContextThemeWrapper(CurrentNeedsActivity.this, R.style.AlertDialogCustom)).setMessage("Are you sure you Want to donate?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).setTitle("Donate!").show();
                    }

                    public void onLongClickItem(View v, int position) {
                        onClickItem(v, position);
                    }
                }));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return Utils.onOptionsItemSelected(this,getApplicationContext(),item.getItemId()) || super.onOptionsItemSelected(item);
    }
}





