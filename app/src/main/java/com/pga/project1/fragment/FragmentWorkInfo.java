package com.pga.project1.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pga.project1.R;
import com.pga.project1.Structures.Chart;
import com.pga.project1.Viewes.ViewNameValue;

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
