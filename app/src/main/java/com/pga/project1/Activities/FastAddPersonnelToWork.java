package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.pga.project1.Adapters.MySpinnerAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Work;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.PersianCalendar;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;
import com.pga.project1.Viewes.ViewNameValue;

import java.util.ArrayList;

public class FastAddPersonnelToWork extends Activity {
    Personnel personnel;
    private Context context;
    private PersianCalendar selectedDateTime;
    private Button timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_add_personnel_to_work);
        //


        context = this;


        Webservice.getWorks(context, new CallBack<ArrayList<Work>>() {
            @Override
            public void onSuccess(ArrayList<Work> result) {

                if (result.size() > 0) {
                    DatabaseHelper db = new DatabaseHelper(context);
                    db.emptyWorkTable();
                    for (Work work : result) {
                        db.insertWork(work);
                    }

                }

            }

            @Override
            public void onError(String errorMessage) {

            }
        });


        personnel = (Personnel) getIntent().getSerializableExtra("personnel");

        ViewNameValue personnel_name = (ViewNameValue) findViewById(R.id.txt_fragmentTaskInfo_personnelName);
        ViewNameValue personnel_phone = (ViewNameValue) findViewById(R.id.txt_fragmentTaskInfo_personnelPhone);


        personnel_name.setNameValue("نام و نام خانوادگی", personnel.getFullName());


        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<Work> works = db.getAllWorks();
        final Spinner mySpinner;
        mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(new MySpinnerAdapter(this, works));
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> aParentView,
                                       View aView, int aPosition, long anId) {
                if (aPosition == 0) {
                    Log.d(getClass().getName(), "Ignoring selection of dummy list item...");
                } else {
                    Log.d(getClass().getName(), "Handling selection of actual list item...");
                    // TODO: insert code to handle selection
                    //resetSelection(mySpinner);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> anAdapterView) {
                // do nothing
            }
        });
        EditText mizanKar = (EditText) findViewById(R.id.editText_MizanKar);

        timePicker = (Button) findViewById(R.id.timePicker);
        timePicker.setText(new PersianCalendar().getIranianDateTime());

        selectedDateTime = new PersianCalendar();
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedDateTime == null)
                    dp = new ViewDateTimePickerPersian(context);
                else
                    dp = new ViewDateTimePickerPersian(context, selectedDateTime);

                new AlertDialog.Builder(context)
                        .setTitle("انتخاب زمان و تاریخ")
                        .setCancelable(false)
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedDateTime = dp.getDate();
                                        timePicker.setText(selectedDateTime.getIranianDateTime());
                                    }
                                }
                        )
                        .show();
            }
        });
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
