package com.pga.project1.fragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Structures.Chart;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;
import java.util.List;

public class FragmentProjectTreeView extends Fragment {

    ListView lv;

    // ------------------------------------------------------------------------------------
    public FragmentProjectTreeView() {

    }

    // ------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getActionBar().show();

        View view = inflater.inflate(R.layout.fragment_layout_project_tree_view, container,
                false);

        lv = (ListView) view.findViewById(R.id.lv_fragmentProjectTreeView_treeView);

        final FragmentProjectTreeView self = this;


        Webservice webservice = new Webservice();
        webservice.getProjects(new CallBack<Chart>() {
            @Override
            public void onSuccess(ArrayList<Chart> result) {
                //TODO Create Adapter

                List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();

                for (Chart chart : result) {

                    itemList.add(new AdapterInputType("icon+title+subtitle", chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher)));

                }

                ListViewCustomAdapter adapter =
                        new ListViewCustomAdapter(self.getActivity(), R.layout.fragment_layout_project_tree_view, itemList);

                lv.setAdapter(adapter);
            }


            @Override
            public void onError(String errorMessage) {
                //TODO Show Error

                Toast toast = Toast.makeText(self.getActivity(), errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        return view;
    }
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------

}
