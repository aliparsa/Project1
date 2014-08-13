package com.pga.project1.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.pga.project1.DataModel.Feature;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.ViewNameValue;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/9/2014.
 */
public class FragmentWorkInfo extends Fragment {


    // Constants-----------------------------------------------------

    //-----------------------------------------------------Constants

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    ViewNameValue workName;
    private Chart chart;
    LinearLayout MainLinearLayout;

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------


    public FragmentWorkInfo() {

    }
    //-----------------------------------------------------Constructor}

    //{override functions---------------------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_info,
                container, false);


        workName = new ViewNameValue(getActivity());

        MainLinearLayout = (LinearLayout) view.findViewById(R.id.main_ln);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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


//
//        MainLinearLayout.addView(new ViewNameValue(getActivity(),,));
//        MainLinearLayout.addView(new ViewNameValue(getActivity(),,));
//        MainLinearLayout.addView(new ViewNameValue(getActivity(),,));
//        MainLinearLayout.addView(new ViewNameValue(getActivity(),,));
//        MainLinearLayout.addView(new ViewNameValue(getActivity(),,));


    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }


    //-----------------------------------------------------override functions}


    //{Functions-----------------------------------------------------


    //-----------------------------------------------------Functions}


    //validations-----------------------------------------------------


    //-----------------------------------------------------validations}


    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

}
