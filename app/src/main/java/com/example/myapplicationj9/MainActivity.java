package com.example.myapplicationj9;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;


import com.example.myapplicationj9.Data.Contract;
import com.example.myapplicationj9.Data.SharedPrefrance;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView ;
    boolean FirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////////////////////////////////////////////////////////////////////////////////////Data Part
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(Contract.SharedPrefrances, MODE_PRIVATE);
            SharedPrefrance sharedPrefrance = new SharedPrefrance(sharedPreferences);
            Cursor data=null;
            String Language;
            FirstTime = sharedPrefrance.isFirstTime();
            Language = sharedPrefrance.getLanguage();
            //if (FirstTime) {
            //    if (isNetworkAvailable(this)) {
                    new DataAsyncTask().execute();
                    sharedPrefrance.setFirstTime(false, sharedPreferences);
             //   } else {

             //       Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
             //   }
           /*** } else {
                Log.i("Ahmed","not First Time");
                new DataAsyncTask().execute();
            }***/

        }
        catch (Exception e)
        {
            Log.i("Ahmed",e.getMessage());
        }












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


    class DataAsyncTask extends AsyncTask <Cursor,Void,Cursor>
    {
        private URL url;
        private String Json_Link = "https://api.myjson.com/bins/ijgo1",Json_String;
        private HttpsURLConnection httpsURLConnection;
        private JSONArray jsonArray,monuments;
        private JSONObject jsonObject,jsonObject2;
        @Override
        protected Cursor doInBackground(Cursor... eVoid) {
            try {
                if (FirstTime) {
                    return getData();
                }
                else {
                    return LoadData();
                }
            }
            catch (Exception e)
            {
                Log.i("Ahmed","DO in BackGround" +e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            cursor.moveToFirst();
            String s=getString(cursor.getColumnIndexOrThrow(Contract.Column_Photo_Cities));
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
        }

        private Cursor getData ()
        {
            try{
                url = new URL (Json_Link);
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                InputStream inputStream=httpsURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                Json_String = scanner.hasNext() ? scanner.next() : "";
                jsonArray = new JSONArray(Json_String);

                for (int i=0;i<jsonArray.length();i++)
                {
                    try {
                        jsonObject = jsonArray.getJSONObject(i);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Contract.Column_Name_Cities_En, jsonObject.getString("name_En"));
                        contentValues.put(Contract.Column_Name_Cities_Ar, jsonObject.getString("name_Ar"));
                        contentValues.put(Contract.Column_Info_Cities_En, jsonObject.getString("info_En"));
                        contentValues.put(Contract.Column_Info_Cities_En, jsonObject.getString("info_Ar"));
                        File my_folder = getExternalFilesDir("Enjoy_Egypt");
                        File file = new File(my_folder, jsonObject.getString("name_En") + ".jpg");
                        InputStream inputStream1 = new BufferedInputStream(new URL(jsonObject.getString("photo")).openStream());
                        FileOutputStream outputStream = new FileOutputStream(file);
                        byte[] data = new byte[1024];
                        int count = 0;
                        while ((count=inputStream1.read(data)) != 1) {
                            outputStream.write(data, 0, count);
                        }
                        inputStream1.close();
                        outputStream.close();
                        contentValues.put(Contract.Column_Photo_Cities, jsonObject.getString("name_En") + ".jpg");
                        getContentResolver().insert(Uri.parse(Contract.Table_Cities_Name), contentValues);
                    }
                    catch (Exception e)
                    {
                        Log.i("Ahmed","inside GetData" + e.getMessage());

                    }
                    monuments=jsonObject.getJSONArray("Monuments");
                    for (int j=0;j<monuments.length();j++)
                    {
                        jsonObject2=monuments.getJSONObject(j);
                        //insert the data in the second table of monuments
                    }
                }
                inputStream.close();
                httpsURLConnection.disconnect();
            }
            catch (Exception e)
            {
                Log.i("Ahmed","GetData \n" +e.getMessage());
            }

            return LoadData();
        }

        private Cursor LoadData ()
        {
            try {
                return getContentResolver().query(Uri.parse(Contract.Table_Uri_Cities),null,null,null,null);
            }
            catch (Exception e)
            {
                Log.i("Ahmed","Load Data" + e.getMessage());
            }



            return null;
        }
    }

    public boolean isNetworkAvailable(Context context) {
        //boolean function to check the internet connection
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
