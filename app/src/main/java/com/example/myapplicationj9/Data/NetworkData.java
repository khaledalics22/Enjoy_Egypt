package com.example.myapplicationj9.Data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.example.myapplicationj9.Content;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.RequiresApi;

public class NetworkData
{
    private URL url;
    private String Json_Link = "https://api.myjson.com/bins/ijgo1",Json_String;
    private HttpsURLConnection httpsURLConnection;
    private JSONArray jsonArray,monuments;
    private JSONObject jsonObject,jsonObject2;
    private ContentResolver cr;

    public NetworkData (ContentResolver contentResolver)
    {
        cr=contentResolver;
    }

    public void getData ()
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
            jsonObject=jsonArray.getJSONObject(i);
             ContentValues contentValues=new ContentValues();
             contentValues.put(Contract.Column_Name_Cities_En,jsonObject.getString("name_En"));
             contentValues.put(Contract.Column_Name_Cities_Ar,jsonObject.getString("name_Ar"));
             contentValues.put(Contract.Column_Photo_Cities,jsonObject.getString("photo"));
             contentValues.put(Contract.Column_Info_Cities_En,jsonObject.getString("info_En"));
             contentValues.put(Contract.Column_Info_Cities_En,jsonObject.getString("info_Ar"));
             cr.insert(Uri.parse(Contract.Table_Cities_Name),contentValues);
             monuments=jsonObject.getJSONArray("Monuments");
             for (int j=0;j<monuments.length();j++)
             {
                 jsonObject2=monuments.getJSONObject(i);
                 //insert the data in the second table of monuments
             }
         }
         httpsURLConnection.disconnect();
    }
    catch (Exception e)
    {
        Log.i("Ahmed",e.getMessage());
    }


    }
}
