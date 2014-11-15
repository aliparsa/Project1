package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.Taradod;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Helpers.SyncHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.CallBackFunction;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.HandleError;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.fragment.NavigationDrawerFragment;

import org.json.JSONArray;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Menu menu;

    private Fragment currentFragment;
    private boolean TwiceBackPressed = false;
    private Context context;

    public MainActivity() {

    }


    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        // load needed data
        SyncHelper.SyncWork(context);
        SyncHelper.SyncProject(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {            }
            @Override
            public void onError(String errorMessage) {            }
        });
        SyncHelper.SyncTaradod(context);
        SyncHelper.SyncFaliat(context);
        SyncHelper.SyncAnbar(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {            }
            @Override
            public void onError(String errorMessage) {            }
        });
        SyncHelper.SyncItemsProvider(context);

        DatabaseHelper db = new DatabaseHelper(context);
        db.cleanOldData(7);

        //  this.getFragmentManager().addOnBackStackChangedListener(new BackStackChanged(this));


//        Fragment frag = new FragmentSplash();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, frag)
//                .commit();
        //ShowLoginFragment("Main Activity");
//
//        if (savedInstanceState==null)
//            ShowTreeFragmnet("");


        prepareActionbar();

        /*Intent intent = new Intent(this, TreeViewActivity.class);
        startActivity(intent);
        finish();*/

        Webservice.getHomePageInfo(this, new CallBack<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                int a = 10;

            }

            @Override
            public void onError(String errorMessage) {

                HandleError.HandleError(context, errorMessage, new CallBackFunction() {
                    @Override
                    public void execute() {

                    }
                });
            }
        });


    }

    private void prepareActionbar() {

        final ViewGroup customActionBar = (ViewGroup) getLayoutInflater().inflate(
                R.layout.actionbar_nav,
                null);

//        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_nav, null);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(customActionBar);

        ImageView icon = (ImageView) customActionBar.findViewById(R.id.ac_icon);
        icon.setVisibility(View.GONE);

        TextView title = (TextView) customActionBar.findViewById(R.id.ac_title);

        FontHelper.SetFont(this, Fonts.MAIN_FONT, title, Typeface.BOLD);

        LinearLayout nav = (LinearLayout) customActionBar.findViewById(R.id.ac_nav_layout);

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavigationDrawerFragment.toggleNavigationDrawer();

            }
        });


    }

    //----------------------------------------------------------------------------------------
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        Intent intent;

        switch (position) {

            case 0:

                intent = new Intent(this, TreeViewActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 1:
                intent = new Intent(this, ProjectPickerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 3:
                intent = new Intent(this, AboutAppActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 4:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 5:

                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;


            case 2:
                intent = new Intent(this, AnbarPickerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

        }

    }


    //----------------------------------------------------------------------------------------
    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }

//    //----------------------------------------------------------------------------------------
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
//            this.menu = menu;
//            restoreActionBar();
//            return true;
////        }
//
//
//        changeMenuIcons(true, false, "main act onCreateOptionsMenu");
//
//
//        return false;
//    }

    //----------------------------------------------------------------------------------------
  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if (item.getItemId() == R.id.action_back) {
            onBackPressed();
            return true;
        }

        if (id == R.id.ac_pick_personnel) {
            return false;
        }

        if (id == R.id.ac_new_work_report) {
            return false;
        }

        if (id == R.id.ac_work_report_save) {
            return false;
        }


        return super.onOptionsItemSelected(item);
    }
*/
    //----------------------------------------------------------------------------------------
    /*public void ShowTreeFragmnet(String CallerFragment) {

        // hide Tabs if Exist
        hideTabs();

        // Call ProjectTree View Fraqgment
        Fragment frag = new FragmentProjectTreeView();
        replaceFragment(frag, "FragmentProjectTreeView", false);


    }*/

    //-----------------------------------------------------------------------------------
  /*  public void ShowTreeFragmnet(Chart chart, String CallerFragment) {


        // hide Tabs if Exist
        hideTabs();

        // Call ProjectTree View Fraqgment
        Fragment frag = new FragmentProjectTreeView();
        ((FragmentProjectTreeView) frag).setChart(chart);
        replaceFragment(frag, "FragmentProjectTreeView", true);

        PathMapManager.push(chart);
    }*/

    //---------------------------------------------------------------------------------------
    public void ShowWorkFragment(Chart chart, String CallerFragment, boolean addToBackStack) {

       /* Fragment frag = new FragmentWork();
        ((FragmentWork) frag).setChart(chart);
        replaceFragment(frag, addToBackStack);


        PathMapManager.push(chart);*/


    }

    //---------------------------------------------------------------------------------------
//    public void ShowWorkTaskFragment(Chart chart, String CallerFragment) {
//        Fragment frag = new FragmentWorkTasks();
//        ((FragmentWorkTasks) frag).setChart(chart);
//        replaceFragment(frag, false);
//    }

    //---------------------------------------------------------------------------------------
//    public void ShowLoginFragment(String CallerFragment) {
//
//        // hide Tabs if Exist
//        hideTabs();
//
//        Fragment frag = new FragmentLogin();
//        replaceFragment(frag, false);
//    }

    //-------------------------------------------------------------------------------------
//    public void changeMenuIcons(boolean navigation, boolean back, String caller) {
//
//        if (menu.findItem(R.id.action_navi) != null)
//            menu.findItem(R.id.action_navi).setVisible(navigation); // getItem(R.id.action_navi).setVisible(navigation);
//
//        if (menu.findItem(R.id.action_back) != null)
//            menu.findItem(R.id.action_back).setVisible(back);
//    }

    //-------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {

        int backStackEntryCount = getFragmentManager().getBackStackEntryCount();

        overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);

        if (TwiceBackPressed) {
            finish();
            overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
        } else {
            if (backStackEntryCount > 0)
                Toast.makeText(this, "جهت خروج دوبار بزنید", Toast.LENGTH_SHORT).show();
            TwiceBackPressed = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    TwiceBackPressed = false;
                }
            }, 700);
        }

        int befor = getFragmentManager().getBackStackEntryCount();

        super.onBackPressed();

        int after = getFragmentManager().getBackStackEntryCount();


    }

    //-------------------------------------------------------------------------------------
    public Menu getMenu() {
        return menu;
    }

    //-------------------------------------------------------------------------------------
    public void hideTabs() {


        if (getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
            getActionBar().removeAllTabs();
            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }


    }

    //-------------------------------------------------------------------------------------
   /* public void replaceFragment(Fragment frag, String tag, boolean addToBackStack) {

        if (addToBackStack) {  // add to back stack or not

            if (currentFragment != null) {
                getFragmentManager().beginTransaction()
                        //  .detach(currentFragment)
                        .replace(R.id.container, frag, tag)
                        .addToBackStack(null)
                        .commit();
            }

            if (currentFragment == null) {
                getFragmentManager().beginTransaction()
                        .add(R.id.container, frag, tag)
                        .addToBackStack(null)
                        .commit();
            }

        } else {
            if (currentFragment != null) {
                getFragmentManager().beginTransaction()
                        // .detach(currentFragment)
                        .replace(R.id.container, frag, tag)
                        .commit();
            }

            if (currentFragment == null) {
                getFragmentManager().beginTransaction()
                        .add(R.id.container, frag, tag)
                        .commit();
            }
        }
        currentFragment = frag;
    }*/

    //-------------------------------------------------------------------------------------
    public static class BackStackChanged implements FragmentManager.OnBackStackChangedListener {

        int lastSize = 0;
        private Activity activity;

        public BackStackChanged(Activity activity) {

            this.activity = activity;
        }

        @Override
        public void onBackStackChanged() {

            int currentStackSize = activity.getFragmentManager().getBackStackEntryCount();

//            if(currentStackSize < lastSize){
//                PathMapManager.pop(" M A onBackStackChanged");
//            }

            if (currentStackSize > 0) {
                //((MainActivity) activity).changeMenuIcons(false, true, "backstack > 0");
            } else {
                //((MainActivity) activity).changeMenuIcons(true, false, "backstack <= 0");
            }

            lastSize = currentStackSize;
        }
    }
    //-------------------------------------------------------------------------------------


}
