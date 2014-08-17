package com.pga.project1.fragment;


import android.app.Fragment;
import android.os.Bundle;
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
 * Created by ashkan on 8/9/2014.
 */
public class FragmentWorkTasks extends Fragment {


    // Constants-----------------------------------------------------

    //-----------------------------------------------------Constants

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------


    public FragmentWorkTasks() {

    }
    //-----------------------------------------------------Constructor}

    //{override functions---------------------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_tasks,
                container, false);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
