package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewObjectAdapter;
import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.AnbarTransaction;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Helpers.SyncHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.ListViewItemINTERFACE;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;

public class AnbarActivity extends Activity {

    private Context context;
    private ImageView inButton;
    private ImageView outButton;
    private PathMapManager pathManager;
    private ListView lv;
    private Anbar anbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anbar);
        context = this;

        anbar = (Anbar) getIntent().getSerializableExtra("anbar");

        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<AnbarTransaction> anbarTransactions = db.getAnbarTransactions(anbar);


        lv = (ListView) findViewById(R.id.lv_anbarTrans);
//        lv.setAdapter(ListViewAdapterHandler.getLoadingAdapter(this));

        context = this;

        //TODO create function to handle listview loading

        pathManager = (PathMapManager) findViewById(R.id.pmm);
        PathMapManager.push(new PathObject(anbar.getName()));
        pathManager.refresh();


        loadTransactions();

        prepareActionBar();

    }

    private void prepareActionBar() {

        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_back, null);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(customActionBar);

        TextView title = (TextView) customActionBar.findViewById(R.id.ac_title);
        FontHelper.SetFont(this, Fonts.MAIN_FONT, title, Typeface.BOLD);

        title.setText("لیست ورود ها و خروج ها");

        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        inButton = (ImageView) customActionBar.findViewById(R.id.ac_action1);
        outButton = (ImageView) customActionBar.findViewById(R.id.ac_action2);

        inButton.setImageResource(R.drawable.ic_action_enter);
        inButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//TODO enter item activity
            }
        });

        outButton.setImageResource(R.drawable.ic_action_exit);
        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//TODO exit item activity
            }
        });

    }

    void loadTransactions(){

        pathManager.refresh();

        ArrayList<ListViewItemINTERFACE> itemList = new ArrayList<ListViewItemINTERFACE>();

        DatabaseHelper db = new DatabaseHelper(context);

        ArrayList<AnbarTransaction> transes = db.getAnbarTransactions(anbar);

        for (AnbarTransaction trans:transes) {
            itemList.add(anbar);
        }


        // adapter = new ListViewObjectAdapter(context, itemList);
        //lv.setAdapter(adapter);

        // lv.setOnItemClickListener(new AnbarPickerActivity.onAnbarClickListener());
    }
}
