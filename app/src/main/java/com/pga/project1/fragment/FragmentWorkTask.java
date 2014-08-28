package com.pga.project1.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pga.project1.Activities.ActivityTaskPage;
import com.pga.project1.Activities.PersonelPickerActivity;
import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.DataModel.ServerResponse;
import com.pga.project1.DataModel.Task;
import com.pga.project1.DataModel.WorkUnit;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ListViewAdapterHandler;
import com.pga.project1.Utilities.PersianCalendar;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWorkTask extends Fragment {


    private Chart chart;
    private PersianCalendar selectedStartDateTime;
    private PersianCalendar selectedEndDateTime;
    Spinner spinner_noe_kar;

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
                    addPersonnelToWork(personnel);
//                    Toast.makeText(getActivity(), "Personel id is > " + personnel.getFirst_name(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No personel Selected", Toast.LENGTH_SHORT).show();

                }
                break;
            }
        }
    }

    private void addPersonnelToWork(final Personnel personnel) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_personel_to_work, null);

        final EditText task_name = (EditText) dialogView.findViewById(R.id.editText_task_name);
        final EditText task_price = (EditText) dialogView.findViewById(R.id.editText_task_price);
        final Button button_start_date = (Button) dialogView.findViewById(R.id.button_start_date);
        final Button button_end_date = (Button) dialogView.findViewById(R.id.button_end_date);

        final EditText editText_kol_kar = (EditText) dialogView.findViewById(R.id.editText_kol_kar);
        final EditText editText_tozihat = (EditText) dialogView.findViewById(R.id.editText_tozihat);


        spinner_noe_kar = (Spinner) dialogView.findViewById(R.id.spinner_vahed_kar);


        Webservice.getWorkUnitList(getActivity(), new CallBack<ArrayList<WorkUnit>>() {
            @Override
            public void onSuccess(ArrayList<WorkUnit> result) {

                String[] workunit = new String[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    workunit[i] = result.get(i).toString();
                }

                spinner_noe_kar.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, workunit));

            }

            @Override
            public void onError(String err) {
                //TODO KHATA DAR ERTEBAT INTERNETI
            }
        });


        selectedStartDateTime = new PersianCalendar();
        selectedEndDateTime = new PersianCalendar();

        button_start_date.setText(selectedStartDateTime.getIranianDateTime());
        button_end_date.setText(selectedEndDateTime.getIranianDateTime());

        button_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedStartDateTime == null)
                    dp = new ViewDateTimePickerPersian(getActivity());
                else
                    dp = new ViewDateTimePickerPersian(getActivity(), selectedStartDateTime);

                new AlertDialog.Builder(getActivity())
                        .setTitle("انتخاب زمان و تاریخ")
                        .setCancelable(false)
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedStartDateTime = dp.getDate();
                                        button_start_date.setText(selectedStartDateTime.getIranianDateTime());
                                    }
                                }
                        )
                        .show();
            }
        });
//-------------------------------------------------------------
        button_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedEndDateTime == null)
                    dp = new ViewDateTimePickerPersian(getActivity());
                else
                    dp = new ViewDateTimePickerPersian(getActivity(), selectedEndDateTime);

                new AlertDialog.Builder(getActivity())
                        .setTitle("انتخاب زمان و تاریخ")
                        .setCancelable(false)
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedEndDateTime = dp.getDate();
                                        button_end_date.setText(selectedEndDateTime.getIranianDateTime());
                                    }
                                }
                        )
                        .show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(personnel.getFirst_name() + " " + personnel.getLast_name() + "   >   " + chart.getName());
        builder.setCancelable(false);
        builder.setView(dialogView);
        builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO Store Report

                Task task = new Task(task_name.getText().toString(),
                        task_price.getText().toString(),
                        selectedStartDateTime.getIranianDate(),
                        selectedEndDateTime.getIranianDate(),
                        editText_kol_kar.getText().toString(),
                        spinner_noe_kar.getSelectedItemId() + "",
                        editText_tozihat.getText().toString()
                );

                final ProgressDialog pg = new ProgressDialog(getActivity());
                pg.setMessage("در حال ارسال");
                pg.show();

                Webservice.addPersonnelToWork(getActivity(), personnel.getId(), chart.getId(), task, new CallBack<ServerResponse>() {
                    @Override
                    public void onSuccess(ServerResponse result) {
                        pg.setMessage("در حال بروزرسانی اطلاعات");
                        if (result.getResult().equals("ok")) {
                            Toast.makeText(getActivity(), "عملیات انجام شد", Toast.LENGTH_SHORT).show();
                            prepareTasks();
                        } else {
                            Toast.makeText(getActivity(), "عملیات انجام نشد", Toast.LENGTH_SHORT).show();
                        }
                        pg.dismiss();
                    }

                    @Override
                    public void onError(String err) {
                        pg.dismiss();
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

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
                lv.setAdapter(ListViewAdapterHandler.checkAdapterForNoItem(adapter));

                // set on click listener
                lv.setOnItemClickListener(new onTaskListClickListener());

            }

            @Override
            public void onError(String err) {
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
