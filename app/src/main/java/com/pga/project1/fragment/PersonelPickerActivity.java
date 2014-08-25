package com.pga.project1.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;

public class PersonelPickerActivity extends Activity {

    private SearchView searchView;
    private ListView listView;
    private ListViewCustomAdapter adapter;
    private CallBack<Personnel> callback;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        PathMapManager.push(new PathObject("انتخاب پرسنل"));

        setContentView(R.layout.activity_personel_picker);


        context = this;

        searchView = (SearchView) findViewById(R.id.srchv_searchPersonnel_searchName);
        listView = (ListView) findViewById(R.id.lv_searchPersonnel_results);

        adapter = new ListViewCustomAdapter(this,
                R.layout.drawer_item, new ArrayList<AdapterInputType>());


        loadPersonals("");

        // listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new onPersonnelSearchListener());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.personel_picker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.ac_back_in_personnel_picker) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void loadPersonals(final String str) {

        Webservice.searchPersonnel(this, str, new CallBack<ArrayList<Personnel>>() {
            @Override
            public void onSuccess(ArrayList<Personnel> result) {

                ArrayList<AdapterInputType> listItem = new ArrayList<AdapterInputType>();

                for (Personnel person : result) {

                    AdapterInputType adapterInputType = new AdapterInputType(
                            person, ListViewCustomAdapter.PERSONNEL_ITEM,
                            person.getFirst_name() + " " + person.getLast_name(),
                            person.getPhone_number(), person.getGroupsString(), ""
                    );

                    listItem.add(adapterInputType);
                }

                adapter = new ListViewCustomAdapter(context,
                        R.layout.drawer_item, listItem);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new onPersonnelClickListener());

            }

            @Override
            public void onError(ErrorMessage err) {

            }
        });
    }


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

    public class onPersonnelClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Personnel personnel;

            if (tag instanceof Personnel) {
                personnel = (Personnel) tag;

                Intent returnIntent = new Intent();
                returnIntent.putExtra("personnel", personnel);
                setResult(RESULT_OK, returnIntent);
                finish();

            } else
                return;

            //Toast.makeText(getActivity(), personnel.getFirst_name() + " :) :)", Toast.LENGTH_LONG).show();
            //searchView.clearFocus();
            // callback.onSuccess(personnel);

        }


    }

    @Override
    protected void onDestroy() {

        PathMapManager.pop("personnel picker onBackPressed");

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();

    }
}
