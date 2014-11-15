package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pga.project1.Adapters.ListViewObjectAdapter;
import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.ListViewItemINTERFACE;
import com.pga.project1.R;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.ListViewAdapterHandler;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;

public class AnbarPickerActivity extends Activity {

    private ImageView inButton;
    private ImageView outButton;
    private ListView lv;
    private PathMapManager pathManager;
    private ArrayList<Anbar> anbars;
    private Context context;
    private ListViewObjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anbar_picker);

        lv = (ListView) findViewById(R.id.lv_anbarspicker);
        lv.setAdapter(ListViewAdapterHandler.getLoadingAdapter(this));
        
        context = this;

        //TODO create function to handle listview loading

        pathManager = (PathMapManager) findViewById(R.id.pmm);
        pathManager.clear();
        PathMapManager.push(new PathObject("انبار ها"));
        pathManager.refresh();

        prepareActionBar();


        // load projects
        final DatabaseHelper db = new DatabaseHelper(this);
        anbars = db.getMyAnbars();

        if (anbars.size() < 1)
            Webservice.getAnbar(this, new CallBack<ArrayList<Anbar>>() {

                @Override
                public void onSuccess(ArrayList<Anbar> anbars) {
                    db.emptyAnbarTable();
                    for (Anbar anbar : anbars) {
                        db.insertAnbar(anbar);
                    }
                    loadAnbars();

                }

                @Override
                public void onError(String errorMessage) {
                    if (errorMessage.equals("UNAUTHORIZED")) {

                        // clear token
                        Account.getInstant(context).clearToken();

                        // pass user to login page
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.putExtra("reason", "UNAUTHORIZED");
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                    }
                }
            });
        else
            loadAnbars();
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

        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        inButton = (ImageView) customActionBar.findViewById(R.id.ac_action2);
        outButton = (ImageView) customActionBar.findViewById(R.id.ac_action1);

        inButton.setImageResource(R.drawable.ic_camera);
        inButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //saveButton.setText("ذخیره");
        outButton.setImageResource(R.drawable.ic_save);
        // saveButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));
        final Context context = this;
        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });
    }



    private void loadAnbars() {

        pathManager.refresh();


        ArrayList<ListViewItemINTERFACE> itemList = new ArrayList<ListViewItemINTERFACE>();

        DatabaseHelper db = new DatabaseHelper(context);

        anbars = db.getMyAnbars();

        for (Anbar anbar:anbars) {

            itemList.add(anbar);

        }

        adapter = new ListViewObjectAdapter(context, itemList);
        lv.setAdapter(adapter);

    }

}
