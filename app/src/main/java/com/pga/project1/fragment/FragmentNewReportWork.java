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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Utilities.PersianCalendar;
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
    Button percent;
    Button btnSave;
    Button timePicker;
    TextView pickedDate;
    private Chart chart;


    //filled information
    private PersianCalendar selectedDateTime;
    private int selectedPercent;
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

        report = (EditText) view.findViewById(R.id.edittext_fragmentNewReportWork_reportText);
        percent = (Button) view.findViewById(R.id.btn_fragmentNewReportWork_selectPercent);
        timePicker = (Button) view.findViewById(R.id.btn_fragmentNewReportWork_selectDate);
        btnSave = (Button) view.findViewById(R.id.btn_newReport_Save);


        // set pre percent value to edit text
        selectedPercent = chart.getPercent();
        percent.setText(selectedPercent + " %");
        timePicker.setText(new PersianCalendar().getIranianDateTime());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Saving", Toast.LENGTH_SHORT).show();
            }
        });

        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedDateTime == null)
                    dp = new ViewDateTimePickerPersian(getActivity());
                else
                    dp = new ViewDateTimePickerPersian(getActivity(), selectedDateTime);

                new AlertDialog.Builder(getActivity())
                        .setTitle("انتخاب زمان و تاریخ")
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedDateTime = dp.getDate();
                                        timePicker.setText(selectedDateTime.getIranianDateTime());
                                    }
                                }
                        )
                        .show();
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final NumberPicker percentPicker = new NumberPicker(getActivity());
                percentPicker.setMaxValue(100);
                percentPicker.setMinValue(1);

                percentPicker.setValue(selectedPercent);

                new AlertDialog.Builder(getActivity())
                        .setTitle("درصد پیشرفت")
                        .setView(percentPicker)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedPercent = percentPicker.getValue();
                                        percent.setText(selectedPercent + "%");
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
