package com.pga.project1.fragment;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        this.setChart((Chart) bundle.getSerializable("chart"));
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
        lv.setAdapter(ListViewAdapterHandler.getLoadingAdapter(getActivity()));

        prepareReport();

        prepareActionbarButton();
    }

    private void prepareActionbarButton() {
        View actionbar = getActivity().getActionBar().getCustomView();
        ImageView addReport = (ImageView) actionbar.findViewById(R.id.ac_action2);
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

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.ac_new_work_report) {
            newWorkReport();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    public void newWorkReport() {
        Intent intent = new Intent(getActivity(), NewReportActivity.class);
        intent.putExtra("chart", chart);
        startActivityForResult(intent, 148);
        getActivity().overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
    }

    private void prepareReport() {


        try {

            Webservice.getReportListByWorkId(getActivity(), chart.getId(), new CallBack<ArrayList<Report>>() {
                @Override
                public void onSuccess(ArrayList<Report> reportList) {

                    if (!isAdded()) return;
                    List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();
                    for (Report report : reportList) {

                        itemList.add(new AdapterInputType(report, ListViewCustomAdapter.REPORT_ITEM, report.getDate(), report.getPercent() + "", BitmapFactory.decodeResource(getResources(),
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
                            getActivity().overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                        }
                    });

                }

                @Override
                public void onError(String err) {
                    Toast.makeText(getActivity(), "Error 109", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
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
