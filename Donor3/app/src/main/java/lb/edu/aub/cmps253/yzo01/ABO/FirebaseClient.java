package lb.edu.aub.cmps253.yzo01.ABO;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirebaseClient {

    private static FirebaseClient fbc;

    private DatabaseReference mDBRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String url = "https://customauthdonor3.herokuapp.com/token/";

    private FirebaseClient()
    {
        mDBRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Auth Status", "Signed in");
                }
                else {
                    Log.d("Auth Status", "Signed out");
                }
            }
        };
    }

    public static FirebaseClient getInstance()
    {
        if(fbc == null)
            fbc = new FirebaseClient();

        return fbc;
    }

    public boolean isAuthenticated()
    {
        return mAuth.getCurrentUser() != null;
    }

    public DatabaseReference getDatabaseReference()
    {
        return mDBRef;
    }

    public FirebaseAuth getFirebaseAuth()
    {
        return mAuth;
    }

    public void DBGetOnce(String path, final DBCustomCodeRunner dbccr)
    {
        mDBRef.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dbccr.run(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void DBGetRealTime(String path, final DBCustomCodeRunner dbccr)
    {
        mDBRef.child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dbccr.run(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.print(databaseError.getMessage());
            }
        });
    }

    public void DBSetValue(String path, Object value)
    {
        mDBRef.child(path).setValue(value);
    }

    private static String callOnNetwork ( String url ) throws IOException {
        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String registerPhoneNumber(String phoneNumber)
    {
        try {
            return callOnNetwork(url+phoneNumber);
        } catch (IOException e) {
            return e.toString();
        }
    }

    public String verify(String phoneNumber, String verificationCode)
    {
        try {
            return callOnNetwork(url+phoneNumber+"/"+verificationCode);
        } catch (IOException e) {
            return null;
        }
    }

    public void logout()
    {
        mAuth.signOut();
    }

    public String getUID()
    {
        if(mAuth.getCurrentUser() != null)
            return mAuth.getCurrentUser().getUid();

        return null;
    }

    public void sendNotification(final String to, final String message, final String title){
        Thread threadN = new Thread(new Runnable() {
            @Override
            public void run() {
                //
                MediaType JSON = MediaType.parse("application/json");
                //

                String urlString = "https://fcm.googleapis.com/fcm/send";
                JSONObject jsonObjects = new JSONObject();
                JSONObject notif = new JSONObject();
                try {
                    notif.put("title", title);
                    notif.put("body", message);
                    jsonObjects.put("notification",notif);

                    jsonObjects.put("to","/topics/" + to);
                    //JUST TO TEST
                    //jsonObjects.put("to","/topics/BeirutSMinus");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Response", jsonObjects.toString());
                RequestBody body = RequestBody.create(JSON, jsonObjects.toString());
                Request req = new Request.Builder()
                        .url(urlString)
                        .post(body)
                        .addHeader("Authorization","key=AAAA9el20So:APA91bH0Q9Z9sITyuHpJFAq8rqXP2HL2Cc6S5vls67tsRDB47nmZeLyHQKzDqrjCW8JByzJfvHAIAnqY69ZKDUQcClbkMYJh_IFH0CnUeqC7Gy7e0Kj4ljZk9g1ARlG8kTlF6yJaL0WS")
                        .build();
                try {
                    Response res = new OkHttpClient().newCall(req).execute();
                    Log.d("Response", res.body().string());
                } catch (IOException e) {
                    sendNotification(to, message, title);
                }
            }

        });
        threadN.start();

    }
}