package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.pga.project1.Adapters.ListViewObjectAdapter;
import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Product;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Helpers.SyncHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.ListViewItemINTERFACE;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;

public class KalaPickerActivity extends Activity {
    private SearchView searchView;
    private ListView listView;
    private ImageView refreshButton;
    private ListView lv;
    private PathMapManager pm;
    private Context context;
    private ListViewObjectAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kala_picker);

        context = this;
        lv = (ListView) findViewById(R.id.lv_kalapicker);

        pm = (PathMapManager) findViewById(R.id.pmm);
        pm.push(new PathObject("انتخاب کالا"));
        pm.refresh();

        loadKala(null);

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
                loadKala(s);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                loadKala(s);
                return true;
            }
        });


        refreshButton = (ImageView) customActionBar.findViewById(R.id.ac_reload);

        refreshButton.setImageResource(R.drawable.ac_refresh);


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loadPersonalsFromWeb("");
                SyncHelper.syncProduct(context, new CallBack() {
                    @Override
                    public void onSuccess(Object result) {

                        searchView.setQuery("", false);
                        loadKala(null);
                    }

                    @Override
                    public void onError(String errorMessage) {

                    }
                });
            }
        });


        // change hint color
        int searchTextViewId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchTextView = (TextView) searchView.findViewById(searchTextViewId);
        searchTextView.setTextColor(getResources().getColor(R.color.actionbar_title_color));
        searchTextView.setHintTextColor(getResources().getColor(R.color.actionbar_search_hint));
        //searchTextView.setTextSize(R.dimen.actionbar_search_font_size);


    }

    private void loadKala(String s) {

        pm.refresh();

        ArrayList<ListViewItemINTERFACE> itemList = new ArrayList<ListViewItemINTERFACE>();

        DatabaseHelper db = new DatabaseHelper(context);

        ArrayList<Product> kalas = db.getProducts(s);

        for (Product kala:kalas) {
            itemList.add(kala);
        }

        adapter = new ListViewObjectAdapter(context, itemList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO item selected
                Product product = ((Product.Holder) view.getTag()).getProduct();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("product", product);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PathMapManager.pop("destroy kala picker");
    }
}
