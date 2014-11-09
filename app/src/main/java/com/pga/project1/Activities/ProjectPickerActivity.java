package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.CallBackFunction;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.HandleError;
import com.pga.project1.Utilities.ListViewAdapterHandler;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;
import java.util.List;

import static com.pga.project1.Utilities.TwiceBackEndApp.twiceBackCheck;

public class ProjectPickerActivity extends Activity {

    ListView lv;
    final Context context = this;
    Chart chart;


    private ListViewCustomAdapter adapter;
    private PathMapManager pathManager;
    private boolean TwiceBackPressed = false;

    ArrayList<Chart> projects;
    private ImageView reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);


        lv = (ListView) findViewById(R.id.lv_fragmentProjectTreeView_treeView);
        lv.setAdapter(ListViewAdapterHandler.getLoadingAdapter(this));

        //TODO create function to handle listview loading

        pathManager = (PathMapManager) findViewById(R.id.pmm);
        pathManager.clear();
        PathMapManager.push(new PathObject("مدیریت سریع"));
        pathManager.refresh();

        prepareActionBar();


        // load projects
        final DatabaseHelper db = new DatabaseHelper(context);
        projects = db.getProjects();
        if (projects.size() < 1)
            Webservice.getProjects(context, new CallBack<ArrayList<Chart>>() {
                @Override
                public void onSuccess(ArrayList<Chart> result) {
                    db.emptyProjectsTable();
                    for (Chart chart : result) {
                        db.insertProject(chart);
                    }
                    loadProjects();

                }

                @Override
                public void onError(String errorMessage) {
                    if (errorMessage.equals("UNAUTHORIZED")) {

                        // clear token
                        Account.getInstant(context).clearToken();

                        // pass user to login page
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.putExtra("reason", "UNAUTHORIZED");
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                    }
                }
            });
        else
            loadProjects();
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

        title.setText("یک پروژه انتخاب نمایید");
        //ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);
        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        reload = (ImageView) customActionBar.findViewById(R.id.ac_action1);

        //addPhotoButton.setText("تصویر");
        reload.setImageResource(R.drawable.ac_refresh);
        //addPhotoButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DatabaseHelper db = new DatabaseHelper(context);

                Webservice.getProjects(context, new CallBack<ArrayList<Chart>>() {
                    @Override
                    public void onSuccess(ArrayList<Chart> result) {
                        db.emptyProjectsTable();
                        for (Chart chart : result) {
                            db.insertProject(chart);
                        }
                        loadProjects();

                    }

                    @Override
                    public void onError(String errorMessage) {
                        if (errorMessage.equals("UNAUTHORIZED")) {

                            // clear token
                            Account.getInstant(context).clearToken();

                            // pass user to login page
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("reason", "UNAUTHORIZED");
                            context.startActivity(intent);
                            ((Activity) context).overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                        }
                    }
                });
            }
        });
    }

    private void loadProjects() {

        pathManager.refresh();


        List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();

        DatabaseHelper db = new DatabaseHelper(context);

        projects = db.getProjects();

        for (Chart chart : projects) {

            itemList.add(new AdapterInputType(chart, ListViewCustomAdapter.CHART_ITEM, chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_launcher), chart.getId()));

        }

        adapter = new ListViewCustomAdapter(context, R.layout.fragment_layout_project_tree_view, itemList);
        lv.setAdapter(ListViewAdapterHandler.checkAdapterForNoItem(adapter));

        // set on click listener
        lv.setOnItemClickListener(new onTreeViewClickListener());


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);

        twiceBackCheck(this);

        super.onBackPressed();

    }

    public class onTreeViewClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart)
                chart = (Chart) tag;
            else
                return;

            Intent intent2 = new Intent(context, FastProjectManagmentActivity.class);
            intent2.putExtra("chart", chart);
            startActivityForResult(intent2, 147);
            overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
        }


    }


}
