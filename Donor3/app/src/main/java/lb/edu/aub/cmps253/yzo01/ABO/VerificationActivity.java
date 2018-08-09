package lb.edu.aub.cmps253.yzo01.ABO;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerificationActivity extends AppCompatActivity {
    private static final String TAG = "VerificationActivity";

    @Bind(R.id.verificatioCodeInput)EditText verificatioCodeInput;
    @Bind(R.id.btn_verify)Button btn_verify;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificatioCodeInput.getText().toString().length() != 6)
                    verificatioCodeInput.setError("Verification code consists of 6 digits");
                else {
                    btn_verify.setEnabled(false);
                    new Verify(getIntent().getStringExtra("Phone"), verificatioCodeInput.getText().toString()).execute();
                }
            }
        });
    }

    private class Verify extends AsyncTask<Void,Void,String> {
        String phoneNumber;
        String verificationCode;
        ProgressDialog progressDialog;

        public Verify(String phoneNumber, String verificationCde){
            this.phoneNumber = phoneNumber;
            this.verificationCode = verificationCde;
            progressDialog = new ProgressDialog(VerificationActivity.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Verifying");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String customToken = FirebaseClient.getInstance().verify(phoneNumber, verificationCode);

            return customToken;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result == null){
                Toast.makeText(VerificationActivity.this, "customToken is null!", Toast.LENGTH_SHORT).show();
                btn_verify.setEnabled(true);
                progressDialog.dismiss();
            }
            else {
                FirebaseClient.getInstance().getFirebaseAuth().signInWithCustomToken(result).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(VerificationActivity.this, (task.isSuccessful()) ? "Successful" : "NOT Successful", Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        }
                        else {
                            btn_verify.setEnabled(true);
                        }
                        progressDialog.dismiss();
                    }
                });
            }

        }
    }
}