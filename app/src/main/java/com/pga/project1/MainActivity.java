package com.pga.project1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Personnel;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.fragment.FragmentLogin;
import com.pga.project1.fragment.FragmentNewReportWork;
import com.pga.project1.fragment.FragmentPersonnelSearch;
import com.pga.project1.fragment.FragmentProjectTreeView;
import com.pga.project1.fragment.FragmentSplash;
import com.pga.project1.fragment.FragmentTaskPage;
import com.pga.project1.fragment.FragmentWork;
import com.pga.project1.fragment.NavigationDrawerFragment;

import java.io.File;


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

    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        this.getFragmentManager().addOnBackStackChangedListener(new BackStackChanged(this));


        //this.changeMenuIcons(true, false);

        Fragment frag = new FragmentSplash();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit();


        Uri fileUri = Uri.parse("android.resource://com.pga.project1/" + R.drawable.ic_launcher);

        File myFile = new File(fileUri.toString());



        Webservice.uploadFile(this, myFile.getAbsolutePath() , new CallBack() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(ErrorMessage err) {

            }
        } );

    }

    //----------------------------------------------------------------------------------------
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

    }

    //----------------------------------------------------------------------------------------
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
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

    //----------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            this.menu = menu;
            restoreActionBar();
            return true;
        }


        return super.onCreateOptionsMenu(menu);
    }

    //----------------------------------------------------------------------------------------
    @Override
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

    //----------------------------------------------------------------------------------------
    public void ShowTreeFragmnet(String CallerFragment) {

        // hide Tabs if Exist
        hideTabs();

        // Call ProjectTree View Fraqgment
        Fragment frag = new FragmentProjectTreeView();
        replaceFragment(frag, false);


    }

    //-----------------------------------------------------------------------------------
    public void ShowTreeFragmnet(Chart chart, String CallerFragment) {


        // hide Tabs if Exist
        hideTabs();

        // Call ProjectTree View Fraqgment
        Fragment frag = new FragmentProjectTreeView();
        ((FragmentProjectTreeView) frag).setChart(chart);
        replaceFragment(frag, true);

        PathMapManager.push(chart);
    }

    //---------------------------------------------------------------------------------------
    public void ShowWorkFragment(Chart chart, String CallerFragment, boolean addToBackStack) {
        Fragment frag = new FragmentWork();
        ((FragmentWork) frag).setChart(chart);
        replaceFragment(frag, addToBackStack);


        PathMapManager.push(chart);

    }

    //---------------------------------------------------------------------------------------
