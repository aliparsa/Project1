package com.pga.project1.Activities;

/**
 * Created by aliparsa on 9/1/2014.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pga.project1.fragment.FaliatFragment;
import com.pga.project1.fragment.TaradodFragment;


public class FastProjectManTabPageAdapter extends FragmentStatePagerAdapter {


    public FastProjectManTabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragment for Ios Tab
                FaliatFragment f2 = new FaliatFragment();
                return f2;


            case 1:
                //Fragement for Android Tab
                TaradodFragment f1 = new TaradodFragment();
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