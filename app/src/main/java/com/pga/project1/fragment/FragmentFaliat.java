package com.pga.project1.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Faliat;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
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

        DatabaseHelper db = new DatabaseHelper(this.getActivity());

        List<Faliat> faliats = db.getAllFaliatsWithPersonnelAndWork();

        List<AdapterInputType> items = new ArrayList<AdapterInputType>();

        for (Faliat faliat : faliats){

            AdapterInputType type = new AdapterInputType(faliat, ListViewCustomAdapter.FALIAT_ITEM,"",0);
            items.add(type);
        }



        ListViewCustomAdapter adapter = new ListViewCustomAdapter(this.getActivity(), 0, items);

   //     lvFaliats.setAdapter(adapter);
        TextView textView = new TextView(getActivity());
                textView.setText("ali ali ali ali ali ali lai laakff fd dfg g g ");
        lvFaliats.addHeaderView(textView);
    }
}
