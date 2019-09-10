package com.example.myapplicationj9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;


import com.example.myapplicationj9.Data.Contract;
import com.example.myapplicationj9.Data.NetworkData;
import com.example.myapplicationj9.Data.SharedPrefrance;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////////////////////////////////////////////////////////////////////////////////////Data Part
        SharedPreferences sharedPreferences=getSharedPreferences(Contract.SharedPrefrances,MODE_PRIVATE);
        SharedPrefrance sharedPrefrance = new SharedPrefrance(sharedPreferences);
        boolean FirstTime = sharedPrefrance.isFirstTime();
        if(isNetworkAvailable(this))
        {
            if (FirstTime)
            {

            }
            else
            {

            }

        }
        else
        {
            if (FirstTime)
            {

            }
            else
            {

            }

        }
        new DataAsyncTask().execute();








        ////////////////////////////////////////////////////////////////////////////////////////////////////Data part


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.city, R.id.map, R.id.near,
                R.id.feed, R.id.type, R.id.setting)
                .setDrawerLayout(drawer)
                .build();

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem item= navigationView.getCheckedItem();
        if(item!=null)
            item.setChecked(false);
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
       //NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        switch (id) {

            case R.id.city:
                startActivity(new Intent(this,Cities_1.class));
                return true;
            case R.id.setting:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.feed:
                Intent email = new Intent(Intent.ACTION_SEND);

                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"EnjoyEgypt.com.test"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                email.putExtra(Intent.EXTRA_TEXT, "I have a problem with: ");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                return true;
            default:
                return false;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MenuItem item= navigationView.getCheckedItem();
        if(item!=null)
            item.setChecked(false);
    }
    ////////////data Part


    class DataAsyncTask extends AsyncTask <Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            new NetworkData(getContentResolver()).getData();
            return null;
        }
    }

    public boolean isNetworkAvailable(Context context) {
        //boolean function to check the internet connection
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
