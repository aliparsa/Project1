package com.pga.project1.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.pga.project1.Activities.LoginActivity;
import com.pga.project1.Intefaces.CallBackFunction;

/**
 * Created by aliparsa on 9/2/2014.
 */
public class HandleError {

    public static void HandleError(Context context, String err, final CallBackFunction callback) {

        // toast error
        // Toast.makeText(context, err, Toast.LENGTH_SHORT).show();

        //---------------------------------------
        if (err.equals("network error")) {
            new AlertDialog.Builder(context)
                    .setTitle("عدم دسترسی به سامانه")
                    .setCancelable(false)
                    .setMessage("لطفا تنظیمات ارتباطی تلفن همراه خود را بررسی نمایید")
                    .setPositiveButton("تلاش مجدد", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    callback.execute();
                                }
                            }
                    )
                    .show();
        }
        //---------------------------------------
        if (err.equals("UNAUTHORIZED")) {

            // clear token
            Account.getInstant(context).clearToken();

            // pass user to login page
            Intent intent = new Intent(context, LoginActivity.class);
            intent.putExtra("reason", "UNAUTHORIZED");
            context.startActivity(intent);
        }
    }
}
