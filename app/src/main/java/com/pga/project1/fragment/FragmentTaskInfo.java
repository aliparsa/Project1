package com.pga.project1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.R;
import com.pga.project1.Viewes.ImageLoaderView;
import com.pga.project1.Viewes.ViewNameValue;

/**
 * Created by ashkan on 8/19/2014.
 */
public class FragmentTaskInfo extends Fragment {
    private Chart chart;
    private ViewNameValue personnelNameView;
    private ViewNameValue personnelJobView;
    private ViewNameValue personnelPhoneView;
    private ViewNameValue taskStartView;
    private ViewNameValue taskEndView;
    private ViewNameValue taskPercentView;
    private ViewNameValue dayWorkedView;
    private ImageLoaderView personnelPic;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_task_info, container, false);


        personnelPic = (ImageLoaderView) rootView.findViewById(R.id.imgv_fragmentTaskInfo_pic);
        personnelNameView = (ViewNameValue) rootView.findViewById(R.id.txt_fragmentTaskInfo_personnelName);
        personnelJobView = (ViewNameValue) rootView.findViewById(R.id.txt_fragmentTaskInfo_personnelJob);
        personnelPhoneView = (ViewNameValue) rootView.findViewById(R.id.txt_fragmentTaskInfo_personnelPhone);
        taskStartView = (ViewNameValue) rootView.findViewById(R.id.txt_fragmentTaskInfo_taskStart);
        taskEndView = (ViewNameValue) rootView.findViewById(R.id.txt_fragmentTaskInfo_taskEnd);
        taskPercentView = (ViewNameValue) rootView.findViewById(R.id.txt_fragmentTaskInfo_taskPercent);
        dayWorkedView = (ViewNameValue) rootView.findViewById(R.id.txt_fragmentTaskInfo_dayWorked);


        personnelPic.setUrl(chart.getPersonnel().getPersonnel_image());

        personnelNameView.setValue(chart.getPersonnel().getFullName());
        personnelJobView.setValue(chart.getName());
        personnelPhoneView.setValue(chart.getPersonnel().getPhone_number());

        taskStartView.setValue(chart.getStart_date());
        taskEndView.setValue(chart.getEnd_date());
        taskPercentView.setValue(chart.getHand_percent() + "%");



        return rootView;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }
    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------

    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    //---------------------------------------------------Factory function}
}
