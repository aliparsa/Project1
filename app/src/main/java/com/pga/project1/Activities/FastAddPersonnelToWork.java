package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.Adapters.MySpinnerAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Faliat;
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
    private ImageView saveButton;
    private EditText mizanKar;
    private Work selectedWork;
    private Chart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_add_personnel_to_work);
        //


        context = this;

        chart = (Chart) getIntent().getSerializableExtra("chart");


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
        personnel_phone.setNameValue("شماره تماس", personnel.getPhone_number());


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
                    selectedWork = (Work) aView.getTag();
                    int a = 10;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> anAdapterView) {
                // do nothing
            }
        });
        mizanKar = (EditText) findViewById(R.id.editText_MizanKar);

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

        prepareActionBar();

    }

    private void prepareActionBar() {

        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_back, null);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(customActionBar);

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

        saveButton = (ImageView) customActionBar.findViewById(R.id.ac_action1);
        saveButton.setImageResource(R.drawable.ic_save);

        final Context context = this;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validation
                if (personnel == null || selectedWork == null || selectedDateTime == null || mizanKar.length() < 1) {
                    saveButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.view_not_valid));
                    Toast.makeText(context, "اطلاعات تکمیل نیست", Toast.LENGTH_SHORT).show();
                    return;
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("آیا فعالیت ثبت شود؟")
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                addFaliat();

                            }
                        }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });
                builder.show();


            }
        });
    }

    private void addFaliat() {


        Faliat faliat = new Faliat(personnel.getId() + "", selectedWork.getId() + "", mizanKar.getText().toString(), selectedDateTime.getGregorianDate() + "", 0, chart.getId() + "");
        DatabaseHelper db = new DatabaseHelper(context);
        db.insertFaliat(faliat);
        finish();
    }


}
