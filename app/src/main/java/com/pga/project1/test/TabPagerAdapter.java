package com.pga.project1.test;

/**
 * Created by aliparsa on 9/1/2014.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.fragment.FragmentTaskInfo;
import com.pga.project1.fragment.FragmentWorkReport;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for Android Tab
                return new Android();
            case 1:
                //Fragment for Ios Tab
                return new Ios();
            case 2:
                //Fragment for Windows Tab
                FragmentTaskInfo f1 = new FragmentTaskInfo();
                f1.setChart(new Chart(1, 1, "ali", "ali", "ali",
                        new Personnel(1, "ali", "ali", "1", "ali"),
                        "ali",
                        "ali",
                        "ali",
                        "ali",
                        "ali",
                        "ali",
                        10, 10, null, ""));


                return f1;
        }
        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3; //No of Tabs
    }
}