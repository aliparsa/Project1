package com.pga.project1.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.pga.project1.Activities.LoginActivity;
import com.pga.project1.R;

/**
 * Created by parsa on 2014-12-20.
 */
public class ErrorHelper {

    public static void handelError(Context context, String errorMessage) {
        if (errorMessage.equals("UNAUTHORIZED")) {

            if (Account.getInstant(context).alreadyHaveToken()) {

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
}
