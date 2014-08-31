package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.fragment.FragmentTaskInfo;
import com.pga.project1.fragment.FragmentTaskReport;

public class TaskPageActivity extends Activity {


    //{Constants-----------------------------------------------------
    public String[] TABS = {"اطلاعات", "گزارشات"};
    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    FrameLayout container;


    private PageType pageType;
    private Chart chart;
    private Menu menu;

    FragmentTaskInfo infoFrag;
    FragmentTaskReport reportFrag;
    Fragment currentFrag;

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------

    //-----------------------------------------------------Constructor}


    //{override functions---------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.chart = (Chart) getIntent().getSerializableExtra("chart");
        PathMapManager.push(chart);

        setContentView(R.layout.activity_activity_task_page);

        this.pageType = PageType.Info;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_fragment_task, menu);
        this.menu = menu;


        MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
        if (addNewReport != null) addNewReport.setVisible(false);

        MenuItem RemoveTask = menu.findItem(R.id.action_removeTask);
        if (RemoveTask != null) RemoveTask.setVisible(false);

        setTabs();

        return true;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_back) {


            onBackPressed();

            return true;
        }
        if (item.getItemId() == R.id.action_addReportTask) {

            //((MainActivity) getActivity()).ShowNewTaskReportFragment(chart);

            Intent intent = new Intent(this, NewReportActivity.class);
            intent.putExtra("chart", chart);
            startActivity(intent);

            return true;
        }
        if (item.getItemId() == R.id.action_removeTask) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("آیا " + chart.getPersonnel().getFullName() + " از " + this.chart.getName() + " حذف شود؟")
                    .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteChart();
                        }
                    }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
        }

        return false;
    }

    private void deleteChart() {

        final Activity self = this;

        Webservice.removeTask(this, chart.getId(), new CallBack<ServerResponse>() {
            @Override
            public void onSuccess(ServerResponse result) {

                Toast.makeText(self, "حذف انجام شد", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String err) {
                Toast.makeText(self, "حذف انجام نشد", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {

        PathMapManager.pop("ActivityTaskPage");
        super.onBackPressed();
    }


    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    private void setTabs() {

        getActionBar().removeAllTabs();

        // Force Tab Support
        if (getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD)
            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create Tabs
        ActionBar.Tab tab_taskInfo = getActionBar().newTab();
        ActionBar.Tab tab_taskReport = getActionBar().newTab();

        // Set Tab Titles
        tab_taskInfo.setText("اطلاعات");
        tab_taskReport.setText("گزارش عملکرد");

        // Set Tab Listeners
        tab_taskInfo.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                if (infoFrag == null) {

                    currentFrag = infoFrag = new FragmentTaskInfo();
                    infoFrag.setChart(chart);

                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .add(R.id.host_taskPage, infoFrag)
                            .commit();

                } else if (currentFrag != infoFrag) {
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.host_taskPage, infoFrag)
                            .commit();

                    currentFrag = infoFrag;

                }

                pageType = PageType.Info;
                MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
                if (addNewReport != null) addNewReport.setVisible(false);

                MenuItem RemoveTask = menu.findItem(R.id.action_removeTask);
                if (RemoveTask != null) RemoveTask.setVisible(true);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }
        });

        tab_taskReport.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                if (reportFrag == null) {

                    currentFrag = reportFrag = new FragmentTaskReport();
                    reportFrag.setChart(chart);
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .add(R.id.host_taskPage, reportFrag)
                            .commit();

                } else if (currentFrag != reportFrag) {

                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.host_taskPage, reportFrag)
                            .commit();

                    reportFrag.loadReports();

                    currentFrag = reportFrag;
                }

                pageType = PageType.Reports;
                MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
                if (addNewReport != null) addNewReport.setVisible(true);

                MenuItem RemoveTask = menu.findItem(R.id.action_removeTask);
                if (RemoveTask != null) RemoveTask.setVisible(false);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }
        });
        // Cleanup And set Tabs

        getActionBar().addTab(tab_taskReport, false);
        getActionBar().addTab(tab_taskInfo, false);

        if (this.pageType == PageType.Info) {
            this.getActionBar().selectTab(tab_taskInfo);
        } else if (this.pageType == PageType.Reports) {
            this.getActionBar().selectTab(tab_taskReport);
        }

    }
    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------


    //---------------------------------------------------Factory function}


    private static enum PageType {
        Info,
        Reports
    }
}
