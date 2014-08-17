package com.pga.project1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/17/2014.
 */
public class FragmentPersonnelSearch extends Fragment {
    private SearchView searchView;
    private ListView listView;
    private ListViewCustomAdapter adapter;


    //{Constants-----------------------------------------------------

    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

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

        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new onPersonnelSearchListener());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).hideTabs();
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

                adapter.itemList = listItem;
                adapter.notifyDataSetChanged();
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

            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    }

    //-----------------------------------------------------static callback classes}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

    //{Factory function--------------------------------------------------

    //---------------------------------------------------Factory function}
}
