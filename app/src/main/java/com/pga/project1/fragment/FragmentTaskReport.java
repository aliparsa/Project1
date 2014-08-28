package com.pga.project1.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Activities.ActivityTaskPage;
import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Report;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ListViewAdapterHandler;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashkan on 8/19/2014.
 */
public class FragmentTaskReport extends Fragment {
    private Chart chart;
    private Menu menu;
    private ListView listView;


    //{Constants-----------------------------------------------------

    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------

    //-----------------------------------------------------Constructor}


    //{override functions---------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_task_report, container, false);


        listView = (ListView) rootView.findViewById(R.id.lv_fragmentTask_report);
        loadReports();


        return rootView;
    }

    private void loadReports() {


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
                listView.setAdapter(ListViewAdapterHandler.checkAdapterForNoItem(adapter));

                // set on click listener
                listView.setOnItemClickListener(new onReportListClickListener());

            }

            @Override
            public void onError(String err) {
                Toast.makeText(getActivity(), "Error 109", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class onReportListClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart) {
                chart = (Chart) tag;

                Intent intent = new Intent(getActivity(), ActivityTaskPage.class);
                intent.putExtra("chart", chart);
                startActivity(intent);

            } else
                return;

            Toast.makeText(getActivity(), chart.getType_id() + "", Toast.LENGTH_LONG).show();
        }


    }


    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------
    public void setChart(Chart chart) {
        this.chart = chart;
    }
    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    //---------------------------------------------------Factory function}
}
