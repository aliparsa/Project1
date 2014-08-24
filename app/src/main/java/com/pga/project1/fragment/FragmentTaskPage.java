package com.pga.project1.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Viewes.PathMapManager;

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
    private Menu menu;

    FragmentTaskInfo infoFrag;
    FragmentTaskReport reportFrag;
    Fragment currentFrag;

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------
    public FragmentTaskPage(){

        this.pageType = PageType.Info;
    }
    //-----------------------------------------------------Constructor}


    //{override functions---------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_page, container, false);

        return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        menu.clear();
        inflater.inflate(R.menu.menu_fragment_task, menu);
        this.menu = menu;


        MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
        if (addNewReport != null) addNewReport.setVisible(false);

        setTabs();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_addReportTask){

            ((MainActivity) getActivity()).ShowNewTaskReportFragment(chart);

            return true;
        }

        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        PathMapManager.pop("fragmenttaskpage detach");
    }


    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    private void setTabs() {

        getActivity().getActionBar().removeAllTabs();

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

                if(infoFrag == null) {

                    currentFrag = infoFrag = new FragmentTaskInfo();
                    infoFrag.setChart(chart);

                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .add(R.id.host_taskPage, infoFrag)
                            .commit();

                }else if(currentFrag != infoFrag){
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.host_taskPage, infoFrag)
                            .commit();

                    currentFrag = infoFrag;

                }

                pageType = PageType.Info;
                MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
                if(addNewReport != null) addNewReport.setVisible(false);
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        });

        tab_taskReport.setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                if(reportFrag == null) {

                    currentFrag = reportFrag = new FragmentTaskReport();
                    reportFrag.setChart(chart);
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .add(R.id.host_taskPage, reportFrag)
                            .commit();

                }else if(currentFrag != reportFrag){

                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.host_taskPage, reportFrag)
                            .commit();

                    currentFrag = reportFrag;
                }

                pageType = PageType.Reports;
                MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
                if(addNewReport != null) addNewReport.setVisible(true);

            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        });
        // Cleanup And set Tabs

        getActivity().getActionBar().addTab(tab_taskReport, false);
        getActivity().getActionBar().addTab(tab_taskInfo, false);

        if(this.pageType == PageType.Info) {
            this.getActivity().getActionBar().selectTab(tab_taskInfo);
        }else if(this.pageType == PageType.Reports){
            this.getActivity().getActionBar().selectTab(tab_taskReport);
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

