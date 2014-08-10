package com.pga.project1.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.Adapters.ProjectTreeViewCustomAdapter;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Structures.Chart;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;
import java.util.List;

public class FragmentProjectTreeView extends Fragment {

    ListView lv ;

	// ------------------------------------------------------------------------------------
	public FragmentProjectTreeView() {
		
	}

	// ------------------------------------------------------------------------------------
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

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

                for (Chart chart : result){

                    itemList.add(new AdapterInputType("icon+title+subtitle",chart.getName(), chart.getStart_date(), null ));

                }

                ProjectTreeViewCustomAdapter adapter =
                        new ProjectTreeViewCustomAdapter(self.getActivity(), R.layout.fragment_layout_project_tree_view, itemList );

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
