package com.pga.project1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Feature;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.Viewes.ViewNameValue;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/13/2014.
 */
public class FragmentWorkPersonnel extends Fragment {

    private Chart chart;

    public FragmentWorkPersonnel(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_personnel,
                container, false);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Webservice.getTaskListByWorkId(getActivity(), chart.getId(), new CallBack<ArrayList<Feature>>() {
            @Override
            public void onSuccess(ArrayList<Feature> featureList) {

            }

            @Override
            public void onError(ErrorMessage err) {
                Toast.makeText(getActivity(), "Error 102", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public Chart getChart() {

        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    // ------------------------------------------------------------------------------------
    public class onTaskListClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart)
                chart = (Chart) tag;
            else
                return;

            int type_id = chart.getType_id();
            Toast.makeText(getActivity(), type_id + "", Toast.LENGTH_LONG).show();
        }


    }
}
