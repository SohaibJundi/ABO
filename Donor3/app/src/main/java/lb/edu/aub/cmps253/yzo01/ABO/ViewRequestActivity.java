package lb.edu.aub.cmps253.yzo01.ABO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewRequestActivity extends AppCompatActivity {
    @Bind(R.id.confirmtext) TextView confirmationText;
    @Bind(R.id.Cancel_button) Button cancel_button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        ButterKnife.bind(this);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseClient.getInstance().getDatabaseReference().child("bloodRequests/" + FirebaseClient.getInstance().getUID()).removeValue();

                Intent intent = new Intent(ViewRequestActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        FirebaseClient.getInstance().DBGetOnce("bloodRequests/" + FirebaseClient.getInstance().getUID(), new DBCustomCodeRunner() {
            @Override
            public void run(DataSnapshot dsp) {
                confirmationText.setText("");
                for(DataSnapshot dspchild: dsp.getChildren()){
                    if(!dspchild.getKey().equals("Donor")) {
                        confirmationText.setText(confirmationText.getText() + dspchild.getKey() + ": " + dspchild.getValue() + "\n");
                    }
                    else {
                        confirmationText.setText("Donor: " + dspchild.child("UID").getValue().toString() + "\n" + confirmationText.getText());
                        cancel_button.setText(getString(R.string.viewRequest_Confirm_donation_Button));
                    }
                }
                if(confirmationText.getText().equals("")){
                    confirmationText.setText(getString(R.string.viewRequest_No_Request_Label));
                }
                else{
                    cancel_button.setVisibility(View.VISIBLE);
                }
            }
        });
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
