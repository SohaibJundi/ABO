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

public class DonationTipsActivity extends AppCompatActivity{
    private static final String TAG = "DonationTipsActivity";

    @Bind(R.id.whocandonate_btn) Button whoCanDonateButton;
    @Bind(R.id.beforedonate_btn) Button beforeDonateButton;
    @Bind(R.id.duringdonation_btn) Button duringDonationButton;
    @Bind(R.id.afterdonating_btn) Button afterDonationButton;
    @Bind(R.id.healthstatus_btn) Button healthStatusButton;
    @Bind(R.id.myths_btn) Button mythsButton;
    @Bind(R.id.stories_btn) Button storiesButton;
    @Bind(R.id.faq_btn) Button faqButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationtips);
        ButterKnife.bind(this);

        whoCanDonateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                  tipsText(whoCanDonateButton);
            }
        });

        beforeDonateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tipsText(beforeDonateButton);
            }
        });

        duringDonationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tipsText(duringDonationButton);
            }
        });

        afterDonationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tipsText(afterDonationButton);
            }
        });

        healthStatusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tipsText(healthStatusButton);
            }
        });

        mythsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tipsText(mythsButton);
            }
        });

        storiesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tipsText(storiesButton);
            }
        });

        faqButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tipsText(faqButton);
            }
        });
    }


    public void tipsText(Button tipButton){
        Intent intent = new Intent(getApplicationContext(), TipsTextActivity.class);
        intent.putExtra("Tip",tipButton.getText().toString());
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
