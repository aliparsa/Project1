package com.pga.project1.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.Chart;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;

public class FragmentProjectTreeView extends Fragment {



	// ------------------------------------------------------------------------------------
	public FragmentProjectTreeView() {
		
	}

	// ------------------------------------------------------------------------------------
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_project_tree_view, container,
				false);

        Webservice webservice = new Webservice();
        webservice.getProjects(new CallBack<Chart>() {
            @Override
            public void onSuccess(ArrayList<Chart> result) {
            //TODO Create Adapter
            }

            @Override
            public void onError(String errorMessage) {
            //TODO Show Error
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
