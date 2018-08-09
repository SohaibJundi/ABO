package lb.edu.aub.cmps253.yzo01.ABO;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ProfileActivity extends AppCompatActivity{

    @Bind(R.id.input_nameprofile) EditText _nameText;
    @Bind(R.id.adressprofile) EditText _addressText;
    @Bind(R.id.input_mobileprofile) EditText _mobileText;
    @Bind(R.id.genderprofile) Spinner _gender;
    @Bind(R.id.bloodgroupprofile) Spinner _bloodgroup;
    @Bind(R.id.dobprofile) Button _dob;
    @Bind(R.id.btn_updateprofile) Button updtButoon;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        _mobileText.setEnabled(false);

        _gender.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[] {getString(R.string.profile_genderSpinner_Male),getString(R.string.profile_genderSpinner_Female)}));
        final String[] bloodTypes = {"O+","O-","A+","A-","B+","B-","AB+","AB-"};
        _bloodgroup.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bloodTypes));

        final String UID = FirebaseClient.getInstance().getFirebaseAuth().getCurrentUser().getUid();

        FirebaseClient.getInstance().DBGetOnce("Users/" + UID, new DBCustomCodeRunner() {
            @Override
            public void run(DataSnapshot dsp) {
                _mobileText.setText(UID);
                for (DataSnapshot dsp1: dsp.getChildren()) {
                    if(dsp1.getKey().equals("DOB"))
                        _dob.setText(dsp1.getValue(String.class));
//                    else if(dsp1.getKey().equals("bloodType"))
//                        _bloodgroup.
                    else if(dsp1.getKey().equals("fullName"))
                        _nameText.setText(dsp1.getValue(String.class));
//                    else if(dsp1.getKey().equals("gender"))
//                        _gender.setText(dsp1.getValue(String.class));
                    else if(dsp1.getKey().equals("livingPlace"))
                        _addressText.setText(dsp1.getValue(String.class));
                }
            }
        });

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        _dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                DatePickerDialog dd = new DatePickerDialog(ProfileActivity.this,R.style.AppTheme_Dark_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            java.util.Date date = formatter.parse(dateInString);
                            _dob.setText(formatter.format(date).replace("/", "-"));

                        }
                        catch (Exception ex) { }
                    }
                }, year, month, day);
                dd.show();
            }
        });

        updtButoon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name=_nameText.getText().toString();
                String adress=_addressText.getText().toString();
                String gender=_gender.getSelectedItem().toString();
                String bloodgroup=_bloodgroup.getSelectedItem().toString();
                String dob=_dob.getText().toString();

                if(valid()) {
                    FirebaseClient.getInstance().DBSetValue("Users/" + UID + "/DOB", dob);
                    FirebaseClient.getInstance().DBSetValue("Users/" + UID + "/bloodType", bloodgroup);
                    FirebaseClient.getInstance().DBSetValue("Users/" + UID + "/fullName", name);
                    FirebaseClient.getInstance().DBSetValue("Users/" + UID + "/gender", gender);
                    FirebaseClient.getInstance().DBSetValue("Users/" + UID + "/livingPlace", adress);

                    for(int i = 0; i < bloodTypes.length; i++){
                        FirebaseMessaging.getInstance().unsubscribeFromTopic(bloodTypes[i].replace("+", "P").replace("-", "M"));
                    }

                    FirebaseMessaging.getInstance().subscribeToTopic(UID);
                    FirebaseMessaging.getInstance().subscribeToTopic(bloodgroup.replace("+", "P").replace("-", "M"));
                    Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
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
        return item.getItemId()==R.id.item2 || !valid() || Utils.onOptionsItemSelected(this,getApplicationContext(),item.getItemId()) ||super.onOptionsItemSelected(item);
    }

    private boolean valid()
    {
        boolean valid = true;

        if(_nameText.getText().toString().length() < 3) {
            _nameText.setError("Name must be greater than 3 characters");
            valid = false;
        }
        if(_addressText.getText().toString().length() < 3) {
            _addressText.setError("Address must be greater than 3 characters");
            valid = false;
        }
        if(_dob.getText().toString().equals("Birth Day")) {
            _dob.setError("Please select date");
            valid = false;
        }

        return valid;
    }
}
