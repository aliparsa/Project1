package com.pga.project1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pga.project1.MainActivity;
import com.pga.project1.R;

public class FragmentSplash extends Fragment {

    //	ImageView ivIcon;
    TextView txt;
    ProgressBar prgbar;
    Button btn;
//	CustomContentListViewAdapter adapter;
//	boolean is_data_received = false;
//	
//	public boolean loaded=false;
//	
//	ListView lv_cat;
//	ListView lv_product;
//
//	String father;

    // array list to store items recived form activity
    // ArrayList<TreeMenuItem> itemlist;

//	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
//	public static final String ITEM_NAME = "itemName";

    // ------------------------------------------------------------------------------------
    public FragmentSplash() {

    }

    // ------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_splash, container,
                false);

        txt = (TextView) view.findViewById(R.id.textView1);
        prgbar = (ProgressBar) view.findViewById(R.id.progressBar1);
        btn = (Button) view.findViewById(R.id.button1);

        getActivity().getActionBar().hide();

        //CheckIntenetConnection();


        // Activate Action bar

        // Call Login Fraqgment
        ((MainActivity) getActivity()).ShowLoginFragment("Splash Fargment");

        return view;
    }
    // ------------------------------------------------------------------------------------
//	public void CheckIntenetConnection(){
//		txt.setText("بارگذاری...");
//		prgbar.setVisibility(ProgressBar.VISIBLE);
//		btn.setVisibility(Button.GONE);
//
//		ListView mDrawerList = new ListView(getActivity());
//
//		new AsynLoadByTag(getActivity(), "top", null).execute();
//		new AsynLoadByTag(getActivity(), "new", null).execute();
//		new AsynLoadByTag(getActivity(), "new", null).execute();
//
//		new AsynLoadMainGroups(getActivity(),new CallBackAsync() {
//
//			@Override
//			public void onSuccess(Object result) {
//				// TODO Auto-generated method stub
//				IsConnected(true,(CustomDrawerAdapter)result);
//
//			}
//
//			@Override
//			public void onError(String errMessage) {
//				// TODO Auto-generated method stub
//				IsConnected(false,null);
//
//			}
//		}).execute("");
//
//	}
    // ------------------------------------------------------------------------------------
//	public void IsConnected(boolean status,CustomDrawerAdapter adapter){
//		if (status==true){
//			//TODO Set Adapter to Drawer list view
//			((MainActivity) getActivity()).initApp(adapter);
//
//		}else{
//			//Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
//			 txt.setText("ارتباط مقدور نیست");
//			 btn.setText("تلاش مجدد");
//			 prgbar.setVisibility(ProgressBar.GONE);
//			 btn.setVisibility(Button.VISIBLE);
//			 btn.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View arg0) {
//					// TODO Auto-generated method stub
//					CheckIntenetConnection();
//
//				}
//			});
//
//
//
//		}
//	}
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------

}
