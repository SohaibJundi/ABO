package lb.edu.aub.cmps253.yzo01.ABO;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AfterRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_request);
        TextView afterRequestText = (TextView) findViewById(R.id.afterRequestText);
        String recipientName = getIntent().getStringExtra("Name");
        afterRequestText.setText(getString(R.string.afterRequest_TextView).replace("(|||)", recipientName));
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(AfterRequestActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        super.onDestroy();
    }
}
