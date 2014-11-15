package com.pga.project1.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.AnbarTransaction;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.Webservice;

import java.util.ArrayList;

public class AnbarActivity extends Activity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anbar);
        context = this;

        Anbar anbar = (Anbar) getIntent().getSerializableExtra("anbar");

        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<AnbarTransaction> anbarTransactions = db.getAnbarTransactions(anbar);

        // TODO  Ready to Show Here


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.anbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
