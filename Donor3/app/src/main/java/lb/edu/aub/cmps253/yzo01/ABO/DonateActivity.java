package lb.edu.aub.cmps253.yzo01.ABO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DonateActivity extends AppCompatActivity {

    @Bind(R.id.newdonationbutton) Button newdonationButton;
    @Bind(R.id.changedonationbutton) Button changeDonationButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        ButterKnife.bind(this);

        newdonationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gotoDonatioActivity("New");
            }
        });

        changeDonationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gotoDonatioActivity("Change");
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

    private void gotoDonatioActivity(String action){
        Intent intent = new Intent(getApplicationContext(),RequestActivity.class);
        intent.putExtra("NewOrChange", action);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}

