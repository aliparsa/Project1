package com.pga.project1.Adapters;

/**
 * Created by aliparsa on 9/1/2014.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pga.project1.fragment.FragmentFaliat;
import com.pga.project1.fragment.FragmentTaradod;


public class FastProjectManTabPageAdapter extends FragmentStatePagerAdapter {

    public FragmentTaradod f1;
    public FragmentFaliat f2;

    public FastProjectManTabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragment for Ios Tab
                f2 = new FragmentFaliat();
                return f2;


            case 1:
                //Fragement for Android Tab
                f1 = new FragmentTaradod();
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