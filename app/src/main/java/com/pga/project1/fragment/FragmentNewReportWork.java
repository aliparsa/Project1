package com.pga.project1.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;

/**
 * Created by ashkan on 8/18/2014.
 */
public class FragmentNewReportWork extends Fragment {


    //{Constants-----------------------------------------------------

    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    EditText report;
    EditText percent;
    Button btnSave;
    ImageButton timePicker;
    TextView pickedDate;
    private Chart chart;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        report = (EditText) view.findViewById(R.id.edittext_newReport_reportText);
        percent = (EditText) view.findViewById(R.id.edittext_newReport_percent);
        btnSave = (Button) view.findViewById(R.id.btn_newReport_Save);
        timePicker = (ImageButton) view.findViewById(R.id.btn_newReport_TimePicker);
        pickedDate = (TextView) view.findViewById(R.id.txt_newReport_PickedDate);

        // set pre percent value to edit text
        percent.setText(chart.getPercent() + "");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Saving", Toast.LENGTH_SHORT).show();
            }
        });

        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp = new ViewDateTimePickerPersian(getActivity());

                new AlertDialog.Builder(getActivity())
                        .setTitle("انتخاب زمان و تاریخ")
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delet

                                        pickedDate.setText(dp.getYear() + "/" + dp.getMonth() + "/" + dp.getDay());


                                    }
                                }
                        )
                        .show();

            }
        });

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

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
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
