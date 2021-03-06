package com.pga.project1.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.fragment.FragmentWorkInfo;
import com.pga.project1.fragment.FragmentWorkReport;
import com.pga.project1.fragment.FragmentWorkTask;

/**
 * Created by aliparsa on 9/2/2014.
 */
public class WorkPageTabPagerAdapter extends FragmentStatePagerAdapter {
    private final FragmentManager supportFragmentManager;
    private final Chart chart;

    public WorkPageTabPagerAdapter(FragmentManager supportFragmentManager, Chart chart) {

        super(supportFragmentManager);

        this.supportFragmentManager = supportFragmentManager;
        this.chart = chart;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //Fragment for Ios Tab
                FragmentWorkReport f3 = new FragmentWorkReport();
                Bundle bundle = new Bundle();
                bundle.putSerializable("chart", chart);
                f3.setArguments(bundle);
                return f3;


            case 1:
                //Fragment for Android Tab
                FragmentWorkTask f2 = new FragmentWorkTask();
                //f2.setChart(chart);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("chart", chart);
                f2.setArguments(bundle2);
                return f2;

            case 2:
                //Fragment for Android Tab
                FragmentWorkInfo f1 = new FragmentWorkInfo();
                f1.setChart(chart);
                return f1;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
