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
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.Helpers.SyncHelper;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Intefaces.CallBackFunction;
import com.pga.project1.R;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.HandleError;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.ViewNameValue;
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
    private ImageView reload;

    public MainActivity() {

    }


    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{



        context = this;
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        // load needed data
        SyncHelper.syncWork(context);
        SyncHelper.syncProject(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                LoadHomePageInfo();
            }

            @Override
            public void onError(String errorMessage) {
            }
        });
        SyncHelper.syncTaradod(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                LoadHomePageInfo();
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
        SyncHelper.syncFaliat(context);
        SyncHelper.syncAnbar(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                LoadHomePageInfo();
            }

            @Override
            public void onError(String errorMessage) {
            }
        });
        SyncHelper.syncItemsProvider(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
        SyncHelper.syncProduct(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
        SyncHelper.syncAnbarTransaction(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
        SyncHelper.syncPersonnel(context, new CallBack() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });


        DatabaseHelper db = new DatabaseHelper(context);
        db.cleanOldData(7);

        prepareActionbar();



        LoadHomePageInfo();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void LoadHomePageInfo() {
        try{


        ViewNameValue nv1 = (ViewNameValue) findViewById(R.id.nv1);
        ViewNameValue nv2 = (ViewNameValue) findViewById(R.id.nv2);
        ViewNameValue nv3 = (ViewNameValue) findViewById(R.id.nv3);

        nv1.setNameValue("تعداد پروژه های من", new DatabaseHelper(context).getProjects().size() + "");
        nv2.setNameValue("تعداد انبار های من", new DatabaseHelper(context).getMyAnbars().size() + "");
        nv3.setNameValue("تعداد افراد حاضر امروز", new DatabaseHelper(context).countCurrentPersonnel() + "");

        new DatabaseHelper(context).countCurrentPersonnel();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void prepareActionbar() {
try{
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

        reload = (ImageView) customActionBar.findViewById(R.id.ac_action1);

        //addPhotoButton.setText("تصویر");
        reload.setImageResource(R.drawable.ac_refresh);
        //addPhotoButton.setTextColor(getResources().getColor(R.color.actionbar_button_text));
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SyncHelper.syncProject(context, new CallBack() {
                    @Override
                    public void onSuccess(Object result) {
                        LoadHomePageInfo();
                    }

                    @Override
                    public void onError(String errorMessage) {

                    }
                });

                SyncHelper.syncAnbar(context, new CallBack() {
                    @Override
                    public void onSuccess(Object result) {
                        LoadHomePageInfo();
                    }

                    @Override
                    public void onError(String errorMessage) {

                    }
                });

                SyncHelper.syncTaradod(context, new CallBack() {
                    @Override
                    public void onSuccess(Object result) {
                        Toast.makeText(context, "بروزرسانی انجام شد", Toast.LENGTH_SHORT).show();
                        LoadHomePageInfo();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(context, "بروز رسانی پروژه ها با خطا مواجه شد", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        reload.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, LogActivity.class);
                startActivity(intent);


                return true;
            }
        });


}catch (Exception e){
    e.printStackTrace();
}
    }

    //----------------------------------------------------------------------------------------
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        try{
        // update the main content by replacing fragments

        Intent intent;

        switch (position) {

//            case 0:
//
//                intent = new Intent(this, TreeViewActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
//                break;

            case 0:
                intent = new Intent(this, ProjectPickerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 1:
                intent = new Intent(this, AnbarPickerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;


            case 2:
                intent = new Intent(this, AboutAppActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 3:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 4:

                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                break;

            case 5:
                Account.getInstant(context).clearToken();
                Intent intent2 = new Intent(context, LoginActivity.class);
                intent2.putExtra("reason", "UNAUTHORIZED");
                context.startActivity(intent2);
                ((Activity) context).overridePendingTransition(R.anim.activity_fade_in_animation, R.anim.activity_fade_out_animation);
                this.finish();
                break;


        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------
    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }

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


    @Override
    protected void onResume() {
        super.onResume();
        LoadHomePageInfo();
    }
}
