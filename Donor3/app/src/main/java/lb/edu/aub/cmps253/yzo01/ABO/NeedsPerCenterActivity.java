package lb.edu.aub.cmps253.yzo01.ABO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NeedsPerCenterActivity extends AppCompatActivity {
    private static final String TAG = "NeedsPerCenterActivity";

    @Bind(R.id.donatenow_button) Button donateNowButton;
    @Bind(R.id.centernameandadresstext) TextView centerNameandAdress;
    Map<String, String> centerAdressMap=new HashMap<String, String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needspercenter);
        ButterKnife.bind(this);

        populateMap();
        String centerName="default";
        if(getIntent().hasExtra("centerName")) {
             centerName= getIntent().getStringExtra("centerName");
        }

        String adress=(String)centerAdressMap.get(centerName);
        centerNameandAdress.setText(centerName + "\n "+adress+"\n\nBlood Types Needed");
        Log.e(TAG, "centerName in needs per activity is"+ centerName);

        donateNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RequestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void populateMap(){
        centerAdressMap.put("AUBMC-Lebanon","Cairo Street, Hamra, Beirut, Lebanon");
        centerAdressMap.put("Ghandour Hospital","Sabbah Street, Nabatieh, Lebanon");
        centerAdressMap.put("Clemenceau Center","Clemenceau Street, Beirut, Lebanon");
        centerAdressMap.put("Hotel Die","Achrafieh, Beirut, Lebanon");
        centerAdressMap.put("Al Najda Hospital","Nabatieh, Lebanon");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(Utils.onOptionsItemSelected(this,getApplicationContext(),id))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
