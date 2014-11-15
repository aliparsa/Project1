package com.pga.project1.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.Adapters.FastProjectManTabPageAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Helpers.SyncHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FastProjectManagmentActivity extends ActionBarActivity {

    private Context context;
    Personnel personnel;
    private ImageView saveButton;
    private ImageView attendance;
    private ImageView faliat;
    private ImageView synch;

    public Chart chart;
    private PathMapManager pathManager;

    ViewPager Tab;
    public FastProjectManTabPageAdapter TabAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_project_managment);


        //personnel_name.setNameValue("تلفن",personnel.get(
        //
        // ));

        this.chart = (Chart) getIntent().getSerializableExtra("chart");
        pathManager = (PathMapManager) findViewById(R.id.pmm);


        pathManager.clear();
        PathMapManager.push(new PathObject("مدیریت سریع"));
        PathMapManager.push(chart);
        pathManager.refresh();


        //Add New Tab

        // actionBar.addTab(actionBar.newTab().setText("گزارش عملکرد").setTabListener(tabListener),false);
        // actionBar.addTab(actionBar.newTab().setText("اطلاعات").setTabListener(tabListener),false);
        //

        TabAdapter = new FastProjectManTabPageAdapter(getSupportFragmentManager());
        Tab = (ViewPager) findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar = getSupportActionBar();
                        actionBar.setSelectedNavigationItem(position);
                    }
                }
        );
        Tab.setAdapter(TabAdapter);
        actionBar = getSupportActionBar();

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                Tab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        };

        android.support.v7.app.ActionBar.Tab tab_taskInfo = getSupportActionBar().newTab();
        android.support.v7.app.ActionBar.Tab tab_taskReport = getSupportActionBar().newTab();

        // Set Tab Titles
        tab_taskInfo.setText("تردد").setTabListener(tabListener);
        tab_taskReport.setText("عملکرد").setTabListener(tabListener);

        getSupportActionBar().addTab(tab_taskReport);
        getSupportActionBar().addTab(tab_taskInfo);

        this.getSupportActionBar().selectTab(tab_taskInfo);

        prepareActionBar();
    }

    private void prepareActionBar() {

        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_back, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setCustomView(customActionBar);

        actionBar.setLogo(null); // forgot why this one but it helped
        actionBar.setIcon(null);

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);

        View homeIcon = findViewById(android.R.id.home);

        if (homeIcon != null) {
            homeIcon.setVisibility(View.GONE);
        }
        if (homeIcon.getParent() != null) {
            ((View) homeIcon.getParent()).setVisibility(View.GONE);
        }


        TextView title = (TextView) customActionBar.findViewById(R.id.ac_title);
        FontHelper.SetFont(this, Fonts.MAIN_FONT, title, Typeface.BOLD);

        title.setText("مدیریت سریع");
        //ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);
        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        synch = (ImageView) customActionBar.findViewById(R.id.ac_action3);
        attendance = (ImageView) customActionBar.findViewById(R.id.ac_action2);
        faliat = (ImageView) customActionBar.findViewById(R.id.ac_action1);

        //addPhotoButton.setText("تصویر");
        attendance.setImageResource(R.drawable.ac_walking);
        //addPhotoButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PathMapManager.push(new PathObject("ثبت تردد"));

                Intent intent = new Intent(context, PersonelPickerActivity.class);

                startActivityForResult(intent, 1412);
            }
        });

        //saveButton.setText("ذخیره");
        faliat.setImageResource(R.drawable.ic_action_faaliyat);
        // saveButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));
        final Context context = this;
        faliat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PathMapManager.push(new PathObject("ثبت فعالیت"));
                Intent intent = new Intent(context, PersonelPickerActivity.class);
                startActivityForResult(intent, 1413);

            }
        });

        synch.setImageResource(R.drawable.ic_action_synch);
        synch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "در حال بررسی و ارسال", Toast.LENGTH_SHORT).show();
                SyncHelper.SyncTaradodAndFaliat(context);
                DatabaseHelper db = new DatabaseHelper(context);
                db.cleanOldData(7);


            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1412) {
            personnel = (Personnel) data.getSerializableExtra("personnel");
            PathMapManager.pop("FastProj onActivityResult 1412");
            //HandleTaradod(personnel);
            HandelInOut(personnel);
        }

        if (resultCode == RESULT_OK && requestCode == 1413) {
            personnel = (Personnel) data.getSerializableExtra("personnel");
            PathMapManager.pop("FastProj onActivityResult 1413");
            Intent intent = new Intent(context, FastAddPersonnelToWork.class);
            intent.putExtra("personnel", personnel);
            intent.putExtra("chart", chart);
            startActivity(intent);


            Tab.setAdapter(TabAdapter);
            Tab.setCurrentItem(0);
        }


        if (resultCode == RESULT_CANCELED) {
            PathMapManager.pop("FastProj onActivityResult RESULT_CANCELED");
        }
    }

    //-------------------------------------------------------------------------------------

    private void HandelInOut(final Personnel personnel) {
        DatabaseHelper db = new DatabaseHelper(context);
        //  final String last_in_out = db.getPersonnelInOrOut(personnel);

        final ViewDateTimePickerPersian pickerPersian = new ViewDateTimePickerPersian(context);
        new AlertDialog.Builder(context)
                .setView(pickerPersian)
                .setTitle("ثبت تردد برای  " + personnel.getFullName())
                .setPositiveButton("ثبت ورود", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {

//                                    if (last_in_out != null && last_in_out.equals("in")) {
//                                        Toast.makeText(context, "این پرسنل قبلا وارد شده است", Toast.LENGTH_LONG).show();
//                                        return;
//                                    }
                                    String date = pickerPersian.getDate().getGregorianDate();
                                    String hour = pickerPersian.getHour() < 10 ? "0" + pickerPersian.getHour() : pickerPersian.getHour() + "";
                                    String minute = pickerPersian.getMinute() < 10 ? "0" + pickerPersian.getMinute() : pickerPersian.getMinute() + "";
                                    date += " " + hour + "-" + minute;

                                    DatabaseHelper db = new DatabaseHelper(context);
                                    Taradod taradod = new Taradod(personnel.getId() + "", "in", 0, date, chart.getId() + "");
                                    db.insertTaradod(taradod);

                                    Toast.makeText(context, "ذخیره شد", Toast.LENGTH_SHORT).show();
                                    Tab.setAdapter(TabAdapter);
                                    Tab.setCurrentItem(1);
                                } catch (Exception e) {
                                    Toast.makeText(context, "عملیات با خطا مواجه شد", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                )
                .

                        setNegativeButton("ثبت خروج", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        try {
//                                            if (last_in_out == null || last_in_out.equals("out")) {
//                                                Toast.makeText(context, "این پرسنل هنوز وارد نشده", Toast.LENGTH_LONG).show();
//                                                return;
//                                            }

                                            String date = pickerPersian.getDate().getGregorianDate();
                                            String hour = pickerPersian.getHour() < 10 ? "0" + pickerPersian.getHour() : pickerPersian.getHour() + "";
                                            String minute = pickerPersian.getMinute() < 10 ? "0" + pickerPersian.getMinute() : pickerPersian.getMinute() + "";
                                            date += " " + hour + "-" + minute;

                                            DatabaseHelper db = new DatabaseHelper(context);
                                            Taradod taradod = new Taradod(personnel.getId() + "", "out", 0, date, chart.getId() + "");
                                            db.insertTaradod(taradod);

                                            Toast.makeText(context, "ذخیره شد", Toast.LENGTH_SHORT).show();
                                            Tab.setAdapter(TabAdapter);
                                            Tab.setCurrentItem(1);
                                        } catch (Exception e) {
                                            Toast.makeText(context, "عملیات با خطا مواجه شد", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                        )
                .

                        show();
    }
}
