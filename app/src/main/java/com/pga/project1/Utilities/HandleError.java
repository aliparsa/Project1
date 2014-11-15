package com.pga.project1.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.widget.EditText;

import com.pga.project1.Activities.LoginActivity;
import com.pga.project1.Intefaces.CallBackFunction;
import com.pga.project1.R;

/**
 * Created by aliparsa on 9/2/2014.
 */
public class HandleError {

    public static void HandleError(final Context context, String err, final CallBackFunction callback) {

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
                    .setNegativeButton("تغییر سرور", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("آدرس سرور را وارد نمایید");

                                    final EditText input = new EditText(context);
                                    input.setText(Webservice.getSERVER_ADDRESS());
                                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                                    builder.setView(input);

                                    builder.setPositiveButton("ذخیره", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Webservice.modifyServerAddress(input.getText().toString(), context);
                                            callback.execute();
                                        }
                                    });
                                    builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                                    builder.show();
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
            ((Activity) context).overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
        }
    }
}
