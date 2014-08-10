package com.pga.project1.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.pga.project1.Structures.AshkanAdapter;
import com.pga.project1.Structures.Chart;
import com.pga.project1.Structures.ListViewRowItem;
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

                //List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();

                Bitmap image = BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_launcher);

                List<ListViewRowItem> itemList = new ArrayList<ListViewRowItem>();

                itemList.add(new ListViewRowItem("Ashkan", R.id.txt_listViewRowItem2_txt1, image, R.id.imgv_listViewRowItem2_img1,  R.layout.listview_rowitem2));

                itemList.add(new ListViewRowItem("shayan", R.id.txt_listViewRowItem2_txt1, image, R.id.imgv_listViewRowItem2_img1,  R.layout.listview_rowitem2));


                for (Chart chart : result){

                    //itemList.add(new AdapterInputType("icon+title+subtitle",chart.getName(), chart.getStart_date(), null ));
                    itemList.add(new ListViewRowItem(chart.getName(), R.id.txt_listViewRowItem1_txt1,  R.layout.listview_rowitem1));
                }

               // ProjectTreeViewCustomAdapter adapter =
                //        new ProjectTreeViewCustomAdapter(self.getActivity(), R.layout.fragment_layout_project_tree_view, itemList );

                itemList.add(new ListViewRowItem("Ali", R.id.txt_listViewRowItem2_txt1,  R.layout.listview_rowitem2));


                for (int i = 0; i<20; i++){

                    //itemList.add(new AdapterInputType("icon+title+subtitle",chart.getName(), chart.getStart_date(), null ));
                    itemList.add(new ListViewRowItem(i+"", R.id.txt_listViewRowItem1_txt1,  R.layout.listview_rowitem1));

                    if(i % 4 == 0)
                        itemList.add(new ListViewRowItem(i+" "+i, R.id.txt_listViewRowItem2_txt1,  R.layout.listview_rowitem2));

                }

                AshkanAdapter adapter = new AshkanAdapter(self.getActivity(), 0, (ArrayList<ListViewRowItem>) itemList);

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
