package com.pga.project1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.fragment.FragmentLogin;
import com.pga.project1.fragment.FragmentProjectTreeView;
import com.pga.project1.fragment.FragmentSplash;
import com.pga.project1.fragment.FragmentWorkInfo;
import com.pga.project1.fragment.FragmentWorkPersonnel;
import com.pga.project1.fragment.NavigationDrawerFragment;


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

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

    }

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

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }


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

//----------------------------------------------------------------------------------------
    public void ShowTreeFragmnet(String CallerFragment) {

        // Call ProjectTree View Fraqgment

        Bundle bundle = new Bundle();
        bundle.putInt("fatherId", -1);

        Fragment frag = new FragmentProjectTreeView();
        frag.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit();
    }

    public void ShowTreeFragmnet(Chart chart, String CallerFragment) {

        // Call ProjectTree View Fraqgment


        Fragment frag = new FragmentProjectTreeView();
        ((FragmentProjectTreeView) frag).setChart(chart);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }

    //---------------------------------------------------------------------------------------
    public void ShowWorkFragment(Chart chart, String CallerFragment) {
        Fragment frag = new FragmentWorkInfo();
        ((FragmentWorkInfo) frag).setChart(chart);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }

    //---------------------------------------------------------------------------------------
    public void ShowWorkPersonelFragment(Chart chart, String CallerFragment) {
        Fragment frag = new FragmentWorkPersonnel();
        ((FragmentWorkPersonnel) frag).setChart(chart);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();
    }
    //---------------------------------------------------------------------------------------
    public void ShowLoginFragment(String CallerFragment) {
        Fragment frag = new FragmentLogin();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit();
    }
    //-------------------------------------------------------------------------------------
    public void changeMenuIcons(boolean navigation, boolean back){

        menu.findItem(R.id.action_navi).setVisible(navigation); // getItem(R.id.action_navi).setVisible(navigation);
        menu.findItem(R.id.action_back).setVisible(back);
    }
    //-------------------------------------------------------------------------------------

    public void backPressed(){

        PathMapManager.pop(this);

        this.getFragmentManager().popBackStack();
       // onBackPressed();
    }
    //-------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressed();
    }

    //-------------------------------------------------------------------------------------
    public static class BackStackChanged implements FragmentManager.OnBackStackChangedListener {

        private Activity activity;

        public BackStackChanged(Activity activity){

            this.activity = activity;
        }

        @Override
        public void onBackStackChanged() {

            int currentStackSize = activity.getFragmentManager().getBackStackEntryCount();

            if(currentStackSize > 0){
                ((MainActivity) activity).changeMenuIcons(false, true);
            }else{
                ((MainActivity) activity).changeMenuIcons(true, false);
            }
        }
    }
    //-------------------------------------------------------------------------------------
    public Menu getMenu() {
        return menu;
    }
}
