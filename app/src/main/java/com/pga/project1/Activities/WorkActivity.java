package com.pga.project1.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pga.project1.Adapters.WorkPageTabPagerAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.fragment.FragmentWorkInfo;
import com.pga.project1.fragment.FragmentWorkReport;
import com.pga.project1.fragment.FragmentWorkTask;

public class WorkActivity extends ActionBarActivity {

    private Chart chart;
    private Menu menu;
    private FragmentWorkInfo infoFrag;
    private Fragment currentFrag;
    private FragmentWorkTask taskFrag;
    private FragmentWorkReport reportFrag;

    private boolean isTabsSet = false;

    PageType pageType;
    private ImageView addPersonnelButton;
    private ImageView addReportButton;

    ///
    ViewPager Tab;
    WorkPageTabPagerAdapter TabAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.chart = (Chart) getIntent().getSerializableExtra("chart");
        PathMapManager.push(chart);

        getSupportActionBar().setTitle(chart.getName());

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
                        actionBar = getSupportActionBar();
                        actionBar.setSelectedNavigationItem(position);
                    }
                }
        );
        Tab.setAdapter(TabAdapter);
        actionBar = getSupportActionBar();

        // Create Tabs
        ActionBar.Tab tab_workInfo = getSupportActionBar().newTab();
        ActionBar.Tab tab_workTask = getSupportActionBar().newTab();
        ActionBar.Tab tab_workReport = getSupportActionBar().newTab();

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {


            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
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
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        };

        // Set Tab Titles
        tab_workInfo.setText("اطلاعات کار").setTabListener(tabListener);
        tab_workTask.setText("وظیفه ها").setTabListener(tabListener);
        tab_workReport.setText("گزارش عملکرد").setTabListener(tabListener);


        getSupportActionBar().addTab(tab_workReport, false);
        getSupportActionBar().addTab(tab_workTask, false);
        getSupportActionBar().addTab(tab_workInfo, false);

        this.getSupportActionBar().selectTab(tab_workInfo);

        /*ActionBar.Tab tab_taskInfo = getSupportActionBar().newTab();
        ActionBar.Tab tab_taskInfo = getSupportActionBar().newTab();
        ActionBar.Tab tab_taskReport = getSupportActionBar().newTab();

        // Set Tab Titles
        tab_taskInfo.setText("اطلاعات").setTabListener(tabListener);
        tab_taskInfo.setText("اطلاعات").setTabListener(tabListener);
        tab_taskReport.setText("گزارش عملکرد").setTabListener(tabListener);

        getSupportActionBar().addTab(tab_taskReport);
        getSupportActionBar().addTab(tab_taskInfo);

        this.getSupportActionBar().selectTab(tab_taskInfo);*/


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
        final ActionBar actionBar = getSupportActionBar();

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

        //ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);
        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addPersonnelButton = (ImageView) customActionBar.findViewById(R.id.ac_action1);
        addReportButton = (ImageView) customActionBar.findViewById(R.id.ac_action2);

        //addPersonnelButton.setText("پرسنل جدید");
        addPersonnelButton.setImageResource(R.drawable.ic_add_personnel);

        //addReportButton.setText("عملکرد جدید");
        addReportButton.setImageResource(R.drawable.ic_add_report);


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

/*
    private void setTabs(String caller) {

        isTabsSet = true;

        // Cleanup And set Tabs
        getSupportActionBar().removeAllTabs();


        // Force Tab Support
        if (getSupportActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD)
            getSupportActionBar().setNavigationMode(
                    ActionBar.NAVIGATION_MODE_TABS);

        // Create Tabs
        final ActionBar.Tab tab_workInfo = getSupportActionBar().newTab();
        ActionBar.Tab tab_workTask = getSupportActionBar().newTab();
        ActionBar.Tab tab_workReport = getSupportActionBar().newTab();

        // Set Tab Titles
        tab_workInfo.setText("اطلاعات کار");
        tab_workTask.setText("وظیفه ها");
        tab_workReport.setText("گزارش عملکرد");

        // Set Tab Listeners
        tab_workInfo.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

           */
/*     if (infoFrag == null) {

                    currentFrag = infoFrag = new FragmentWorkInfo();
                    infoFrag.setChart(chart);
                    fragmentTransaction
                            .add(R.id.tab_host, infoFrag);
                    //.commit();

                } else if (currentFrag != infoFrag) {
                    fragmentTransaction
                            .attach(infoFrag);
                            *//*
*/
/*.remove(currentFrag)
                            .add(R.id.tab_host, infoFrag)
                            .commit();*//*
*/
/*

                    currentFrag = infoFrag;
                }

                pageType = PageType.Info;*//*

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

              */
/*  fragmentTransaction.detach(currentFrag);*//*

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {


            }
        });
        tab_workTask.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
               */
/* if (taskFrag == null) {

                    currentFrag = taskFrag = new FragmentWorkTask();
                    taskFrag.setChart(chart);

                    fragmentTransaction
                            .add(R.id.tab_host, taskFrag);

                } else if (currentFrag != taskFrag) {
                    fragmentTransaction
                            .attach(taskFrag);
                            *//*
*/
/*.remove(currentFrag)
                            .add(R.id.tab_host, taskFrag)
                            .commit();*//*
*/
/*

                    currentFrag = taskFrag;
                }

                pageType = PageType.Task;
                showHideMenuItems(true, false);*//*

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
              */
/*  if (reportFrag == null) {

                    currentFrag = reportFrag = new FragmentWorkReport();
                    reportFrag.setChart(chart);

                    fragmentTransaction
                            .add(R.id.tab_host, reportFrag);

                } else if (currentFrag != reportFrag) {
                    fragmentTransaction
                            .attach(reportFrag);
                            *//*
*/
/*.remove(currentFrag)
                            .add(R.id.tab_host, reportFrag)
                            .commit();*//*
*/
/*

                    currentFrag = reportFrag;
                }

                pageType = PageType.Report;
                showHideMenuItems(false, true);*//*

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                // fragmentTransaction.detach(currentFrag);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });

        getSupportActionBar().addTab(tab_workReport, false);
        getSupportActionBar().addTab(tab_workTask, false);
        getSupportActionBar().addTab(tab_workInfo, false);

        switch (pageType) {

            case Info: {
                getSupportActionBar().selectTab(tab_workInfo);
                break;
            }
            case Task: {
                getSupportActionBar().selectTab(tab_workTask);
                break;
            }
            case Report: {
                getSupportActionBar().selectTab(tab_workReport);
                break;
            }

        }

    }
*/

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
