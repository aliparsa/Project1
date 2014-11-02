package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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

public class FastProjectManagmentActivity extends Activity {

    boolean isFirstTime = true;
    private Context context;
    Personnel personnel;
    private ImageView saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_project_managment);
        //personnel_name.setNameValue("تلفن",personnel.get());

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
               /* if(task_name.getText().toString().length() == 0) {

                    saveButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.view_not_valid));
                    task_name.startAnimation(AnimationUtils.loadAnimation(context, R.anim.view_not_valid));
                    Toast.makeText(context, "نام وظیفه باید پر شود", Toast.LENGTH_SHORT).show();
                    return;
                }*/



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

                                setResult(Activity.RESULT_CANCELED);
                                finish();
                                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);

                            }
                        });
                builder.show();


            }
        });
    }

    private void addFaliat() {

        //Faliat faliat = new Faliat()
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

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fast_project_managment, menu);
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
                .setItems(new String[]{"تبت فعالیت", "ثبت تردد"}, new DialogInterface.OnClickListener() {
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
