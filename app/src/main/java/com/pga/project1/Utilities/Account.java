package com.pga.project1.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by aliparsa on 8/10/2014.
 */
public class Account {


    static Account instant;
    String token;


    Context context;


    public static Account getInstant(Context context) {

        if (instant == null && context != null) {
            instant = new Account();
            instant.context = context;
        }

        return instant;

    }


    public Account() {

    }


    public void storeToken(String token) {
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = app_preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }


    public String getToken() {

        if (token != null || token.length() > 1)
            return token;

        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        token = app_preferences.getString("token", "");
        return token;

    }

    public boolean alreadyHaveToken() {
        SharedPreferences app_preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        String token;
        token = app_preferences.getString("token", null);

        if (token == null)
            return false;
        else
            return true;

    }
}
