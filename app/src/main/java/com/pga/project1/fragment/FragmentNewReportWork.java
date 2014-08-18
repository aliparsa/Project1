package com.pga.project1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pga.project1.MainActivity;
import com.pga.project1.R;

/**
 * Created by ashkan on 8/18/2014.
 */
public class FragmentNewReportWork extends Fragment {

    //{Constants-----------------------------------------------------

    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------
    public FragmentNewReportWork() {

    }
    //-----------------------------------------------------Constructor}


    //{override functions---------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_report_work,
                container, false);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).hideTabs();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------

    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{static callback classes-----------------------------------------------------

    //-----------------------------------------------------static callback classes}

    //{Setter getters-----------------------------------------------------


    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    //---------------------------------------------------Factory function}

}
