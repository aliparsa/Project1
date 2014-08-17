package com.pga.project1.fragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentProjectTreeView extends Fragment {

    ListView lv;
    private Chart chart;

    // ------------------------------------------------------------------------------------
    public FragmentProjectTreeView() {

    }


    // ------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //getActivity().getActionBar().show();


        View view = inflater.inflate(R.layout.fragment_layout_project_tree_view, container,
                false);

        lv = (ListView) view.findViewById(R.id.lv_fragmentProjectTreeView_treeView);

        final FragmentProjectTreeView self = this;

        if (chart == null) {

            Webservice.getProjects(getActivity(), new CallBack<ArrayList<Chart>>() {
                @Override
                public void onSuccess(ArrayList<Chart> result) {
                    //TODO Create Adapter

                    List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();

                    for (Chart chart : result) {

                        itemList.add(new AdapterInputType(chart, "icon+title+subtitle", chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                                R.drawable.ic_launcher), chart.getId()));

                    }

                    ListViewCustomAdapter adapter =
                            new ListViewCustomAdapter(getActivity(), R.layout.fragment_layout_project_tree_view, itemList);

                    lv.setAdapter(adapter);

                    // set on click listener
                    lv.setOnItemClickListener(new onTreeViewClickListener());

                }

                @Override
                public void onError(ErrorMessage err) {
                    //TODO Show Error
                    Toast.makeText(getActivity(), "Error 101", Toast.LENGTH_SHORT).show();
                    //Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
                    //toast.show();
                }


            });
        } else {
            Webservice.GetChildOfID(getActivity(), chart.getId(), new CallBack<ArrayList<Chart>>() {
                @Override
                public void onSuccess(ArrayList<Chart> result) {
                    // Child Returned
                    List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();

                    for (Chart chart : result) {
                        itemList.add(new AdapterInputType(chart, "icon+title+subtitle", chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                                R.drawable.ic_launcher), chart.getId()));
                    }

                    // create adapter from data
                    ListViewCustomAdapter adapter =
                            new ListViewCustomAdapter(getActivity(), R.layout.fragment_layout_project_tree_view, itemList);

                    // set adapter to lv
                    lv.setAdapter(adapter);

                    // set on click listener
                    lv.setOnItemClickListener(new onTreeViewClickListener());


                }

                @Override
                public void onError(ErrorMessage err) {
                    Toast.makeText(getActivity(), "Error 102", Toast.LENGTH_SHORT).show();
                }


            });
        }

        return view;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    // ------------------------------------------------------------------------------------
    public class onTreeViewClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart)
                chart = (Chart) tag;
            else
                return;


            //String item_name = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).title.getText().toString();


            Toast.makeText(getActivity(), chart.getType_id() + "", Toast.LENGTH_LONG).show();

            switch (chart.getType_id()) {
                case 0: // item is chart
                    ((MainActivity) getActivity()).ShowTreeFragmnet(chart, "Project Tree View Fragment");

                    //pushing to Path Map
                    PathMapManager.push(chart);

                    break;
                case 1:       // item is work
                    ((MainActivity) getActivity()).ShowWorkFragment(chart, "Project Tree View Fragment", true);

                    //pushing to Path Map
                    PathMapManager.push(chart);

            }


        }


    }

    @Override
    public void onResume() {
        super.onResume();

        // hide Tabs If Tab Resumed After a Fragment With Tab
        ((MainActivity) getActivity()).hideTabs();
    }
}
// ------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------


