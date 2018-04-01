package com.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.adapters.TabsFragmentAdapter;
import com.tripuranow.R;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabsFragmentAdapter tabsFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initUI();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
         //Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            // Handle the camera action
        } else if (id == R.id.nav_buzz) {

        } else if (id == R.id.nav_live) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initUI() {

        //final ViewPager viewPager=(ViewPager)findViewById(R.id.tab_bar_pager);
       // this.tabsFragmentAdapter = new TabsFragmentAdapter(getSupportFragmentManager());
        //viewPager.setAdapter(this.tabsFragmentAdapter);



        final String[] colors = getResources().getStringArray(R.array.tab_bar_item_colors);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_menu_camera),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_menu_camera))
                        .title("Services")
//                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_menu_manage),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_menu_manage))
                        .title("Upload")
//                        .badgeTitle("with")
                        .build()
        );


        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_menu_gallery),
                        Color.TRANSPARENT)
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_menu_gallery))
                        .title("Properties")
                       // .isLargeIcon(true)
//                        .badgeTitle("icon")
                        .build()

        );


        navigationTabBar.setModels(models);
        //navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setBgColor (getResources().getColor( R.color.tabbarbgcolor));
        navigationTabBar.setInactiveColor(getResources().getColor(R.color.tabbardefaultitemcolor));
        navigationTabBar.setActiveColor(getResources().getColor(R.color.white));


        navigationTabBar.setBehaviorEnabled(false);

        navigationTabBar.setIsSwiped(false);


        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

//                Utils.consoleLog(MainTabActivity.class,"onPageScrolled");


            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
//                Utils.consoleLog(MainTabActivity.class,"onPageSelected");
//
//                Config.getSharedInstance().selectedTab = position;
//                switch (position)
//                {
//                    case 0:
//                        DashboardFragment dashboardFragment = (DashboardFragment)tabsFragmentAdapter.getItem(0);
//                        dashboardFragment.refreshFragment();
//                        break;
//                    case 1:
//                        LeadsFragment leadsFragment = (LeadsFragment) tabsFragmentAdapter.getItem(1);
//                        leadsFragment.refreshFragment();
//                        break;
//
//                    case 2:
//                        RevenueFragment revenueFragment = (RevenueFragment) tabsFragmentAdapter.getItem(2);
//                        revenueFragment.refreshFragment();
//                        break;
//
//
//                    case 3:
//                        TeamActivitiesFragment teamActivitiesFragment = (TeamActivitiesFragment) tabsFragmentAdapter.getItem(3);
////                        teamActivitiesFragment.refreshFragment();
//                        break;
//
//
//
//                    case 4:
//                        SettingsFragment settingsFragment = (SettingsFragment) tabsFragmentAdapter.getItem(4);
//                        settingsFragment.refreshFragment();
//                        break;
//
//
//                }

            }

            @Override
            public void onPageScrollStateChanged(final int state) {
//                Utils.consoleLog(MainTabActivity.class,"onPageScrollStateChanged");


            }
        });




    }
}
