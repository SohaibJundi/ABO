package lb.edu.aub.cmps253.yzo01.ABO;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RequestActivity extends AppCompatActivity {

    @Bind(R.id.centers_spinner) Spinner centersSpinner; //where can you donate
    @Bind(R.id.bloodtype_spinner) Spinner bloodtypeSpin;
    @Bind(R.id.units_number_spinner) Spinner unitsNumberSpin;
    @Bind(R.id.bookdonation) Button bookDonationButton;
    @Bind(R.id.recipient_name_EditText) EditText recipientNameEditText;
    @Bind(R.id.platelets_only_checkBox) CheckBox plateletsOnlyCheckBox;

    List<String> centerList= new ArrayList<String>();
    List<String> bloodList= new ArrayList<String>();
    List<String> unitList= new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        ButterKnife.bind(this);

        addItemsOnSpinners();

        bookDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == DialogInterface.BUTTON_POSITIVE) {
                            FirebaseClient.getInstance().DBSetValue("bloodRequests/" + FirebaseClient.getInstance().getUID() + "/neededBloodType", bloodtypeSpin.getSelectedItem().toString());
                            FirebaseClient.getInstance().DBSetValue("bloodRequests/" + FirebaseClient.getInstance().getUID() + "/unitsNumber", unitsNumberSpin.getSelectedItem().toString());
                            FirebaseClient.getInstance().DBSetValue("bloodRequests/" + FirebaseClient.getInstance().getUID() + "/center", centersSpinner.getSelectedItem().toString());
                            FirebaseClient.getInstance().DBSetValue("bloodRequests/" + FirebaseClient.getInstance().getUID() + "/recipient", recipientNameEditText.getText().toString());
                            FirebaseClient.getInstance().DBSetValue("bloodRequests/" + FirebaseClient.getInstance().getUID() + "/plateletsOnly", plateletsOnlyCheckBox.isChecked() ? "Y" : "N");
                            FirebaseClient.getInstance().sendNotification(bloodtypeSpin.getSelectedItem().toString().replace("+", "P").replace("-", "M"), recipientNameEditText.getText().toString() + " needs " + unitsNumberSpin.getSelectedItem().toString() + " unit/s of " + bloodtypeSpin.getSelectedItem().toString() + " at " + centersSpinner.getSelectedItem().toString() + (plateletsOnlyCheckBox.isChecked() ? "\nPlateles Only" : ""), "Request");
                            Toast.makeText(RequestActivity.this, "Request have been sent", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RequestActivity.this, AfterRequestActivity.class);
                            intent.putExtra("Name", recipientNameEditText.getText().toString());
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                };
                new AlertDialog.Builder(new ContextThemeWrapper(RequestActivity.this, R.style.AlertDialogCustom)).setMessage("Do you want to confirm the request?\nNote that any previous request will be overwritten").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).setTitle("Request!").show();

            }
        });
    }

    public void addItemsOnSpinners() {              //get places from google and sort in order
        //places List
        centerList.add("Abou Jaoude Hospital S.A.L --- Metn/Baabda");
        centerList.add("Ain Wazein Hospital --- Chouf/Aley");
        centerList.add("Akkar Rahal --- North Lebanon");
        centerList.add("Al Bissar Hospital --- North Lebanon");
        centerList.add("Al Mortada hospital --- Bekaa");
        centerList.add("Al Salam(La Paix) --- North Lebanon");
        centerList.add("Al Zahraa University Hospital --- Beirut");
        centerList.add("Alaeddine hospital --- South Lebanon");
        centerList.add("AL-Hanan --- North Lebanon");
        centerList.add("Al-Hayat hospital --- Metn/Baabda");
        centerList.add("Al-Janoub hospital (Shuayb) --- South Lebanon");
        centerList.add("Al-Rassoul Al-Aazam hospital --- Beirut");
        centerList.add("American University of Beirut Medical Center --- Beirut");
        centerList.add("Armenian Sanatorium of Azounieh --- Chouf/Aley");
        centerList.add("Arz hospital --- Metn/Baabda");
        centerList.add("Baaklyn --- Chouf/Aley");
        centerList.add("Bahmad --- Bekaa");
        centerList.add("Bahman hospital --- Beirut");
        centerList.add("Battoul --- Bekaa");
        centerList.add("Beirut --- Beirut");
        centerList.add("Beirut Eye Specialty Hospital --- Beirut");
        centerList.add("Beit Chabab --- Metn/Baabda");
        centerList.add("Bekhaazi --- Beirut");
        centerList.add("Bhannes --- Metn/Baabda");
        centerList.add("Bitar --- Metn/Baabda");
        centerList.add("Borgi --- North Lebanon");
        centerList.add("Bourj hospital --- Beirut");
        centerList.add("Bshamoun Specialty Hospital --- Chouf/Aley");
        centerList.add("Centre Hospitalier du Nord --- North Lebanon");
        centerList.add("Centre Hospitalier Universitaire - Notre Dame de Secours --- Kesrouan/Jbeil");
        centerList.add("Child & Mother Welfare Hospital --- Beirut");
        centerList.add("Chtoura Hospital --- Bekaa");
        centerList.add("Clemenceau Medical Center affiliated with Johns Hopkins International - CMC --- Beirut");
        centerList.add("Clinique Dr. Rizk --- Beirut");
        centerList.add("Dalla'a General Hospital --- South Lebanon");
        centerList.add("Dar Al-Ajaza Al-Islamia Hospital --- Beirut");
        centerList.add("Dar Al-Amal University Hospital --- Bekaa");
        centerList.add("Dar El Chifaa --- North Lebanon");
        centerList.add("Dar El Hikmah --- Bekaa");
        centerList.add("Dar El Rahmeh --- Metn/Baabda");
        centerList.add("Dar El Sahel --- Beirut");
        centerList.add("Dar Sami Kodsi --- Metn/Baabda");
        centerList.add("Doctors --- Bekaa");
        centerList.add("Dr. Hamed Farhat hospital --- Bekaa");
        centerList.add("Dr. Youssef Rahban Hospital s.a.r.l. --- North Lebanon");
        centerList.add("El Amal Institute for Disabeled --- Metn/Baabda");
        centerList.add("El Arz --- Metn/Baabda");
        centerList.add("El Assi --- Bekaa");
        centerList.add("El Bekaa --- Bekaa");
        centerList.add("El Kheir --- North Lebanon");
        centerList.add("El Ouyoun (Lebanese Ophth. Hosp.) --- Chouf/Aley");
        centerList.add("El Youssef Hospital Center --- North Lebanon");
        centerList.add("Eye & Ear Hospital International --- Metn/Baabda");
        centerList.add("Fanar --- South Lebanon");
        centerList.add("Farhat .F c --- Bekaa");
        centerList.add("Fouad Khoury Hospital --- Beirut");
        centerList.add("Foyer Saint Georges --- Beirut");
        centerList.add("Ghandour hospital --- South Lebanon");
        centerList.add("Haddad hospital for the Rosary Sisters --- Beirut");
        centerList.add("Hajj --- Kesrouan/Jbeil");
        centerList.add("Hamlin --- Chouf/Aley");
        centerList.add("Hammoud --- South Lebanon");
        centerList.add("Haroun --- Metn/Baabda");
        centerList.add("Hiram hospital --- South Lebanon");
        centerList.add("Hopital Albert HAYKEL. S.A.L --- North Lebanon");
        centerList.add("HOPITAL AL-KOURA --- North Lebanon");
        centerList.add("Hopital Chahine --- North Lebanon");
        centerList.add("Hopital Dr S. Serhal --- Metn/Baabda");
        centerList.add("Hopital Dr. Adnan Haidar --- Beirut");
        centerList.add("Hopital Hayek --- Metn/Baabda");
        centerList.add("Hopital Libanais --- Beirut");
        centerList.add("Hopital Monseigneur Cortbawi --- Kesrouan/Jbeil");
        centerList.add("Hopital Notre Dame de la Paix --- North Lebanon");
        centerList.add("Hopital Notre Dame du Liban --- Kesrouan/Jbeil");
        centerList.add("Hopital Notre Dame Maritime --- Kesrouan/Jbeil");
        centerList.add("Hopital Saint Georges - Ajaltoun --- Kesrouan/Jbeil");
        centerList.add("Hopital Saydet Zgharta / HSZ --- North Lebanon");
        centerList.add("Hotel Dieu de France --- Beirut");
        centerList.add("Hopital des Soeurs de la Croix (Deir-El-Kamar) --- Chouf/Aley");
        centerList.add("Hopital Sainte Thأ©rأ¨se --- Metn/Baabda");
        centerList.add("Ibn Sina --- Bekaa");
        centerList.add("Iman --- Chouf/Aley");
        centerList.add("Irfane --- Chouf/Aley");
        centerList.add("Islamic Health Institution --- South Lebanon");
        centerList.add("Islamy --- North Lebanon");
        centerList.add("Jabal Amel --- South Lebanon");
        centerList.add("Jbeily --- South Lebanon");
        centerList.add("Kassab --- South Lebanon");
        centerList.add("Khalidy Hospital --- Beirut");
        centerList.add("Kharroubi Gen.Hospital --- South Lebanon");
        centerList.add("Khatib hospital --- Bekaa");
        centerList.add("Khoury General Hospital - Zahle - Doctors Center --- Bekaa");
        centerList.add("Labib Medical Center s.a.l. --- South Lebanon");
        centerList.add("Libano-Canadien --- Metn/Baabda");
        centerList.add("Libano-Francais --- Bekaa");
        centerList.add("Maarbes --- Metn/Baabda");
        centerList.add("Makassed --- Beirut");
        centerList.add("Medical 2000 Co. SARL Kamal Joumblat hopital --- Chouf/Aley");
        centerList.add("Middle East Institute of Health --- Metn/Baabda");
        centerList.add("Mounla hospital --- North Lebanon");
        centerList.add("Mount Lebanon Hospital --- Metn/Baabda");
        centerList.add("Najjar Hospital --- Beirut");
        centerList.add("najm --- South Lebanon");
        centerList.add("New Hospital Mazloum --- North Lebanon");
        centerList.add("Nini hospital s.a.l --- North Lebanon");
        centerList.add("Othman --- Chouf/Aley");
        centerList.add("Psychiatric Hospital of the Cross (Jal El Dib) --- Metn/Baabda");
        centerList.add("Ragheb Harb --- South Lebanon");
        centerList.add("Raii --- South Lebanon");
        centerList.add("Rayak Hospital --- Bekaa");
        centerList.add("Rayan --- Bekaa");
        centerList.add("Sacre Coeur --- Metn/Baabda");
        centerList.add("Sahel General Hospital --- Beirut");
        centerList.add("Saideh --- Metn/Baabda");
        centerList.add("Saint George Hospital University Medical Center --- Beirut");
        centerList.add("Secours Populaire Libanais-Nabatieh --- South Lebanon");
        centerList.add("Social Services --- North Lebanon");
        centerList.add("St Antoine (Souhaid) --- Beirut");
        centerList.add("St Charles --- Metn/Baabda");
        centerList.add("St Joseph Hospital - Raymond & Aida Najjar Medical Center --- Metn/Baabda");
        centerList.add("St Louis --- Kesrouan/Jbeil");
        centerList.add("St Michel --- Kesrouan/Jbeil");
        centerList.add("Taanayel Gen.Hosp. --- Bekaa");
        centerList.add("Tal Chiha --- Bekaa");
        centerList.add("Tatari hospital --- Bekaa");
        centerList.add("Trad Hospital & Medical Center --- Beirut");
        centerList.add("Universal --- Bekaa");
        centerList.add("Watani --- Chouf/Aley");
        centerList.add("Womens --- Beirut");



        //Blood List
        bloodList.add("A+");
        bloodList.add("A-");
        bloodList.add("B+");
        bloodList.add("B-");
        bloodList.add("O+");
        bloodList.add("O-");
        bloodList.add("AB+");
        bloodList.add("AB-");

        for (int i = 1; i < 11; i++){
            unitList.add(i+"");
        }

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, centerList);

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bloodList);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, unitList);

        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        centersSpinner.setAdapter(dataAdapter1);
        bloodtypeSpin.setAdapter(dataAdapter2);
        unitsNumberSpin.setAdapter(dataAdapter3);
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
