package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.DataModel.Task;
import com.pga.project1.DataModel.WorkUnit;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.PersianCalendar;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;

import java.util.ArrayList;

public class AddPersonnelToWorkActivity extends Activity {


    private Spinner spinner_noe_kar;
    private PersianCalendar selectedStartDateTime;
    private PersianCalendar selectedEndDateTime;
    EditText task_name;
    EditText task_price;
    Button button_start_date;
    Button button_end_date;

    EditText editText_kol_kar;
    EditText editText_tozihat;
    private Chart chart;
    private Personnel personnel;

    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personnel_to_work);


        chart = (Chart) getIntent().getSerializableExtra("chart");
        personnel = (Personnel) getIntent().getSerializableExtra("personnel");

        PathMapManager.push(new PathObject(personnel.getFullName() + " در" + chart.getName()));

        task_name = (EditText) findViewById(R.id.editText_task_name);
        task_price = (EditText) findViewById(R.id.editText_task_price);
        button_start_date = (Button) findViewById(R.id.button_start_date);
        button_end_date = (Button) findViewById(R.id.button_end_date);

        editText_kol_kar = (EditText) findViewById(R.id.editText_kol_kar);
        editText_tozihat = (EditText) findViewById(R.id.editText_tozihat);


        spinner_noe_kar = (Spinner) findViewById(R.id.spinner_vahed_kar);

        prepareForm();

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

        ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        saveButton = (Button) customActionBar.findViewById(R.id.ac_action1);
        saveButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_save,0, 0, 0);

        final Context context = this;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(task_name.getText().toString().length() == 0) {

                    saveButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.view_not_valid));
                    task_name.startAnimation(AnimationUtils.loadAnimation(context, R.anim.view_not_valid));
                    Toast.makeText(context, "نام وظیفه باید پر شود", Toast.LENGTH_SHORT).show();
                    return;
                }



                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("آیا وظیفه ثبت شود؟")
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                addPersonnelToWork();
                            }
                        }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                setResult(Activity.RESULT_CANCELED);
                                finish();
                            }
                        });
                builder.show();


            }
        });
    }

    private void prepareForm() {

        final AddPersonnelToWorkActivity self = this;

        selectedStartDateTime = new PersianCalendar();
        selectedEndDateTime = new PersianCalendar();

        button_start_date.setText(selectedStartDateTime.getIranianDateTime());
        button_end_date.setText(selectedEndDateTime.getIranianDateTime());

        //--------------------------------------------------
        button_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedStartDateTime == null)
                    dp = new ViewDateTimePickerPersian(self);
                else
                    dp = new ViewDateTimePickerPersian(self, selectedStartDateTime);

                new AlertDialog.Builder(self)
                        .setTitle("انتخاب زمان و تاریخ")
                        .setCancelable(false)
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedStartDateTime = dp.getDate();
                                        button_start_date.setText(selectedStartDateTime.getIranianDateTime());
                                    }
                                }
                        )
                        .show();
            }
        });
        //-------------------------------------------------------------
        button_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedEndDateTime == null)
                    dp = new ViewDateTimePickerPersian(self);
                else
                    dp = new ViewDateTimePickerPersian(self, selectedEndDateTime);

                new AlertDialog.Builder(self)
                        .setTitle("انتخاب زمان و تاریخ")
                        .setCancelable(false)
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedEndDateTime = dp.getDate();
                                        button_end_date.setText(selectedEndDateTime.getIranianDateTime());
                                    }
                                }
                        )
                        .show();
            }
        });

        Webservice.getWorkUnitList(this, new CallBack<ArrayList<WorkUnit>>() {
            @Override
            public void onSuccess(ArrayList<WorkUnit> result) {

                String[] workUnit = new String[result.size()];
                int[] workUnitId = new int[result.size()];

                for (int i = 0; i < result.size(); i++) {
                    workUnit[i] = result.get(i).toString();
                    workUnitId[i] = result.get(i).getId();
                }

                spinner_noe_kar.setAdapter(new ArrayAdapter<String>(self, android.R.layout.simple_spinner_item, workUnit));

            }

            @Override
            public void onError(String err) {
                //TODO KHATA DAR ERTEBAT INTERNETI

            }
        });
    }


    private void addPersonnelToWork() {

        final AddPersonnelToWorkActivity self = this;

        Task task = new Task(task_name.getText().toString(),
                task_price.getText().toString(),
                selectedStartDateTime.getIranianDate(),
                selectedEndDateTime.getIranianDate(),
                editText_kol_kar.getText().toString(),
                (spinner_noe_kar.getSelectedItemId() + 1) + "",
                editText_tozihat.getText().toString()
        );

        final ProgressDialog pg = new ProgressDialog(this);
        pg.setMessage("در حال ارسال");
        pg.show();

        Webservice.addPersonnelToWork(this, personnel.getId(), chart.getId(), task, new CallBack<ServerResponse>() {
            @Override
            public void onSuccess(ServerResponse result) {
                pg.setMessage("در حال بروزرسانی اطلاعات");
                if (result.getResult().equals("{\"result\":\"ok\"}")) {
                    Toast.makeText(self, "عملیات انجام شد", Toast.LENGTH_SHORT).show();

                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    //Toast.makeText(self, "عملیات انجام نشد", Toast.LENGTH_SHORT).show();

                    new AlertDialog.Builder(self)
                            .setTitle("عملیات انجام نشد")
                            .setCancelable(false)
                            .setPositiveButton("تلاش مجدد", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            addPersonnelToWork();
                                        }
                                    }
                            ).setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    setResult(Activity.RESULT_CANCELED);
                                    finish();
                                }
                            }
                    ).show();

                }
                pg.dismiss();
            }

            @Override
            public void onError(String err) {
                pg.dismiss();

                new AlertDialog.Builder(self)
                        .setTitle("عملیات انجام نشد")
                        .setCancelable(false)
                        .setPositiveButton("تلاش مجدد", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        addPersonnelToWork();
                                    }
                                }
                        ).setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                setResult(Activity.RESULT_CANCELED);
                                finish();
                            }
                        }
                ).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        PathMapManager.pop("add personnel to work activity");
    }
}
