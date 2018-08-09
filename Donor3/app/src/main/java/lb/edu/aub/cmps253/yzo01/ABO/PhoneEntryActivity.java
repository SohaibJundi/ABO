package lb.edu.aub.cmps253.yzo01.ABO;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

public class PhoneEntryActivity extends AppCompatActivity {
    private static final String TAG = "PhoneEntryActivity";

    @Bind(R.id.phoneNumberInput)EditText phoneNumberInput;
    @Bind(R.id.btn_login)Button btn_login;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_entry);
        ButterKnife.bind(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phoneNumberInput.getText().toString().length() != 8)
                    phoneNumberInput.setError("Phone number must be 8 digits");
                else {
                    btn_login.setEnabled(false);
                    new Login(phoneNumberInput.getText().toString()).execute();
                }
            }
        });
    }

    private class Login extends AsyncTask<Void,Void,Boolean> {
        ProgressDialog progressDialog;
        String phoneNumber;

        public Login(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
            progressDialog = new ProgressDialog(PhoneEntryActivity.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return FirebaseClient.getInstance().registerPhoneNumber(phoneNumber).equals("");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Log.d(TAG,"onPostExecute");
            if(!result){
                Toast.makeText(PhoneEntryActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
                btn_login.setEnabled(true);
            }
            else {
                Intent intent = new Intent(getApplicationContext(),VerificationActivity.class);
                intent.putExtra("Phone",phoneNumber);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
            progressDialog.dismiss();
        }
    }
}
