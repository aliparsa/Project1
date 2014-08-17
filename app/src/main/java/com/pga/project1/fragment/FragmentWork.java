package com.pga.project1.fragment;


import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Feature;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.ViewNameValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashkan on 8/9/2014.
 */
public class FragmentWork extends Fragment {


    // Constants-----------------------------------------------------

    //-----------------------------------------------------Constants

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    ViewNameValue workName;
    private Chart chart;

    LinearLayout ll_work_info;
    LinearLayout ll_work_tasks;
    LinearLayout ll_work_report;



    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------


    public FragmentWork() {

    }
    //-----------------------------------------------------Constructor}

    //{override functions---------------------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work,
                container, false);


        workName = new ViewNameValue(getActivity());

        ll_work_info = (LinearLayout) view.findViewById(R.id.ll_fragmentWork_workInfo);
        ll_work_tasks = (LinearLayout) view.findViewById(R.id.ll_fragmentWork_workTasks);
        ll_work_report = (LinearLayout) view.findViewById(R.id.ll_fragmentWork_workReport);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        prepareInfo();

        prepareTasks();

        prepareReport();


    }

    private void prepareReport() {
    }

    private void prepareTasks() {

        final ListView lv = (ListView) ll_work_tasks.findViewById(R.id.lv_fragmentWork_task_taskviewr);
        Webservice.getTaskListByWorkId(getActivity(), chart.getId(), new CallBack<ArrayList<Chart>>() {
            @Override
            public void onSuccess(ArrayList<Chart> taskList) {

                List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();
                for (Chart chart : taskList) {
                    itemList.add(new AdapterInputType(chart, "icon+title+subtitle", chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher)));
                }

                // create adapter from data
                ListViewCustomAdapter adapter =
                        new ListViewCustomAdapter(getActivity(), R.layout.fragment_layout_project_tree_view, itemList);

                // set adapter to lv
                lv.setAdapter(adapter);

                // set on click listener
                lv.setOnItemClickListener(new onTaskListClickListener());

            }

            @Override
            public void onError(ErrorMessage err) {
                Toast.makeText(getActivity(), "Error 102", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void prepareInfo() {
        final LinearLayout MainLinearLayout = (LinearLayout) ll_work_info.findViewById(R.id.main_ln);

        MainLinearLayout.addView(new ViewNameValue(getActivity(), "نام", chart.getName()));
        MainLinearLayout.addView(new ViewNameValue(getActivity(), "تاریخ شروع", chart.getStart_date()));
        MainLinearLayout.addView(new ViewNameValue(getActivity(), "پایان شروع", chart.getEnd_date()));
        MainLinearLayout.addView(new ViewNameValue(getActivity(), "وضعیت", chart.getStatus()));
        MainLinearLayout.addView(new ViewNameValue(getActivity(), "واحد کار", chart.getWork_unit()));

        Webservice.getFeatureById(getActivity(), chart.getId(), new CallBack<ArrayList<Feature>>() {
            @Override
            public void onSuccess(ArrayList<Feature> featureList) {
                chart.setFeatureList(featureList);

                for (Feature fe : chart.getFeatureList()) {
                    MainLinearLayout.addView(new ViewNameValue(getActivity(), fe.getName(), fe.getValue()));
                }
            }

            @Override
            public void onError(ErrorMessage err) {
                Toast.makeText(getActivity(), "Error 100", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        // Set Tabs
        setTabs();
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }


    //-----------------------------------------------------override functions}


    //{Functions-----------------------------------------------------

    private void setTabs() {

        // Force Tab Support
        if (getActivity().getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD)
            getActivity().getActionBar().setNavigationMode(
                    ActionBar.NAVIGATION_MODE_TABS);

        // Create Tabs
        ActionBar.Tab tab_workInfo = getActivity().getActionBar().newTab();
        ActionBar.Tab tab_workTask = getActivity().getActionBar().newTab();
        ActionBar.Tab tab_workReport = getActivity().getActionBar().newTab();

        // Set Tab Titles
        tab_workInfo.setText("اطلاعات");
        tab_workTask.setText("پرسنل");
        tab_workReport.setText("گزارشات");

        // Set Tab Listeners
        tab_workInfo.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                pleasOnlyShow(ll_work_info);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        tab_workTask.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                pleasOnlyShow(ll_work_tasks);

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });
        tab_workReport.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                pleasOnlyShow(ll_work_report);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });

        // Cleanup And set Tabs
        getActivity().getActionBar().removeAllTabs();
        getActivity().getActionBar().addTab(tab_workReport, false);
        getActivity().getActionBar().addTab(tab_workTask, false);
        getActivity().getActionBar().addTab(tab_workInfo, true);

    }

    //-----------------------------------------------------Functions}


    //validations-----------------------------------------------------


    //-----------------------------------------------------validations}


    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}
    public class onTaskListClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart)
                chart = (Chart) tag;
            else
                return;

            Toast.makeText(getActivity(), chart.getType_id() + "", Toast.LENGTH_LONG).show();
        }


    }


    public void pleasOnlyShow(LinearLayout ll) {
        ll_work_info.setVisibility(View.GONE);
        ll_work_tasks.setVisibility(View.GONE);
        ll_work_report.setVisibility(View.GONE);
        ll.setVisibility(View.VISIBLE);

    }
}
