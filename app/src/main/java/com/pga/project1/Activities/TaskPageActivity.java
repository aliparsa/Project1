package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.fragment.FragmentTaskInfo;
import com.pga.project1.fragment.FragmentTaskReport;

public class TaskPageActivity extends FragmentActivity {


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

    private Button removeTaskButton;
    private Button addReportButton;

    ViewPager Tab;
    TaskPageTabPagerAdapter TabAdapter;
    ActionBar actionBar;
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

        prepareActionBar();

        TabAdapter = new TaskPageTabPagerAdapter(getSupportFragmentManager(), chart);
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
                    case 0:
                        showHideMenuItems(false, true);
                        break;

                    case 1:
                        showHideMenuItems(true, false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub
            }
        };

        //Add New Tab

        // actionBar.addTab(actionBar.newTab().setText("گزارش عملکرد").setTabListener(tabListener),false);
        // actionBar.addTab(actionBar.newTab().setText("اطلاعات").setTabListener(tabListener),false);
        //
        ActionBar.Tab tab_taskInfo = getActionBar().newTab();
        ActionBar.Tab tab_taskReport = getActionBar().newTab();

        // Set Tab Titles
        tab_taskInfo.setText("اطلاعات").setTabListener(tabListener);
        tab_taskReport.setText("گزارش عملکرد").setTabListener(tabListener);

        getActionBar().addTab(tab_taskReport);
        getActionBar().addTab(tab_taskInfo);

        this.getActionBar().selectTab(tab_taskInfo);


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

        removeTaskButton = (Button) customActionBar.findViewById(R.id.ac_action1);
        addReportButton = (Button) customActionBar.findViewById(R.id.ac_action2);

        //removeTaskButton.setText("حذف وظیفه");
        removeTaskButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_delete);
        removeTaskButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));

        removeTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteChart();
            }
        });

        //addReportButton.setText("عملکرد جدید");
        addReportButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_add_report);
        addReportButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));

        showHideMenuItems(false, false);

    }

    private void showHideMenuItems(boolean ac_remove_task, boolean ac_new_work_report_vis) {


        removeTaskButton.setVisibility(View.GONE);
        addReportButton.setVisibility(View.GONE);

        if (ac_remove_task)
            removeTaskButton.setVisibility(View.VISIBLE);

        if (ac_new_work_report_vis)
            addReportButton.setVisibility(View.VISIBLE);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_fragment_task, menu);
        this.menu = menu;


        MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
        if (addNewReport != null) addNewReport.setVisible(false);

        MenuItem RemoveTask = menu.findItem(R.id.action_removeTask);
        if (RemoveTask != null) RemoveTask.setVisible(false);

        setTabs();

        return true;
    }*/


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
            startActivityForResult(intent, 654);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 654) {
            if (reportFrag != null && reportFrag == currentFrag)
                reportFrag.loadReports();

        }
    }

    private void deleteChart() {

        final Activity self = this;


        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("آیا " + chart.getPersonnel().getFullName() + " از " + this.chart.getName() + " حذف شود؟")
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Webservice.removeTask(self, chart.getId(), new CallBack<ServerResponse>() {
                            @Override
                            public void onSuccess(ServerResponse result) {

                                Toast.makeText(self, "حذف انجام شد", Toast.LENGTH_SHORT).show();

                                setResult(RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onError(String err) {
                                Toast.makeText(self, "حذف انجام نشد", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();

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

               /* if (infoFrag == null) {

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
                if (RemoveTask != null) RemoveTask.setVisible(true);*/
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

               /* if (reportFrag == null) {

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

                    currentFrag = reportFrag;
                }

                pageType = PageType.Reports;
                MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
                if (addNewReport != null) addNewReport.setVisible(true);

                MenuItem RemoveTask = menu.findItem(R.id.action_removeTask);
                if (RemoveTask != null) RemoveTask.setVisible(false);*/
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
