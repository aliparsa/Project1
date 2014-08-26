package com.pga.project1.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWorkTask extends Fragment {


    private Chart chart;

    public FragmentWorkTask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_work_task, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareTasks();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //

        MenuItem ac_pick_personnel = menu.findItem(R.id.ac_pick_personnel);

        if (ac_pick_personnel != null) {
            ac_pick_personnel.setVisible(true);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.ac_pick_personnel) {
            pickPersonnel();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1212: {
                if (resultCode == Activity.RESULT_OK) {
                    Personnel personnel = (Personnel) data.getSerializableExtra("personnel");
                    Toast.makeText(getActivity(), "Personel id is > " + personnel.getFirst_name(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No personel Selected", Toast.LENGTH_SHORT).show();

                }
                break;
            }
        }
    }


    public void pickPersonnel() {

        Intent intent = new Intent(getActivity(), PersonelPickerActivity.class);
        startActivityForResult(intent, 1212);
    }

    private void prepareTasks() {

        final ListView lv = (ListView) getView().findViewById(R.id.lv_fragmentWork_task_taskviewr);


        Webservice.getTaskListByWorkId(getActivity(), chart.getId(), new CallBack<ArrayList<Chart>>() {
            @Override
            public void onSuccess(ArrayList<Chart> taskList) {

                List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();
                for (Chart chart : taskList) {
                    itemList.add(new AdapterInputType(chart, "icon+title+subtitle", chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher)));
                }

                // create adapter from data
                ListViewCustomAdapter adapter =
                        new ListViewCustomAdapter(getActivity(), R.layout.fragment_layout_project_tree_view, itemList);

                // set adapter to lv
                lv.setAdapter(adapter);

                // set on click listener
                lv.setOnItemClickListener(new onTaskListClickListener());

            }

            @Override
            public void onError(ErrorMessage err) {
                Toast.makeText(getActivity(), "Error 102", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public class onTaskListClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart) {
                chart = (Chart) tag;
                //setActiveTab(FragmentWork.TabPersonnel);
                //((MainActivity) getActivity()).ShowTaskPageFragment(chart);


                Intent intent = new Intent(getActivity(), ActivityTaskPage.class);
                intent.putExtra("chart", chart);
                startActivity(intent);

            } else
                return;

            Toast.makeText(getActivity(), chart.getType_id() + "", Toast.LENGTH_LONG).show();
        }


    }
}
