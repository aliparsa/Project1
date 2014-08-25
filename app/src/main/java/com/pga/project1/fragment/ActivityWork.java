package com.pga.project1.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.R;
import com.pga.project1.Viewes.PathMapManager;

public class ActivityWork extends Activity {

    private Chart chart;
    private Menu menu;
    private FragmentWorkInfo infoFrag;
    private Fragment currentFrag;
    private FragmentWorkTask taskFrag;
    private FragmentWorkReport reportFrag;

    private boolean isTabsSet = false;

    PageType pageType;


    //----swipe test----------------------


    private GestureDetectorCompat mDetector;


    //-------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.chart = (Chart) getIntent().getSerializableExtra("chart");
        PathMapManager.push(chart);

        pageType = PageType.Info;

        setContentView(R.layout.activity_activity_work);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_fragment_work, menu);
        this.menu = menu;

        showHideMenuItems(false, false);

        if (!isTabsSet)
            setTabs("");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_back) {


            onBackPressed();

            return true;
        }

        /*if (item.getItemId() == R.id.ac_pick_personnel) {
            pickPersonnel();
            return true;
        }

        if (item.getItemId() == R.id.ac_new_work_report) {
            newWorkReport();
            return true;
        }*/


        return super.onOptionsItemSelected(item);
    }


    private void showHideMenuItems(boolean ac_pick_personnel_vis, boolean ac_new_work_report_vis) {

        MenuItem ac_pick_personnel = this.menu.findItem(R.id.ac_pick_personnel);
        MenuItem ac_new_work_report = this.menu.findItem(R.id.ac_new_work_report);

        if (ac_pick_personnel != null) {
            ac_pick_personnel.setVisible(ac_pick_personnel_vis);
        }

        if (ac_new_work_report != null) {
            ac_new_work_report.setVisible(ac_new_work_report_vis);
        }
    }

    private void setTabs(String caller) {

        isTabsSet = true;

        // Cleanup And set Tabs
        getActionBar().removeAllTabs();


        // Force Tab Support
        if (getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD)
            getActionBar().setNavigationMode(
                    ActionBar.NAVIGATION_MODE_TABS);

        // Create Tabs
        final ActionBar.Tab tab_workInfo = getActionBar().newTab();
        ActionBar.Tab tab_workTask = getActionBar().newTab();
        ActionBar.Tab tab_workReport = getActionBar().newTab();

        // Set Tab Titles
        tab_workInfo.setText("اطلاعات کار");
        tab_workTask.setText("وظیفه ها");
        tab_workReport.setText("گزارش عملکرد");

        // Set Tab Listeners
        tab_workInfo.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                if (infoFrag == null) {

                    currentFrag = infoFrag = new FragmentWorkInfo();
                    infoFrag.setChart(chart);
                    fragmentTransaction
                            .add(R.id.tab_host, infoFrag);
                    //.commit();

                } else if (currentFrag != infoFrag) {
                    fragmentTransaction
                            .attach(infoFrag);
                            /*.remove(currentFrag)
                            .add(R.id.tab_host, infoFrag)
                            .commit();*/

                    currentFrag = infoFrag;
                }

                pageType = PageType.Info;
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                fragmentTransaction.detach(currentFrag);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        tab_workTask.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                if (taskFrag == null) {

                    currentFrag = taskFrag = new FragmentWorkTask();
                    taskFrag.setChart(chart);

                    fragmentTransaction
                            .add(R.id.tab_host, taskFrag);

                } else if (currentFrag != taskFrag) {
                    fragmentTransaction
                            .attach(taskFrag);
                            /*.remove(currentFrag)
                            .add(R.id.tab_host, taskFrag)
                            .commit();*/

                    currentFrag = taskFrag;
                }

                pageType = PageType.Task;
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                fragmentTransaction.detach(currentFrag);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        tab_workReport.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                if (reportFrag == null) {

                    currentFrag = reportFrag = new FragmentWorkReport();
                    reportFrag.setChart(chart);

                    fragmentTransaction
                            .add(R.id.tab_host, reportFrag);

                } else if (currentFrag != reportFrag) {
                    fragmentTransaction
                            .attach(reportFrag);
                            /*.remove(currentFrag)
                            .add(R.id.tab_host, reportFrag)
                            .commit();*/

                    currentFrag = reportFrag;
                }

                pageType = PageType.Report;

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                fragmentTransaction.detach(currentFrag);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });

        getActionBar().addTab(tab_workReport, false);
        getActionBar().addTab(tab_workTask, false);
        getActionBar().addTab(tab_workInfo, false);

        switch (pageType) {

            case Info: {
                getActionBar().selectTab(tab_workInfo);
                break;
            }
            case Task: {
                getActionBar().selectTab(tab_workTask);
                break;
            }
            case Report: {
                getActionBar().selectTab(tab_workReport);
                break;
            }

        }

    }

    @Override
    public void onBackPressed() {

        PathMapManager.pop("ActivityTaskPage");
        super.onBackPressed();
    }

    public enum PageType {
        Info,
        Task,
        Report
    }
}
