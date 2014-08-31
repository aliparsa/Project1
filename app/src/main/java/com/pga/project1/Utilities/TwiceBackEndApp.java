package com.pga.project1.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by aliparsa on 8/31/2014.
 */
public class TwiceBackEndApp {

    static boolean TwiceBackPressed = false;

    public static void twiceBackCheck(Activity activity) {
        if (TwiceBackPressed)
            activity.finish();
        else {
            if (!SharedPreference.contains("x1", activity)) {
                SharedPreference.putString("x1", "", activity);
                Toast.makeText(activity, "جهت خروج یکبار دیگر بزنید", Toast.LENGTH_SHORT).show();
            }
            TwiceBackPressed = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    TwiceBackPressed = false;
                }
            }, 200);
        }
    }
}
