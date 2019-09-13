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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,MainAdapter.openDetailOfSight {

    ArrayList<City> cities;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter;
    ///////////////
    Spinner type;


    //////////////////////
    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
////////////////////////////
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
                R.id.feed, R.id.setting)
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


        ///////////////////////////////////////////////////////////////////////////

        ArrayList<Comment> comments=new ArrayList<Comment>();
        ArrayList<Sight> sights=new ArrayList<Sight>();
        cities=new ArrayList<City>();
        ArrayList<Content> contents=new ArrayList<Content>();
        contents.add(new Content("el shrouq hotel bla bla","five star hotel bla bla","no cordinates",R.drawable.ic_menu_slideshow));

        sights.add(new Sight("Cairo Tower","tall tower sadflsdnaffsf;sdafsda;fsdalfsdbldsf;jnasdbdsa;sd","no cordinates",R.drawable.m1,contents));
        sights.add(new Sight("river","amazing river  sdafl;ndsaf;lhvbesfdaohi;adsflkbnefasdoihzl;afsdhibk;dsa","no cordinates",R.drawable.m2));
        sights.add(new Sight(" bla bla","bla bla asdfafdsvn[puwadsopibewadsloewi[u9adsgvjhjodaif9suihewopafdhewiafdshew","no cordinates",R.drawable.m4));
        sights.add(new Sight("Cairo Tower","tall tower","no cordinates",R.drawable.m1));
        sights.add(new Sight("Cairo Tower","tall tower","no cordinates",R.drawable.m4));
        sights.add(new Sight("Cairo Tower","tall tower","no cordinates",R.drawable.m2));

        comments.add(new Comment("ahmed","this city is amazing" ));
        comments.add(new Comment("khaled","this city is amazing" ));
        comments.add(new Comment("asmaa","this city is amazing" ));
        comments.add(new Comment("khaled","this city is amazing" ));
        comments.add(new Comment("ahmed","this city is amazing" ));
        comments.add(new Comment("asmaa","this city is amazing" ));

        cities=new ArrayList<City>();
        cities.add(new City("Cairo",
                sights,
                3,
                "km",
                R.drawable.m1
                ,comments
                , "cairo hase a lot of things"+
                "bla bla  sdlfjsdl;fosdfhlsdj" +
                "alsdfdsl;jfdsfslda" +
                ";sdlfjsdlaf;slad" +
                "sadlfjds;lfj;asdjlfdskf;" +
                "asdlfj;dsf;jsadjfldasldfsdlaf" +
                "asld;fjadslfja;dsl'jfsdlak;fjsa" +
                "asdlfkjsdhflasdf;lds" +
                "asfdh;khsd;fsladk;fhsdafadsk" +
                "sadf;hdsihoiekdsifjll" +
                "la;sdfsadljfsadl;fjasdl" +
                "sioadf;jadslj;f[asdjfjlads;llf" +
                "psadfjeiaosufekadsf asdi[fewioaps" +
                "poiesfujewj;aflsdp[fesdoplesaop" +
                "aepfioeparosd9[fopneasd9poasdofla"));


        cities.add(new City("Giza",sights,3,"km",R.drawable.m2));
        cities.add(new City("whatever else",sights,3,"km",R.drawable.m3));
        cities.add(new City("Cairo",sights,3,"km",R.drawable.m4));
        cities.add(new City("Giza",sights,3,"km",R.drawable.m1));
        cities.add(new City("whatever else",sights,3,"km",R.drawable.m2));
        cities.add(new City("Cairo",sights,3,"km",R.drawable.m3));
        cities.add(new City("Giza",sights,3,"km",R.drawable.m4));
        cities.add(new City("whatever else",sights,3,"km",R.drawable.m1));

        recyclerView=findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MainAdapter(this,cities.get(0).getSights());
        recyclerView.setAdapter(adapter);
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);
        //////////////////////////////////////////////////////////////////
        type=(Spinner)findViewById(R.id.tySpinner);
        type.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                R.layout.spinner,
                getResources().getStringArray(R.array.items_type)));


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {

                // int selectedtype = adapterView.getSelectedItemPosition();
                String selectedtype = adapterView.getItemAtPosition(i).toString();

                switch (selectedtype)
                {
                    case "Religious":
                        ArrayList<Sight> sight_type=new ArrayList<Sight>();

                        sight_type.add(new Sight("Al-Azhar Mosque","amazing river  sdafl;ndsaf;lhvbesfdaohi;adsflkbnefasdoihzl;afsdhibk;dsa","no cordinates",R.drawable.t25));
                        sight_type.add(new Sight("Castel","bla bla asdfafdsvn[puwadsopibewadsloewi[u9adsgvjhjodaif9suihewopafdhewiafdshew","no cordinates",R.drawable.t23));
                        sight_type.add(new Sight("Church","tall tower","no cordinates",R.drawable.t24));
                        sight_type.add(new Sight("Church","tall tower","no cordinates",R.drawable.t21));

                        adapter=new MainAdapter(MainActivity.this,sight_type);
                        recyclerView.setAdapter(adapter);
                        break;

                    case "Cultural":
                        ArrayList<Sight> sight_type1=new ArrayList<Sight>();

                        sight_type1.add(new Sight("Cairo Tower","tall tower sadflsdnaffsf;sdafsda;fsdalfsdbldsf;jnasdbdsa;sd","no cordinates",R.drawable.m1));
                        sight_type1.add(new Sight("river","amazing river  sdafl;ndsaf;lhvbesfdaohi;adsflkbnefasdoihzl;afsdhibk;dsa","no cordinates",R.drawable.m2));
                        sight_type1.add(new Sight(" bla bla","bla bla asdfafdsvn[puwadsopibewadsloewi[u9adsgvjhjodaif9suihewopafdhewiafdshew","no cordinates",R.drawable.m4));
                        sight_type1.add(new Sight("Cairo Tower","tall tower","no cordinates",R.drawable.m1));


                        adapter=new MainAdapter(MainActivity.this,sight_type1);
                        recyclerView.setAdapter(adapter);

                        break;

                    case "Adventure":
                        ArrayList<Sight> sight_type2=new ArrayList<Sight>();

                        sight_type2.add(new Sight("Cairo Tower","tall tower sadflsdnaffsf;sdafsda;fsdalfsdbldsf;jnasdbdsa;sd","no cordinates",R.drawable.t11));
                        sight_type2.add(new Sight("river","amazing river  sdafl;ndsaf;lhvbesfdaohi;adsflkbnefasdoihzl;afsdhibk;dsa","no cordinates",R.drawable.t41));
                        sight_type2.add(new Sight(" bla bla","bla bla asdfafdsvn[puwadsopibewadsloewi[u9adsgvjhjodaif9suihewopafdhewiafdshew","no cordinates",R.drawable.t42));
                        sight_type2.add(new Sight("Cairo Tower","tall tower","no cordinates",R.drawable.m1));
                        adapter=new MainAdapter(MainActivity.this,sight_type2);
                        recyclerView.setAdapter(adapter);

                        break;






                }
                Toast.makeText(MainActivity.this, " "+selectedtype +
                        " Tourism: \t" , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


















    }

    @Override
    public void openDetailsListener(Sight sight) {

        // open the frag of sights

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
