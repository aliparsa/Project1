package com.pga.project1.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pga.project1.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Context context = this;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                finish();

            }
        }, 1200);


    }


}
