package lb.edu.aub.cmps253.yzo01.ABO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(this, HomeActivity.class));

        Intent intent;
        if (FirebaseClient.getInstance().isAuthenticated()) {
            intent = new Intent(this, HomeActivity.class);
        }
        else {
            intent = new Intent(this, PhoneEntryActivity.class);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
