package com.pga.project1.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Activities.EditReportActivity;
import com.pga.project1.Activities.NewReportActivity;
import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Report;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ListViewAdapterHandler;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/19/2014.
 */
public class FragmentTaskReport extends Fragment {
    private Chart chart;
    private Menu menu;
    private ListView listView;
    private ImageView addReportButton;


    //{ButtonConstants-----------------------------------------------------

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

    //--------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_task_report, container, false);


        listView = (ListView) rootView.findViewById(R.id.lv_fragmentTask_report);
        listView.setAdapter(ListViewAdapterHandler.getLoadingAdapter(getActivity()));

        prepareActionBar();
        loadReports();


        return rootView;
    }

    //--------------------------------------------------------------------------------
    public void loadReports() {


        Webservice.getReportListByWorkId(getActivity(), chart.getId(), new CallBack<ArrayList<Report>>() {
            @Override
            public void onSuccess(ArrayList<Report> reportList) {

                ArrayList<AdapterInputType> itemList = new ArrayList<AdapterInputType>();
                for (Report report : reportList) {
                    itemList.add(new AdapterInputType(report, ListViewCustomAdapter.REPORT_ITEM, report.getDate(), report.getPercent() + "", BitmapFactory.decodeResource(getResources(),
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

    //{Functions-----------------------------------------------------
    public void setChart(Chart chart) {
        this.chart = chart;
    }


    //-----------------------------------------------------override functions}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 654) {
            loadReports();
        }
    }
    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    //---------------------------------------------------Factory function}

    //--------------------------------------------------------------------------------
    private void prepareActionBar() {

        View customActionBar = getActivity().getActionBar().getCustomView();

        addReportButton = (ImageView) customActionBar.findViewById(R.id.ac_action2);
        addReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewReportActivity.class);
                intent.putExtra("chart", chart);
                startActivityForResult(intent, 654);
                getActivity().overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
            }
        });


    }

    //--------------------------------------------------------------------------------
    public class onReportListClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();

            if (tag instanceof Report) {

                Intent intent = new Intent(getActivity(), EditReportActivity.class);
                intent.putExtra("report", (Report) tag);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);

            } else
                return;

            Toast.makeText(getActivity(), chart.getType_id() + "", Toast.LENGTH_LONG).show();
        }


    }
}
