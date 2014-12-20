package com.pga.project1.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pga.project1.Activities.FastProjectManagmentActivity;
import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFaliat extends Fragment {


    private ListView lvFaliats;

    public FragmentFaliat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faliat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFaliats = (ListView) view.findViewById(R.id.lv_faliatListView);


    }

    @Override
    public void onStart() {
        super.onStart();
        loadFaaliat();
    }

    public void loadFaaliat() {

        DatabaseHelper db = new DatabaseHelper(getActivity());
        int projectID = ((FastProjectManagmentActivity) getActivity()).chart.getId();

        List<Faliat> faliats = db.getAllFaliatsWithPersonnelAndWork(projectID + "");

        List<AdapterInputType> items = new ArrayList<AdapterInputType>();

        for (Faliat faliat : faliats) {

            AdapterInputType type = new AdapterInputType(faliat, ListViewCustomAdapter.FALIAT_ITEM, "", 0);
            items.add(type);
        }

        ListViewCustomAdapter adapter = new ListViewCustomAdapter(this.getActivity(), 0, items);
        lvFaliats.setAdapter(adapter);


        lvFaliats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Faliat faliat = ((Faliat) ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag());

                new AlertDialog.Builder(getActivity())
                        .setTitle(faliat.getPersonnel().getFullName())
                        .setMessage("عنوان کار :  " + faliat.getWork().getName() + " ( " + faliat.getWork().getType() + " ) " + "\n\n تاریخ  :  " + faliat.getOnlyPersianDate() + "\n\n میزان کار :  " + faliat.getAmount() + "\n\nتوضیحات :  " + faliat.getDescription())
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .show();
            }
        });
    }
}
