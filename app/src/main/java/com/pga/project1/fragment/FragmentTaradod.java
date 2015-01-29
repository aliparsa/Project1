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

import com.pga.project1.Activities.FastProjectManagmentActivity;
import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.PersianCalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTaradod extends Fragment {


    private ListView lvTaradod;

    public FragmentTaradod() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_taradod, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvTaradod = (ListView) view.findViewById(R.id.lv_taradodListView);


    }

    @Override
    public void onStart() {
        super.onStart();
        loadTaradod();
    }


    public void loadTaradod() {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        int projectID = ((FastProjectManagmentActivity) getActivity()).chart.getId();
        List<Taradod> taradods = db.getAllTaradodsWithPersonnel(projectID + "");

        List<AdapterInputType> items = new ArrayList<AdapterInputType>();

        for (Taradod taradod : taradods) {

            AdapterInputType type = new AdapterInputType(taradod, ListViewCustomAdapter.TRADOD_ITEM, "", 0);
            items.add(type);
        }


        ListViewCustomAdapter adapter = new ListViewCustomAdapter(this.getActivity(), 0, items);

        lvTaradod.setAdapter(adapter);

        lvTaradod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Taradod taradod = ((Taradod) ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag());

                new AlertDialog.Builder(getActivity())
                        .setTitle(taradod.getPersonnel().getFullName())
                        .setMessage((taradod.getInOut().equals("in") ? "ورود" : "خروج") + "\n\nتاریخ :  " + taradod.getPersianDate() + "\n\nتوضیحات :  " + taradod.getDescription())
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
