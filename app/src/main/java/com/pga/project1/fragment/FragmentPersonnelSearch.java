package com.pga.project1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Structures.ErrorPlaceHolder;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/17/2014.
 */
public class FragmentPersonnelSearch extends Fragment {



    //{Constants-----------------------------------------------------

    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------
    private SearchView searchView;
    private ListView listView;
    private ListViewCustomAdapter adapter;
    private CallBack<Personnel> callback;
    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------
    public FragmentPersonnelSearch(){

    }
    //-----------------------------------------------------Constructor}


    //{override functions---------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_personnel,
                container, false);

        searchView = (SearchView) view.findViewById(R.id.srchv_searchPersonnel_searchName);
        listView = (ListView) view.findViewById(R.id.lv_searchPersonnel_results);

        adapter = new ListViewCustomAdapter(this.getActivity(),
                R.layout.drawer_item, new ArrayList<AdapterInputType>());

        // listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new onPersonnelSearchListener());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).hideTabs();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        callback.onError(new ErrorMessage(ErrorPlaceHolder.err2));
    }

    //-----------------------------------------------------override functions}

    //{Functions-----------------------------------------------------

    protected void loadPersonals(final String str){

        Webservice.searchPersonnel(this.getActivity(), str , new CallBack<ArrayList<Personnel>>() {
            @Override
            public void onSuccess(ArrayList<Personnel> result) {

                ArrayList<AdapterInputType> listItem = new ArrayList<AdapterInputType>();

                for(Personnel person : result){

                    AdapterInputType adapterInputType = new AdapterInputType(
                           person, ListViewCustomAdapter.PERSONNEL_ITEM,
                           person.getFirst_name() + " " + person.getLast_name(),
                            person.getPhone_number(), person.getGroupsString(),  ""
                    );

                    listItem.add(adapterInputType);
                }

                adapter = new ListViewCustomAdapter(getActivity(),
                        R.layout.drawer_item, listItem);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new onPersonnelClickListener());

            }

            @Override
            public void onError(ErrorMessage err) {

            }
        });
    }

    //-----------------------------------------------------Functions}

    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{static callback classes-----------------------------------------------------
    private class onPersonnelSearchListener implements SearchView.OnQueryTextListener {


        @Override
        public boolean onQueryTextSubmit(String s) {
            loadPersonals(s);
            searchView.clearFocus();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    }

    //-----------------------------------------------------static callback classes}

    //{Setter getters-----------------------------------------------------

    public void setCallback(CallBack<Personnel> callback) {
        this.callback = callback;
    }

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    //---------------------------------------------------Factory function}

    public class onPersonnelClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Personnel personnel;

            if (tag instanceof Personnel)
                personnel = (Personnel) tag;
            else
                return;

            //Toast.makeText(getActivity(), personnel.getFirst_name() + " :) :)", Toast.LENGTH_LONG).show();
            callback.onSuccess(personnel);
        }


    }
}
