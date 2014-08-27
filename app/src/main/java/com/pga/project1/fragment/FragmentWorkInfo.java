package com.pga.project1.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Feature;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.ViewNameValue;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWorkInfo extends Fragment {


    private Chart chart;

    public FragmentWorkInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            chart = (Chart) savedInstanceState.getSerializable("chart");
        }
        return inflater.inflate(R.layout.fragment_work_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareInfo();
    }

    private void prepareInfo() {

        final LinearLayout MainLinearLayout = (LinearLayout) getView().findViewById(R.id.main_ln);

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
            public void onError(String err) {
                Toast.makeText(getActivity(), "Error 100", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("chart", chart);
    }
}
