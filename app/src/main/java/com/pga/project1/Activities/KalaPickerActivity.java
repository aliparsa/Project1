package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;

public class KalaPickerActivity extends Activity {
    private SearchView searchView;
    private ListView listView;
    private ImageView refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kala_picker);

        prepareActionbar();
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        refreshButton = (ImageView) customActionBar.findViewById(R.id.ac_reload);

        refreshButton.setImageResource(R.drawable.ac_refresh);


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loadPersonalsFromWeb("");
            }
        });


        // change hint color
        int searchTextViewId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchTextView = (TextView) searchView.findViewById(searchTextViewId);
        searchTextView.setTextColor(getResources().getColor(R.color.actionbar_title_color));
        searchTextView.setHintTextColor(getResources().getColor(R.color.actionbar_search_hint));
        //searchTextView.setTextSize(R.dimen.actionbar_search_font_size);


    }
}
