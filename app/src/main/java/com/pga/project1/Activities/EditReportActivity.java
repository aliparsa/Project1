package com.pga.project1.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Report;
import com.pga.project1.R;
import com.pga.project1.Viewes.PathMapManager;

public class EditReportActivity extends Activity {

    private LinearLayout ll_image_list;
    private EditText etreport;
    private Button percent;
    private Button timePicker;
    private Report report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        report = (Report) getIntent().getSerializableExtra("report");
        PathMapManager.push(new PathObject(report.getDate()));

        setContentView(R.layout.activity_edit_report);




        ll_image_list = (LinearLayout) findViewById(R.id.ll_fragmentWork_workReport_imageList);
        etreport = (EditText) findViewById(R.id.edittext_fragmentNewReportWork_reportText);
        percent = (Button) findViewById(R.id.btn_fragmentNewReportWork_selectPercent);
        timePicker = (Button) findViewById(R.id.btn_fragmentNewReportWork_selectDate);

        etreport.setText(report.getReport());
        percent.setText(report.getPercent() + "%");
        timePicker.setText(report.getDate());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.ac_back) {

            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PathMapManager.pop("Edit Report act");
    }
}
