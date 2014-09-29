package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;

public class AboutAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);


        prepareActionbar();
    }


    private void prepareActionbar() {

        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_back, null);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(customActionBar);

        ImageView icon = (ImageView) customActionBar.findViewById(R.id.ac_icon);
        TextView title = (TextView) customActionBar.findViewById(R.id.ac_title);
        FontHelper.SetFont(this, Fonts.MAIN_FONT, title, Typeface.BOLD);

        //ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);
        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                onBackPressed();

            }
        });

    }


}
