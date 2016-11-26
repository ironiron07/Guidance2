package com.example.raeven.guidance;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivityAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    String number;

    String accType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);

        Intent rcvIntent = getIntent();
        this.number = rcvIntent.getStringExtra("Number");
        this.accType = rcvIntent.getStringExtra("accountType");

        HomeFragmentAdmin homeFragment = new HomeFragmentAdmin();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentHolder, homeFragment,
                homeFragment.getTag()).commit();

        final List<String> itemList = new ArrayList<String>();


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item,itemList);



        Toast.makeText(getApplicationContext(),number,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragmentAdmin homeFragment = new HomeFragmentAdmin();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragmentHolder, homeFragment,
                    homeFragment.getTag()).commit();

            Toast.makeText(this, "I'm at Home", Toast.LENGTH_SHORT).show();

        }

        else if (id == R.id.nav_councelors) {
            Toast.makeText(this, "I'm at Councelors Fragment", Toast.LENGTH_SHORT).show();
            CouncelorsFragment councelorsFragment = new CouncelorsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragmentHolder, councelorsFragment,
                    councelorsFragment.getTag()).commit();

        }

        else if (id == R.id.nav_account) {
            Toast.makeText(this, "I'm at Account", Toast.LENGTH_SHORT).show();
            AccountFragment accountFragment = new AccountFragment();
            Bundle bundle = new Bundle();
            bundle.putString("accType", this.accType);
            bundle.putString("number", this.number);
            FragmentManager manager = getSupportFragmentManager();

            accountFragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.fragmentHolder, accountFragment,
                    accountFragment.getTag()).commit();
        }

        else if (id == R.id.nav_logout) {
            Toast.makeText(this, "I'm at Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void checkCredentials(){
//        if ..
//
//        else ..

//        Intent intent = new Intent(this, DashboardActivity.class);
//        startActivity(intent);

        Toast.makeText(this, "I'm at check ", Toast.LENGTH_SHORT).show();


    }

    public String getAccount(){
        return this.number;
    }
    public String getAccountType(){ return this.accType; }


}