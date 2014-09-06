package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;

public class AboutUsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

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

        ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
