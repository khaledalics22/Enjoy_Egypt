package com.example.myapplicationj9.Data;

import android.content.SharedPreferences;

public class SharedPrefrance {
    SharedPreferences shp;
    boolean firstTime;
    String language ;
    public SharedPrefrance (SharedPreferences sharedPreferences)
    {
        shp=sharedPreferences;
        firstTime=shp.getBoolean(Contract.FirstTime,true);
        language=shp.getString(Contract.Language,Contract.Language_En);
    }

    public void setFirstTime(boolean firstTime,SharedPreferences sharedPreferences) {
        sharedPreferences.edit().putBoolean(Contract.FirstTime,firstTime).apply();
    }

    public void setLanguage(String language,SharedPreferences sharedPreferences) {
        sharedPreferences.edit().putString(Contract.Language,language).apply();
    }

    public String getLanguage() {
        return language;
    }

    public boolean isFirstTime() {
        return firstTime;
    }
}
