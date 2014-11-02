package com.pga.project1.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.pga.project1.DataModel.Personnel;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FastProjectManagmentActivity extends ActionBarActivity {

    boolean isFirstTime = true;
    private Context context;
    Personnel personnel;
    private ImageView saveButton;
    private ImageView attendance;
    private ImageView faliat;
    private ImageView synch;


    ViewPager Tab;
    FastProjectManTabPageAdapter TabAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_project_managment);
        //personnel_name.setNameValue("تلفن",personnel.get());


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
        tab_taskInfo.setText("اطلاعات").setTabListener(tabListener);
        tab_taskReport.setText("گزارش عملکرد").setTabListener(tabListener);

        getSupportActionBar().addTab(tab_taskReport);
        getSupportActionBar().addTab(tab_taskInfo);

        this.getSupportActionBar().selectTab(tab_taskInfo);

        prepareActionBar();
    }

    private void prepareActionBar() {

        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_back, null);
        final ActionBar actionBar = getSupportActionBar();
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
                onBackPressed();
            }
        });

        synch = (ImageView) customActionBar.findViewById(R.id.ac_action3);
        attendance = (ImageView) customActionBar.findViewById(R.id.ac_action2);
        faliat = (ImageView) customActionBar.findViewById(R.id.ac_action1);

        //addPhotoButton.setText("تصویر");
        attendance.setImageResource(R.drawable.ic_action_attendance);
        //addPhotoButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //saveButton.setText("ذخیره");
        faliat.setImageResource(R.drawable.ic_action_faaliyat);
        // saveButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));
        final Context context = this;
        faliat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        synch.setImageResource(R.drawable.ic_action_synch);
        synch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

        if (isFirstTime) {
            Intent intent = new Intent(this, PersonelPickerActivity.class);
            startActivityForResult(intent, 1411);
        }
        isFirstTime = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1411) {
            personnel = (Personnel) data.getSerializableExtra("personnel");
            HandleFastProjectManagement(personnel);
        }
    }

    //-------------------------------------------------------------------------------------//-------------------------------------------------------------------------------------
    public void HandleFastProjectManagement(final Personnel personnel) {
        new AlertDialog.Builder(context)
                .setTitle("انتخاب نمایید")
                .setItems(new String[]{"ثبت فعالیت", "ثبت تردد"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(context, FastAddPersonnelToWork.class);
                                intent.putExtra("personnel", personnel);
                                startActivity(intent);
                                break;

                            case 1:
                                HandleTaradod(personnel);
                                break;
                        }
                    }
                })
                .show();
    }

    //-------------------------------------------------------------------------------------
    private void HandleTaradod(final Personnel personnel) {
        new AlertDialog.Builder(context)
                .setTitle("ثبت تردد")
                .setItems(new String[]{"ثبت ورود", "ثبت خروج"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        switch (i) {
                            case 0:

                                HandelIn(personnel);

                                break;
                            case 1:
                                HandelOut(personnel);
                                break;
                        }
                    }
                })

                .show();
    }

    //---------------------------------------------------------------------
    private void HandelIn(final Personnel personnel) {
        new AlertDialog.Builder(context)
                .setTitle("انتخاب زمان")
                .setItems(new String[]{"ثبت زمان فعلی", "وارد نمودن تاریخ و ساعت"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        switch (i) {
                            case 0:
                                InOutCurrentTime(personnel, "in");
                                break;
                            case 1:
                                InOutCustomTime(personnel, "in");
                                break;
                        }
                    }
                })
                .show();

    }

    //----------------------------------------------------------------------
    private void InOutCurrentTime(Personnel personnel, String in_out) {
        try {
            DatabaseHelper db = new DatabaseHelper(context);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH-mm");
            String currentDateandTime = sdf.format(new Date());
            db.insertTaradod(personnel, in_out, currentDateandTime);
            Toast.makeText(context, "ذخیره شد", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "عملیات با خطا مواجه شد", Toast.LENGTH_SHORT).show();
        }
    }

    //----------------------------------------------------------------------
    private void HandelOut(final Personnel personnel) {
        new AlertDialog.Builder(context)
                .setTitle("انتخاب زمان")
                .setItems(new String[]{"ثبت زمان فعلی", "وارد نمودن تاریخ و ساعت"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        switch (i) {
                            case 0:
                                InOutCurrentTime(personnel, "out");
                                break;
                            case 1:
                                InOutCustomTime(personnel, "out");
                                break;
                        }
                    }
                })
                .show();
    }
    //-------------------------------------------------------------------------------------

    private void InOutCustomTime(final Personnel personnel, final String in_out) {
        final ViewDateTimePickerPersian pickerPersian = new ViewDateTimePickerPersian(context);
        new AlertDialog.Builder(context)

                .setTitle("انتخاب تاریخ و زمان")
                .setView(pickerPersian)
                .setTitle("تاریخ و ساعت را وارد نمایید")
                .setPositiveButton("ذخیره", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {


                                    String date = pickerPersian.getDate().getGregorianDate();
                                    int hour = pickerPersian.getHour();
                                    int minute = pickerPersian.getMinute();
                                    date += " " + hour + "-" + minute;

                                    DatabaseHelper db = new DatabaseHelper(context);
                                    db.insertTaradod(personnel, in_out, date);

                                    Toast.makeText(context, "ذخیره شد", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(context, "عملیات با خطا مواجه شد", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                )
                .

                        setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }
                        )
                .

                        show();
    }

    //-------------------------------------------------------------------------------------

}
