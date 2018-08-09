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

public class HomeActivity extends AppCompatActivity{
    private static final String TAG = "HomeActivity";

    @Bind(R.id.Request_button) Button requestButton;
    @Bind(R.id.View_Requests_button) Button viewRequestButton;
    @Bind(R.id.needs_button) Button needsButton;
    @Bind(R.id.tips_button) Button tipsButton;
    @Bind(R.id.lang_button) Button langButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        requestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RequestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        viewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewRequestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        tipsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DonationTipsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        needsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CurrentNeedsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        langButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(langButton.getText().toString().equals("العربية")) {
                    Utils.updateResources(HomeActivity.this, "ar", HomeActivity.class);
                }
                else if(langButton.getText().toString().equals("English")){
                    Utils.updateResources(HomeActivity.this, "en", HomeActivity.class);
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
        return item.getItemId() == R.id.item1 || Utils.onOptionsItemSelected(this,getApplicationContext(),item.getItemId()) || super.onOptionsItemSelected(item);
    }
}
