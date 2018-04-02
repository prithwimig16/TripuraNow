package com.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.fragments.BuzzFragment;
import com.fragments.LiveFragment;
import com.fragments.NewsFragment;
import com.tripuranow.R;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabsFragmentAdapter tabsFragmentAdapter;
    final String NEWS="News";
    private Fragment currFragment;
    BottomNavigationView bottomNavigationView;
    String currTag ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
         //Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment selectedFragment = null;
        if (id == R.id.nav_news) {

            selectedFragment = new NewsFragment();
            currTag = "news";
        } else if (id == R.id.nav_buzz) {
            selectedFragment = new BuzzFragment();
            currTag = "buzz";

        } else if (id == R.id.nav_live) {
            selectedFragment = new LiveFragment();
            currTag = "live";

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        currFragment = selectedFragment;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, currFragment,currTag);
                transaction.commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initUI() {

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu_live);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {

                    case R.id.cricket_tab:

                        if(currTag.equals("news")){
                            return true;
                        }
                        selectedFragment = new NewsFragment();
                        currTag = "news";
                        break;
                    case R.id.football_tab:
                        if(currTag.equals("buzz")){
                            return true;
                        }
                        selectedFragment = new BuzzFragment();
                        currTag = "buzz";
                        break;
                    case R.id.tennis_tab:
                        if(currTag.equals("live")){
                            return true;
                        }
                        selectedFragment = new LiveFragment();
                        currTag = "live";
                        break;

                }
                currFragment = selectedFragment;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_frame, currFragment,currTag);
                        transaction.commit();
                    }
                });
                return true;
            }
        });

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, new NewsFragment(),"cricket");
        transaction.commit();
        currTag = "cricket";


    }
}
