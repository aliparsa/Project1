package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Report;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Viewes.ImageLoaderView;
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

        for (int i = 0; i < report.getImageUrls().size(); i++) {
            if (report.getImageUrls().get(i) != null) {
                final ImageLoaderView imgvl = new ImageLoaderView(this, report.getImageUrls().get(i));
                imgvl.setPadding(3, 3, 3, 3);
                imgvl.setTag(report.getImageUrls().get(i));
                imgvl.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                ll_image_list.addView(imgvl);

                imgvl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showImage((String) imgvl.getTag());
                    }
                });
            }

        }

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

       // ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);
        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_report, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.ac_back) {

            overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
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

    //------------------------------------
    public void showImage(String path) {
        Intent intent = new Intent(this, ShowImageActivity.class);
        intent.putExtra("image_url", (String) path);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
    }

    @Override
    public void onBackPressed() {

        overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
        this.finish();
    }
}