//    public void ShowWorkTaskFragment(Chart chart, String CallerFragment) {
//        Fragment frag = new FragmentWorkTasks();
//        ((FragmentWorkTasks) frag).setChart(chart);
//        replaceFragment(frag, false);
//    }

    //---------------------------------------------------------------------------------------
    public void ShowLoginFragment(String CallerFragment) {

        // hide Tabs if Exist
        hideTabs();

        Fragment frag = new FragmentLogin();
        replaceFragment(frag, false);
    }

    //-------------------------------------------------------------------------------------
    public void changeMenuIcons(boolean navigation, boolean back) {

        if (menu.findItem(R.id.action_navi) != null)
            menu.findItem(R.id.action_navi).setVisible(navigation); // getItem(R.id.action_navi).setVisible(navigation);

        if (menu.findItem(R.id.action_back) != null)
            menu.findItem(R.id.action_back).setVisible(back);
    }

    //-------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {

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
        if (getActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS)
            getActionBar().setNavigationMode(
                    ActionBar.NAVIGATION_MODE_STANDARD);
    }

    //-------------------------------------------------------------------------------------
    public void replaceFragment(Fragment frag, boolean addToBackStack) {

        if (addToBackStack) {  // add to back stack or not

            if (currentFragment != null) {
                getFragmentManager().beginTransaction()
                        .remove(currentFragment)
                        .add(R.id.container, frag)
                        .addToBackStack(null)
                        .commit();
            }

            if (currentFragment == null) {
                getFragmentManager().beginTransaction()
                        .add(R.id.container, frag)
                        .addToBackStack(null)
                        .commit();
            }

        } else {
            if (currentFragment != null) {
                getFragmentManager().beginTransaction()
                        .remove(currentFragment)
                        .add(R.id.container, frag)
                        .commit();
            }

            if (currentFragment == null) {
                getFragmentManager().beginTransaction()
                        .add(R.id.container, frag)
                        .commit();
            }
        }
        currentFragment = frag;
    }

    //-------------------------------------------------------------------------------------
    public void ShowNewReportFragment(final Chart chart) {

        // hide Tabs if Exist
        hideTabs();

        final Context self = this;

        CallBack callback = new CallBack() {

            @Override
            public void onSuccess(Object result) {

                FragmentWork frag = new FragmentWork();
                frag.setChart(chart);
                frag.setActiveTab(FragmentWork.TabReport);

                replaceFragment(frag, true);
                PathMapManager.pop("M A   O S   ShowNewReportFragment");
            }

            @Override
            public void onError(ErrorMessage err) {

                FragmentWork frag = new FragmentWork();
                frag.setChart(chart);
                frag.setActiveTab(FragmentWork.TabReport);

                replaceFragment(frag, false);
                Toast.makeText(self, "no report added", Toast.LENGTH_SHORT).show();

            }
        };


        Fragment frag = new FragmentNewReportWork();
        ((FragmentNewReportWork) frag).setChart(chart);
        ((FragmentNewReportWork) frag).setCallback(callback);
        replaceFragment(frag, true);
        PathMapManager.push(new PathObject("ثبت پیشرفت کار"));
    }

    //-------------------------------------------------------------------------------------
    public void ShowTaskInfoFragment(Chart chart) {
        FragmentTaskPage frag = FragmentTaskPage.getInstanceInfo();
        ((FragmentTaskPage) frag).setChart(chart);
        replaceFragment(frag, true);
    }

    //-------------------------------------------------------------------------------------
    public void ShowTaskReportsFragment(Chart chart) {
        FragmentTaskPage frag = FragmentTaskPage.getInstanceReports();
        ((FragmentTaskPage) frag).setChart(chart);
        replaceFragment(frag, true);
    }

    //-------------------------------------------------------------------------------------
    public void ShowTaskPageFragment(Chart chart) {
        FragmentTaskPage frag = FragmentTaskPage.getInstanceInfo();
        ((FragmentTaskPage) frag).setChart(chart);
        replaceFragment(frag, true);

        PathMapManager.push(chart);
    }

    //-------------------------------------------------------------------------------------
    public void ShowPersonnelSearch(final Chart chart) {

        // hide Tabs if Exist
        hideTabs();

        FragmentPersonnelSearch frag = new FragmentPersonnelSearch();

        final Context self = this;

        CallBack<Personnel> callback = new CallBack<Personnel>() {
            @Override
            public void onSuccess(Personnel result) {

                FragmentWork frag = new FragmentWork();
                frag.setChart(chart);
                frag.setPersonnel(result);
                frag.setActiveTab(FragmentWork.TabPersonnel);

                replaceFragment(frag, true);
                PathMapManager.pop(" M A O S ShowPersonelSearch");
            }

            @Override
            public void onError(ErrorMessage err) {

                FragmentWork frag = new FragmentWork();
                frag.setChart(chart);
                frag.setActiveTab(FragmentWork.TabPersonnel);

                replaceFragment(frag, false);
                Toast.makeText(self, "no personnel selected", Toast.LENGTH_SHORT).show();

            }
        };

        frag.setCallback(callback);
        replaceFragment(frag, true);
        PathMapManager.push(new PathObject("انتخاب پرسنل"));

    }

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
                ((MainActivity) activity).changeMenuIcons(false, true);
            } else {
                ((MainActivity) activity).changeMenuIcons(true, false);
            }

            lastSize = currentStackSize;
        }
    }
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------//-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------


}
