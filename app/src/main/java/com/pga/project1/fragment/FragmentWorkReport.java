package com.pga.project1.fragment;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWorkReport extends Fragment {


    private Chart chart;
    ListView lv;

    public FragmentWorkReport() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_work_report, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        lv = (ListView) getView().findViewById(R.id.lv_fragmentWork_report_reportViewer);

        prepareReport();

        prepareActionbarButton();
    }

    private void prepareActionbarButton() {
        View actionbar = getActivity().getActionBar().getCustomView();
        Button addReport = (Button) actionbar.findViewById(R.id.ac_action2);
        addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newWorkReport();
            }
        });
    }

/*    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // super.onCreateOptionsMenu(menu, inflater);

        MenuItem ac_new_work_report = menu.findItem(R.id.ac_new_work_report);

        if (ac_new_work_report != null) {
            ac_new_work_report.setVisible(true);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.ac_new_work_report) {
            newWorkReport();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void newWorkReport() {
        Intent intent = new Intent(getActivity(), NewReportActivity.class);
        intent.putExtra("chart", chart);
        startActivityForResult(intent, 148);
    }

    private void prepareReport() {


        Webservice.getReportListByWorkId(getActivity(), chart.getId(), new CallBack<ArrayList<Report>>() {
            @Override
            public void onSuccess(ArrayList<Report> reportList) {

                List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();
                for (Report report : reportList) {
                    itemList.add(new AdapterInputType(report, "icon+title+subtitle", report.getDate(), report.getPercent() + "", BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher)));
                }

                // create adapter from data
                ListViewCustomAdapter adapter =
                        new ListViewCustomAdapter(getActivity(), R.layout.fragment_layout_project_tree_view, itemList);

                // set adapter to lv
                lv.setAdapter(ListViewAdapterHandler.checkAdapterForNoItem(adapter));

                // set on click listener
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();

                        Intent intent = new Intent(getActivity(), EditReportActivity.class);
                        intent.putExtra("report", (Report) tag);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(String err) {
                Toast.makeText(getActivity(), "Error 109", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 148) {
            prepareReport();
        }
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }
}
