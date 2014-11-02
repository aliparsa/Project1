package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.ListViewAdapterHandler;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;

public class PersonelPickerActivity extends Activity {

    private SearchView searchView;
    private ListView listView;
    private ListViewCustomAdapter adapter;
    private CallBack<Personnel> callback;
    Context context;
    DatabaseHelper db;
    private ImageView refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        DatabaseHelper db = new DatabaseHelper(context);

        PathMapManager.push(new PathObject("انتخاب پرسنل"));

        setContentView(R.layout.activity_personel_picker);


        context = this;

        //searchView = (SearchView) findViewById(R.id.srchv_searchPersonnel_searchName);
        listView = (ListView) findViewById(R.id.lv_searchPersonnel_results);
        listView.setAdapter(ListViewAdapterHandler.getLoadingAdapter(this));

        adapter = new ListViewCustomAdapter(this,
                R.layout.drawer_item, new ArrayList<AdapterInputType>());


        ArrayList<Personnel> personnels = db.getAllPersonnels();

        // listView.setAdapter(adapter);

        if (personnels.size() == 0) {
            loadPersonalsFromWeb("");
        } else {
            loadPersonalsFromLocal("");
        }


        prepareActionbar();
    }

    private void loadPersonalsFromLocal(String s) {
        ArrayList<AdapterInputType> listItem = new ArrayList<AdapterInputType>();
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<Personnel> personnels = db.getAllPersonnels();
        for (Personnel person : personnels) {

            AdapterInputType adapterInputType = new AdapterInputType(
                    person, ListViewCustomAdapter.PERSONNEL_ITEM,
                    person.getFirst_name() + " " + person.getLast_name(),
                    person.getPhone_number(), person.getGroupsString(), person.getPersonnel_image()
            );

            listItem.add(adapterInputType);
        }

        adapter = new ListViewCustomAdapter(context,
                R.layout.drawer_item, listItem);

        listView.setAdapter(ListViewAdapterHandler.checkAdapterForNoItem(adapter));
        listView.setOnItemClickListener(new onPersonnelClickListener());

    }


    private void prepareActionbar() {

        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_search, null);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(customActionBar);

        ImageView icon = (ImageView) customActionBar.findViewById(R.id.ac_icon);
        TextView title = (TextView) customActionBar.findViewById(R.id.ac_title);
        title.setVisibility(View.GONE);

        FontHelper.SetFont(this, Fonts.MAIN_FONT, title, Typeface.BOLD);

        //ImageView back = (ImageView) customActionBar.findViewById(R.id.ac_back);
        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        searchView = (SearchView) customActionBar.findViewById(R.id.ac_search);
        searchView.setOnQueryTextListener(new onPersonnelSearchListener());


        refreshButton = (ImageView) customActionBar.findViewById(R.id.ac_reload);

        //removeTaskButton.setText("حذف وظیفه");
        refreshButton.setImageResource(R.drawable.ac_refresh);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPersonalsFromWeb("");
            }
        });


       // int searchButtonId = searchView.getContext().getResources().getIdentifier("android:id/search_button", null, null);
        //Button searchIcon = (Button) searchView.findViewById(searchButtonId);
       // searchIcon.setdraw//setImageResource(R.drawable.ic_search);

        //int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
       // searchView.findViewById(searchPlateId).setBackgroundResource(R.drawable.textfield_search_selected);

        //int voiceSearchPlateId = searchView.getContext().getResources().getIdentifier("android:id/submit_area", null, null);
        //searchView.findViewById(voiceSearchPlateId).setBackgroundResource(R.drawable.textfield_search_right_selected);

        // change hint color
        int searchTextViewId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchTextView = (TextView) searchView.findViewById(searchTextViewId);
        searchTextView.setTextColor(getResources().getColor(R.color.actionbar_title_color));
        searchTextView.setHintTextColor(getResources().getColor(R.color.actionbar_search_hint));
        //searchTextView.setTextSize(R.dimen.actionbar_search_font_size);

    }


    protected void loadPersonalsFromWeb(final String str) {

        db = new DatabaseHelper(context);

        Webservice.searchPersonnel(this, str, new CallBack<ArrayList<Personnel>>() {
            @Override
            public void onSuccess(ArrayList<Personnel> result) {

                if (result != null) {
                    db.emptyPersonnelTable();
                }

                ArrayList<AdapterInputType> listItem = new ArrayList<AdapterInputType>();

                for (Personnel person : result) {

                    // insert to db
                    if(str == null || str.equals(""))
                        db.insertPersonnel(person);

                    AdapterInputType adapterInputType = new AdapterInputType(
                            person, ListViewCustomAdapter.PERSONNEL_ITEM,
                            person.getFirst_name() + " " + person.getLast_name(),
                            person.getPhone_number(), person.getGroupsString(), person.getPersonnel_image()
                    );

                    listItem.add(adapterInputType);
                }

                adapter = new ListViewCustomAdapter(context,
                        R.layout.drawer_item, listItem);

                listView.setAdapter(ListViewAdapterHandler.checkAdapterForNoItem(adapter));
                listView.setOnItemClickListener(new onPersonnelClickListener());

            }

            @Override
            public void onError(String err) {

            }
        });
    }


    private class onPersonnelSearchListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String s) {
            loadPersonalsFromWeb(s);
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
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);


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
        overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);

    }
}
