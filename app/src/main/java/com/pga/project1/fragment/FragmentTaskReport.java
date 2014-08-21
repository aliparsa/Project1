package com.pga.project1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.R;

/**
 * Created by ashkan on 8/19/2014.
 */
public class FragmentTaskReport extends Fragment {
    private Chart chart;
    private Menu menu;


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_task_report, container, false);

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);

        MenuItem addNewReport = menu.findItem(R.id.action_addReportTask);
        addNewReport.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------
    public void setChart(Chart chart) {
        this.chart = chart;
    }
    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    //---------------------------------------------------Factory function}
}
