package lb.edu.aub.cmps253.yzo01.ABO;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DonationHistoryActivity extends AppCompatActivity {

    @Bind(R.id.historyext) TextView historyOfText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        FirebaseClient.getInstance().DBGetOnce("DonationHistory/" + FirebaseClient.getInstance().getUID(), new DBCustomCodeRunner() {
            @Override
            public void run(DataSnapshot dsp) {
                String result = "";
                if(!dsp.hasChildren()){
                    result = "You have not made any Donations yet!";
                }
                else{
                    for(DataSnapshot dsp1: dsp.getChildren()) {
                        result += dsp1.getValue() + ": " + dsp1.getKey() + "\n";
                    }
                }
                historyOfText.setText(result);
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
