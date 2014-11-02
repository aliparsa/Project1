package com.pga.project1.Adapters;

/**
 * Created by aliparsa on 9/1/2014.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.fragment.FragmentTaskInfo;
import com.pga.project1.fragment.FragmentTaskReport;
import com.pga.project1.test.Android;
import com.pga.project1.test.Ios;


public class TaskPageTabPagerAdapter extends FragmentStatePagerAdapter {

    private Chart chart;

    public TaskPageTabPagerAdapter(FragmentManager fm, Chart chart) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.chart = chart;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragment for Ios Tab
                FragmentTaskReport f2 = new FragmentTaskReport();
                f2.setChart(chart);
                return f2;


            case 1:
                //Fragement for Android Tab
                FragmentTaskInfo f1 = new FragmentTaskInfo();
                f1.setChart(chart);
                return f1;
        }
        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2; //No of Tabs
    }
}