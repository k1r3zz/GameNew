package com.example.administrator.gamenew;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button zhangmeng_but, hezi_but, mine_but;
    private FragmentManager fm;
    //private android.support.v4.app.Fragment mFragments;
    static Activity act;
    private NavigationView navigationView;
    private Fragment NowFragment;
    private MineFragment mineFragment=new MineFragment();
    private HZFragment hzFragment=new HZFragment();
    private ZMFragment zmFragment=new ZMFragment();

    //private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //transplantStatus();
    }


    public void init() {
        act = this;
        //        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        zhangmeng_but = (Button) findViewById(R.id.zhangmeng_but);
        hezi_but = (Button) findViewById(R.id.box_but);
        mine_but = (Button) findViewById(R.id.mine_but);
//        Drawable drawable = getResources().getDrawable(R.drawable.tabzmbutton);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth() * 1 / 2,
//                drawable.getMinimumHeight() * 1 / 2);
//        zhangmeng_but.setCompoundDrawables(null, drawable, null, null);

//        Drawable drawable2 = getResources().getDrawable(R.drawable.tabhzbutton);
//        drawable2.setBounds(0, 0, drawable2.getMinimumWidth() * 1 / 2,
//                drawable2.getMinimumHeight() * 1 / 2);
//        hezi_but.setCompoundDrawables(null, drawable2, null, null);

//        Drawable drawable3 = getResources().getDrawable(R.drawable.tabmebutton);
//        drawable3.setBounds(0, 0, drawable3.getMinimumWidth() * 1 / 2,
//                drawable3.getMinimumHeight() * 1 / 2);
//        mine_but.setCompoundDrawables(null, drawable3, null, null);

        fm = getSupportFragmentManager();
//        zmFragment = new ZMFragment();
//        fm.beginTransaction().replace(R.id.main_framelayout, mFragments)
//                .commit();
//        fm.beginTransaction().addToBackStack(null);
        FragmentTransaction transaction=fm.beginTransaction().setCustomAnimations( R.anim.popwindow_anim,R.anim.popwindow_anim);
        transaction.add(R.id.main_framelayout,zmFragment).commit();
        NowFragment=zmFragment;
        //transaction.addToBackStack(null);

        zhangmeng_but.setSelected(true);
        zhangmeng_but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//               zmFragment = new ZMFragment();
//                fm.beginTransaction()
//                        .replace(R.id.main_framelayout, mFragments).commit();
//                fm.beginTransaction().addToBackStack(null);
                FragmentTransaction transaction=fm.beginTransaction().setCustomAnimations( R.anim.popwindow_anim,R.anim.popwindow_anim);

                if(!zmFragment.isAdded()){
                    transaction.hide(NowFragment).add(R.id.main_framelayout,zmFragment).commit();
                }else {
                    transaction.hide(NowFragment).show(zmFragment).commit(); // 隐藏当前的fragment，显示下一个
                }
                NowFragment=zmFragment;
               // transaction .addToBackStack(null);


                // zhangmeng_but.setFocusable(true);
                // zhangmeng_but.requestFocus();
                // zhangmeng_but.requestFocusFromTouch();
                zhangmeng_but.setSelected(true);
                mine_but.setSelected(false);
                hezi_but.setSelected(false);
            }
        });

        hezi_but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                hzFragment = new HZFragment();
//                fm.beginTransaction()
//                        .replace(R.id.main_framelayout, mFragments).commit();
//                fm.beginTransaction().addToBackStack(null);
                FragmentTransaction transaction=fm.beginTransaction().setCustomAnimations( R.anim.popwindow_anim,R.anim.popwindow_anim);
                if(!hzFragment.isAdded()){
                    transaction.hide(NowFragment).add(R.id.main_framelayout,hzFragment).commit();
                }else {
                    transaction.hide(NowFragment).show(hzFragment).commit(); // 隐藏当前的fragment，显示下一个
                }
                NowFragment=hzFragment;
               // transaction .addToBackStack(null);

                hezi_but.setSelected(true);
                zhangmeng_but.setSelected(false);
                mine_but.setSelected(false);
            }
        });
        mine_but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                mineFragment = new MineFragment();
//                fm.beginTransaction()
//                        .replace(R.id.main_framelayout, mFragments).commit();
//                fm.beginTransaction().addToBackStack(null);
                FragmentTransaction transaction=fm.beginTransaction().setCustomAnimations( R.anim.popwindow_anim,R.anim.popwindow_anim);
                if(!mineFragment.isAdded()){
                    transaction.hide(NowFragment).add(R.id.main_framelayout,mineFragment).commit();
                }else {
                    transaction.hide(NowFragment).show(mineFragment).commit(); // 隐藏当前的fragment，显示下一个
                }
                NowFragment=mineFragment;
              //  transaction.addToBackStack(null);

                hezi_but.setSelected(false);
                zhangmeng_but.setSelected(false);
                mine_but.setSelected(true);

            }
        });

        IntentFilter filter = new IntentFilter(ZMFragment.action);
        this.registerReceiver(broadcastReceiver, filter);

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            } catch (Exception e) {
                Log.i("opp", e.toString());
            }

        }
    };


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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("InlinedApi")
    private void transplantStatus() {
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 透明导航栏
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

}
