package school.cesar.eduardo.projetoandroid;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";
    private static MySharedPreference sInstance;
    private static Context mContext;
    private static final String PREFERENCE_NAME = "MINHAS_PREFERENCIAS";

    private MySharedPreference() {
    }

    public static MySharedPreference getInstance(Context context) {
        if (sInstance == null) {
            mContext = context;
            sInstance = new MySharedPreference();
        }
        return sInstance;
    }

    private static SharedPreferences myPreferences() {
        return mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }
    public void setUserName(String name) {
        myPreferences().edit().putString(USER_NAME, name).commit();
    }

    public String getUserName() {
        return myPreferences().getString("USER_NAME", "");
    }

    public void cleanUserName() {
        myPreferences().edit().remove("USER_NAME");
    }
    public void setPassword(String password) {
        myPreferences().edit().putString(PASSWORD, password).commit();
    }

    public String getPassword() {
        return myPreferences().getString("PASSWORD", "");
    }

    public void cleanPassword() {
        myPreferences().edit().remove("PASSWORD");
    }

}
