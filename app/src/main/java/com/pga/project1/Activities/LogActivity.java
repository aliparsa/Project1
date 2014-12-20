package com.pga.project1.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.pga.project1.Adapters.ListViewObjectAdapter;
import com.pga.project1.Intefaces.ListViewItemINTERFACE;
import com.pga.project1.R;
import com.pga.project1.Utilities.LogHelper;

import java.util.ArrayList;

public class LogActivity extends Activity {

    private ListView lv;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        context = this;

        lv = (ListView) findViewById(R.id.lv_log);

        loadLogs();
    }

    private void loadLogs() {

        ArrayList<ListViewItemINTERFACE> itemList = new ArrayList<ListViewItemINTERFACE>();

        LogHelper db = new LogHelper(context);

        for (LogHelper.Log log : db.getAllLog(20)) {
            itemList.add(log);
        }


        ListViewObjectAdapter adapter = new ListViewObjectAdapter(context, itemList);
        lv.setAdapter(adapter);
    }

}
