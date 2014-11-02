package com.pga.project1.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pga.project1.DataModel.Personnel;
import com.pga.project1.R;
import com.pga.project1.Viewes.ViewNameValue;

public class FastAddPersonnelToWork extends Activity {
    Personnel personnel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_add_personnel_to_work);
        //

        personnel = (Personnel) getIntent().getSerializableExtra("personnel");

        ViewNameValue personnel_name = (ViewNameValue) findViewById(R.id.txt_fragmentTaskInfo_personnelName);
        ViewNameValue personnel_phone = (ViewNameValue) findViewById(R.id.txt_fragmentTaskInfo_personnelPhone);


        personnel_name.setNameValue("نام و نام خانوادگی", personnel.getFullName());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fast_add_personnel_to_work, menu);
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
    }
}
