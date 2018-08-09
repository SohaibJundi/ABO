package lb.edu.aub.cmps253.yzo01.ABO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;

public class Utils {
    private static String lang = "ar";

    public static String getCurrnetLang()
    {
        return lang;
    }

    public static boolean   onOptionsItemSelected(Activity activity, Context context, int id)
    {
        if (id == R.id.action_settings) {
            return true;
        }

        Intent intent = new Intent(context,HomeActivity.class);
        if(id==R.id.item1){
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        else if(id==R.id.item2){
            intent = new Intent(context,ProfileActivity.class);
        }
        else if (id==R.id.item3){
            final String[] bloodTypes = {"O+","O-","A+","A-","B+","B-","AB+","AB-"};
            for(int i = 0; i < bloodTypes.length; i++){
                FirebaseMessaging.getInstance().unsubscribeFromTopic(bloodTypes[i].replace("+", "P").replace("-", "M"));
            }
            FirebaseMessaging.getInstance().unsubscribeFromTopic(FirebaseClient.getInstance().getUID());
            FirebaseClient.getInstance().logout();
            intent = new Intent(context,PhoneEntryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        return false;
    }

    public static void updateResources(Activity activity, String language, Class c) {
        lang = language;
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config, activity.getResources().getDisplayMetrics());

        activity.startActivity(new Intent(activity.getApplicationContext(), c));
        activity.finish();
    }
}
