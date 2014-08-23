package com.pga.project1.fragment;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Feature;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.Report;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.Graphview;
import com.pga.project1.Viewes.PathMapManager;
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

    public final static int TabInfo = 1;
    public final static int TabPersonnel = 2;
    public final static int TabReport = 3;
    ViewNameValue workName;
    LinearLayout ll_work_info;
    LinearLayout ll_work_tasks;
    LinearLayout ll_work_report;
    Menu menu;
    private Chart chart;
    // fill when we come back from personel picker
    private boolean comeFromPicker = false;
    private Personnel selectedPersonnel;
    // handle active tab
    private boolean blnTabInfo = false;
    private boolean blnTabPersonnel = false;
    private boolean blnTabReport = false;

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------


    public FragmentWork() {

    }
    //-----------------------------------------------------Constructor}

    //{override functions---------------------------------------------

    //--------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work,
                container, false);

        setHasOptionsMenu(true);

        workName = new ViewNameValue(getActivity());

        ll_work_info = (LinearLayout) view.findViewById(R.id.ll_fragmentWork_workInfo);
        ll_work_tasks = (LinearLayout) view.findViewById(R.id.ll_fragmentWork_workTasks);
        ll_work_report = (LinearLayout) view.findViewById(R.id.ll_fragmentWork_workReport);


        return view;
    }

    //--------------------------------------------------------------------------------
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        CheckComeFromPersonnelPicker();

        prepareInfo();

        prepareTasks();

        prepareReport();

    }

    //--------------------------------------------------------------------------------
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_fragment_work, menu);
        this.menu = menu;

        setTabs();

    }

    //--------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.ac_pick_personnel) {
            pickPersonnel();
            return true;
        }

        if (item.getItemId() == R.id.ac_new_work_report) {
            newWorkReport();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //--------------------------------------------------------------------------------
    private void callCamera() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(new String[]{"گالری", "دوربین"}, 0, null)
                .show();
    }

    //--------------------------------------------------------------------------------
    @Override
    public void onDetach() {
        super.onDetach();
        PathMapManager.pop("Fragment Work onDetach");
    }

    //--------------------------------------------------------------------------------
    private void CheckComeFromPersonnelPicker() {
        if (comeFromPicker) {

            //Toast.makeText(getActivity(),"Sending...", Toast.LENGTH_SHORT).show();

            // show loading dialog
            final ProgressDialog pg = new ProgressDialog(getActivity());
            pg.setMessage("Sending Request... \n Add Personnel > " + selectedPersonnel.getFirst_name() + " \n to \n " + chart.getName());
            pg.show();


            Webservice.addPersonnelToWork(getActivity(), selectedPersonnel.getId(), chart.getId(), new CallBack<ServerResponse>() {
                @Override
                public void onSuccess(ServerResponse result) {
                    pg.dismiss();

                    new AlertDialog.Builder(getActivity())
                            .setTitle("پرسنل افزوده شد")
                            .setMessage("Personnel \n " + selectedPersonnel.getFirst_name() + " \n Added to \n " + chart.getName())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("خب", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .show();

                }

                @Override
                public void onError(ErrorMessage err) {
                    pg.dismiss();

                    new AlertDialog.Builder(getActivity())
                            .setTitle("خطا")
                            .setMessage("خطا ")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("خب", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .show();
                }
            });

            comeFromPicker = false;
        }


    }

    //--------------------------------------------------------------------------------
    public void newWorkReport() {
        ((MainActivity) getActivity()).ShowNewReportFragment(chart, FragmentNewReport.REPORT_TYPE_WORK);
    }

    //--------------------------------------------------------------------------------
    private void prepareReport() {


        final ListView lv = (ListView) ll_work_report.findViewById(R.id.lv_fragmentWork_report_reportViewer);
        Webservice.getReportListByWorkId(getActivity(), chart.getId(), new CallBack<ArrayList<Report>>() {
            @Override
            public void onSuccess(ArrayList<Report> reportList) {

                List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();
                for (Report report : reportList) {
                    itemList.add(new AdapterInputType(chart, "icon+title+subtitle", report.getDate(), report.getPercent() + "", BitmapFactory.decodeResource(getResources(),
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
                Toast.makeText(getActivity(), "Error 109", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //-------------------------------------------------------------------------------
    public void pickPersonnel() {
        ((MainActivity) getActivity()).ShowPersonnelSearch(chart);
    }

    //--------------------------------------------------------------------------------
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

//        Graphview gr = new Graphview(getActivity(),new float[]{200,100,60});
//        gr.setMinimumHeight(200);
//        gr.setMinimumWidth(200);
//        MainLinearLayout.addView(gr);


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
        //setTabs();

//        PathMapManager pmm = (PathMapManager) getView().findViewById(R.id.path_map_fragment_work);
//        if ( pmm!=null )
//            pmm.refresh();


    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    //-----------------------------------------------------override functions}

    private void setTabs() {

        // Cleanup And set Tabs
        getActivity().getActionBar().removeAllTabs();


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




        if (blnTabInfo) {
            getActivity().getActionBar().addTab(tab_workReport, false);
            getActivity().getActionBar().addTab(tab_workTask, false);
            getActivity().getActionBar().addTab(tab_workInfo, true);
        } else if (blnTabPersonnel) {
            getActivity().getActionBar().addTab(tab_workReport, false);
            getActivity().getActionBar().addTab(tab_workTask, true);
            getActivity().getActionBar().addTab(tab_workInfo, false);
        } else if (blnTabReport) {
            getActivity().getActionBar().addTab(tab_workReport, true);
            getActivity().getActionBar().addTab(tab_workTask, false);
            getActivity().getActionBar().addTab(tab_workInfo, false);
        } else {
            getActivity().getActionBar().addTab(tab_workReport, false);
            getActivity().getActionBar().addTab(tab_workTask, false);
            getActivity().getActionBar().addTab(tab_workInfo, true);
        }


    }


    public boolean isComeFromPicker() {
        return comeFromPicker;
    }

    public void setComeFromPicker(boolean comeFromPicker) {
        this.comeFromPicker = comeFromPicker;
    }

    public Personnel getSelectedPersonnel() {
        return selectedPersonnel;
    }

    public void setSelectedPersonnel(Personnel selectedPersonnel) {
        this.selectedPersonnel = selectedPersonnel;
    }

    //-----------------------------------------------------Functions}


    //validations-----------------------------------------------------


    //-----------------------------------------------------validations}


    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    public void pleasOnlyShow(LinearLayout ll) {

        // hide all linear layouts
        ll_work_info.setVisibility(View.GONE);
        ll_work_tasks.setVisibility(View.GONE);
        ll_work_report.setVisibility(View.GONE);

        // hide Pick Personnel Menu Item
        if (menu != null) {
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
        }

        // make input visible
        ll.setVisibility(View.VISIBLE);

        // if input is Task Menu Pick Personnel is Show
        if (ll == ll_work_tasks)
            if (menu != null)
                menu.getItem(0).setVisible(true);

        if (ll == ll_work_report)
            if (menu != null)
                menu.getItem(1).setVisible(true);


    }

    public void setPersonnel(Personnel personnel) {
        this.selectedPersonnel = personnel;
        this.comeFromPicker = true;

    }

    public void setActiveTab(int activeTab) {

        blnTabInfo = false;
        blnTabPersonnel = false;
        blnTabReport = false;

        switch (activeTab) {
            case TabInfo:
                blnTabInfo = true;
                break;
            case TabPersonnel:
                blnTabPersonnel = true;
                break;
            case TabReport:
                blnTabReport = true;
                break;
        }
    }

    //-----------------------------------------------------Setter getters}
    public class onTaskListClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart) {
                chart = (Chart) tag;
                setActiveTab(FragmentWork.TabPersonnel);
                ((MainActivity) getActivity()).ShowTaskPageFragment(chart);
            } else
                return;

            Toast.makeText(getActivity(), chart.getType_id() + "", Toast.LENGTH_LONG).show();
        }


    }
}
