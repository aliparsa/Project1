package com.pga.project1.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.MainActivity;
import com.pga.project1.R;

/**
 * Created by ashkan on 8/19/2014.
 */
public class FragmentTaskPage extends Fragment {


    //{Constants-----------------------------------------------------
    public String[] TABS = {"اطلاعات","گزارشات"};
    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    FrameLayout container;


    private PageType pageType;
    private Chart chart;
    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------
    public FragmentTaskPage(){


    }
    //-----------------------------------------------------Constructor}


    //{override functions---------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_page, container, false);



        setHasOptionsMenu(true);
        return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        switch (pageType){
//            case Info:{
//                FragmentTaskInfo info = new FragmentTaskInfo();
//
//                FragmentManager fm = getFragmentManager();
//                fm.beginTransaction().add(R.id.host_taskPage, info)
//                        .commit();
//
//                break;
//            }
//
//            case Reports:{
//                FragmentTaskReport reports = new FragmentTaskReport();
//
//                FragmentManager fm = getFragmentManager();
//                fm.beginTransaction().add(R.id.host_taskPage, reports)
//                        .commit();
//                break;
//            }
//        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        setTabs();
    }

    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    private void setTabs() {
        // Force Tab Support
        if (getActivity().getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_STANDARD)
            getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create Tabs
        ActionBar.Tab tab_taskInfo = getActivity().getActionBar().newTab();
        ActionBar.Tab tab_taskReport = getActivity().getActionBar().newTab();

        // Set Tab Titles
        tab_taskInfo.setText("اطلاعات");
        tab_taskReport.setText("گزارشات");

        // Set Tab Listeners
        tab_taskInfo.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                ((MainActivity) getActivity()).ShowTaskInfoFragment(chart);
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        });

        tab_taskReport.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                ((MainActivity) getActivity()).ShowTaskReportsFragment(chart);
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        });

        // Cleanup And set Tabs
        getActivity().getActionBar().removeAllTabs();

        switch (pageType){
            case Info:{
                getActivity().getActionBar().addTab(tab_taskInfo, true);
                getActivity().getActionBar().addTab(tab_taskReport, false);
                break;
            }
            case Reports:{
                getActivity().getActionBar().addTab(tab_taskInfo, true);
                getActivity().getActionBar().addTab(tab_taskReport, false);
                break;
            }
        }

    }
    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    public static FragmentTaskPage getInstanceInfo(){

        FragmentTaskPage frag =  new FragmentTaskPage();
        frag.pageType = PageType.Info;
        return frag;
    }

    public static FragmentTaskPage getInstanceReports(){

        FragmentTaskPage frag =  new FragmentTaskPage();
        frag.pageType = PageType.Reports;
        return frag;
    }



    //---------------------------------------------------Factory function}


    private static enum PageType{
        Info,
        Reports
    }
}

