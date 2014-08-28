package com.pga.project1.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pga.project1.Adapters.ListViewCustomAdapter;
import com.pga.project1.DataModel.Chart;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Structures.AdapterInputType;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeViewActivity extends Activity {

    ListView lv;
    final Context context = this;
    Chart chart;


    Stack<ListViewCustomAdapter> stack = new Stack<ListViewCustomAdapter>();
    private ListViewCustomAdapter adapter;
    private PathMapManager pathManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);


        lv = (ListView) findViewById(R.id.lv_fragmentProjectTreeView_treeView);

        //TODO create function to handle listview loading

        pathManager = (PathMapManager) findViewById(R.id.pmm);

        if (savedInstanceState != null) {

            if (stack.size() > 0) {
                loadTreeFromStack();
            } else {
                loadProjects();
            }
        } else {
            loadProjects();
        }


    }

    private void loadProjects() {

        PathMapManager.clear();
        pathManager.refresh();

        Webservice.getProjects(this, new CallBack<ArrayList<Chart>>() {
            @Override
            public void onSuccess(ArrayList<Chart> result) {
                //TODO Create Adapter

                List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();

                for (Chart chart : result) {

                    itemList.add(new AdapterInputType(chart, "icon+title+subtitle", chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher), chart.getId()));

                }

                adapter = new ListViewCustomAdapter(context, R.layout.fragment_layout_project_tree_view, itemList);

                lv.setAdapter(adapter);

                // set on click listener
                lv.setOnItemClickListener(new onTreeViewClickListener());

            }

            @Override
            public void onError(String err) {
                //TODO Show Error
                if (err.equals("UNAUTHORIZED")) {

                    // clear token
                    Account.getInstant(context).clearToken();

                    // pass user to login page
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("reason", "UNAUTHORIZED");
                    startActivity(intent);
                }

                Toast.makeText(context, "Error 101 " + err, Toast.LENGTH_SHORT).show();
                //Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
                //toast.show();
            }


        });
    }

    private void loadTree(Chart chart) {

        stack.push(adapter);
        PathMapManager.push(chart);

        pathManager = (PathMapManager) findViewById(R.id.pmm);
        pathManager.refresh();

        Webservice.GetChildOfID(context, chart.getId(), new CallBack<ArrayList<Chart>>() {
            @Override
            public void onSuccess(ArrayList<Chart> result) {
                // Child Returned
                List<AdapterInputType> itemList = new ArrayList<AdapterInputType>();

                for (Chart chart : result) {
                    itemList.add(new AdapterInputType(chart, "icon+title+subtitle", chart.getName(), chart.getStart_date(), BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher), chart.getId()));
                }

                // create adapter from data
                adapter = new ListViewCustomAdapter(context, R.layout.fragment_layout_project_tree_view, itemList);

                // set adapter to lv
                lv.setAdapter(adapter);

                // set on click listener
                lv.setOnItemClickListener(new onTreeViewClickListener());
            }

            @Override
            public void onError(String err) {
                Toast.makeText(context, "Error 102", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void loadTreeFromStack() {

        lv.setAdapter(stack.pop());
        PathMapManager.pop("act tree view on loadTreeFromStack");
        pathManager.refresh();
    }


    //---------------------------------------

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stack.push(adapter);
    }


    //-----------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tree_view, menu);
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


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (stack.size() > 0) {
            loadTreeFromStack();

        } else {
            super.onBackPressed();
        }
    }


    public class onTreeViewClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Object tag = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).getTag();
            Chart chart;

            if (tag instanceof Chart)
                chart = (Chart) tag;
            else
                return;


            //String item_name = ((ListViewCustomAdapter.DrawerItemHolder) view.getTag()).title.getText().toString();


            Toast.makeText(context, chart.getType_id() + "", Toast.LENGTH_LONG).show();

            switch (chart.getType_id()) {
                case 3: // item is chart
                    // ((MainActivity) getActivity()).ShowTreeFragmnet(chart, "Project Tree View Fragment");
                    ((TreeViewActivity) context).loadTree(chart);
                    break;

                case 5: // item is chart
                    // ((MainActivity) getActivity()).ShowTreeFragmnet(chart, "Project Tree View Fragment");
                    ((TreeViewActivity) context).loadTree(chart);
                    break;

                case 6:       // item is work
                    //((MainActivity) getActivity()).ShowWorkFragment(chart, "Project Tree View Fragment", true);

                    Intent intent = new Intent(context, ActivityWork.class);
                    intent.putExtra("chart", chart);
                    startActivity(intent);

                    overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);


                    //pushing to Path Map
                    // PathMapManager.push(chart);

            }


        }


    }


}
