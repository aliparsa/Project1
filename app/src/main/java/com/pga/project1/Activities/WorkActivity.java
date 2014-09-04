package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.fragment.FragmentWorkInfo;
import com.pga.project1.fragment.FragmentWorkReport;
import com.pga.project1.fragment.FragmentWorkTask;

public class WorkActivity extends FragmentActivity {

    private Chart chart;
    private Menu menu;
    private FragmentWorkInfo infoFrag;
    private Fragment currentFrag;
    private FragmentWorkTask taskFrag;
    private FragmentWorkReport reportFrag;

    private boolean isTabsSet = false;

    PageType pageType;
    private Button addPersonnelButton;
    private Button addReportButton;

    ///
    ViewPager Tab;
    WorkPageTabPagerAdapter TabAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD)
            getActionBar().setNavigationMode(
                    ActionBar.NAVIGATION_MODE_TABS);

        this.chart = (Chart) getIntent().getSerializableExtra("chart");
        PathMapManager.push(chart);

        getActionBar().setTitle(chart.getName());

        pageType = PageType.Info;

        isTabsSet = false;

        setContentView(R.layout.activity_activity_work);

        prepareActionBar();

        ////swipe
        // add swipe tab

        TabAdapter = new WorkPageTabPagerAdapter(getSupportFragmentManager(), chart);
        Tab = (ViewPager) findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar = getActionBar();
                        actionBar.setSelectedNavigationItem(position);
                    }
                }
        );
        Tab.setAdapter(TabAdapter);
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        // Create Tabs
        final ActionBar.Tab tab_workInfo = getActionBar().newTab();
        ActionBar.Tab tab_workTask = getActionBar().newTab();
        ActionBar.Tab tab_workReport = getActionBar().newTab();

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabReselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Tab.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 2:
                        showHideMenuItems(false, false);
                        break;

                    case 1:
                        showHideMenuItems(true, false);
                        break;

                    case 0:
                        showHideMenuItems(false, true);
                        break;
                }
            }

            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }
        };

        // Set Tab Titles
        tab_workInfo.setText("اطلاعات کار").setTabListener(tabListener);
        tab_workTask.setText("وظیفه ها").setTabListener(tabListener);
        tab_workReport.setText("گزارش عملکرد").setTabListener(tabListener);


        getActionBar().addTab(tab_workReport, false);
        getActionBar().addTab(tab_workTask, false);
        getActionBar().addTab(tab_workInfo, false);

        this.getActionBar().selectTab(tab_workInfo);

        /*ActionBar.Tab tab_taskInfo = getActionBar().newTab();
        ActionBar.Tab tab_taskInfo = getActionBar().newTab();
        ActionBar.Tab tab_taskReport = getActionBar().newTab();

        // Set Tab Titles
        tab_taskInfo.setText("اطلاعات").setTabListener(tabListener);
        tab_taskInfo.setText("اطلاعات").setTabListener(tabListener);
        tab_taskReport.setText("گزارش عملکرد").setTabListener(tabListener);

        getActionBar().addTab(tab_taskReport);
        getActionBar().addTab(tab_taskInfo);

        this.getActionBar().selectTab(tab_taskInfo);*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (currentFrag instanceof FragmentWorkTask) {
            ((FragmentWorkTask) currentFrag).setChart(chart);
        }
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

        addPersonnelButton = (Button) customActionBar.findViewById(R.id.ac_action1);
        addReportButton = (Button) customActionBar.findViewById(R.id.ac_action2);

        //addPersonnelButton.setText("پرسنل جدید");
        addPersonnelButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_add_personnel);
        addPersonnelButton.setTextColor(getResources().getColor(R.color.icon_color));

        //addReportButton.setText("عملکرد جدید");
        addReportButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_add_report);
        addReportButton.setTextColor(getResources().getColor(R.color.icon_color));


        showHideMenuItems(false, false);

        //setTabs("prepare");
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_fragment_work, menu);
        this.menu = menu;

        showHideMenuItems(false, false);

        if (!isTabsSet)
            setTabs("");

        return true;
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_back) {


            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showHideMenuItems(boolean ac_pick_personnel_vis, boolean ac_new_work_report_vis) {

        addPersonnelButton.setVisibility(View.GONE);
        addReportButton.setVisibility(View.GONE);

        if (ac_pick_personnel_vis)
            addPersonnelButton.setVisibility(View.VISIBLE);

        if (ac_new_work_report_vis)
            addReportButton.setVisibility(View.VISIBLE);
    }

/*    private void showHideMenuItems(boolean ac_pick_personnel_vis, boolean ac_new_work_report_vis) {

        MenuItem ac_pick_personnel = this.menu.findItem(R.id.ac_pick_personnel);
        MenuItem ac_new_work_report = this.menu.findItem(R.id.ac_new_work_report);

        if (ac_pick_personnel != null) {
            ac_pick_personnel.setVisible(ac_pick_personnel_vis);
        }

        if (ac_new_work_report != null) {
            ac_new_work_report.setVisible(ac_new_work_report_vis);
        }
    }*/

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

           /*     if (infoFrag == null) {

                    currentFrag = infoFrag = new FragmentWorkInfo();
                    infoFrag.setChart(chart);
                    fragmentTransaction
                            .add(R.id.tab_host, infoFrag);
                    //.commit();

                } else if (currentFrag != infoFrag) {
                    fragmentTransaction
                            .attach(infoFrag);
                            *//*.remove(currentFrag)
                            .add(R.id.tab_host, infoFrag)
                            .commit();*//*

                    currentFrag = infoFrag;
                }

                pageType = PageType.Info;*/
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

              /*  fragmentTransaction.detach(currentFrag);*/
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {


            }
        });
        tab_workTask.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
               /* if (taskFrag == null) {

                    currentFrag = taskFrag = new FragmentWorkTask();
                    taskFrag.setChart(chart);

                    fragmentTransaction
                            .add(R.id.tab_host, taskFrag);

                } else if (currentFrag != taskFrag) {
                    fragmentTransaction
                            .attach(taskFrag);
                            *//*.remove(currentFrag)
                            .add(R.id.tab_host, taskFrag)
                            .commit();*//*

                    currentFrag = taskFrag;
                }

                pageType = PageType.Task;
                showHideMenuItems(true, false);*/
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//                fragmentTransaction.detach(currentFrag);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        tab_workReport.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
              /*  if (reportFrag == null) {

                    currentFrag = reportFrag = new FragmentWorkReport();
                    reportFrag.setChart(chart);

                    fragmentTransaction
                            .add(R.id.tab_host, reportFrag);

                } else if (currentFrag != reportFrag) {
                    fragmentTransaction
                            .attach(reportFrag);
                            *//*.remove(currentFrag)
                            .add(R.id.tab_host, reportFrag)
                            .commit();*//*

                    currentFrag = reportFrag;
                }

                pageType = PageType.Report;
                showHideMenuItems(false, true);*/
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                // fragmentTransaction.detach(currentFrag);
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
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);

    }


    public enum PageType {
        Info,
        Task,
        Report
    }
}
