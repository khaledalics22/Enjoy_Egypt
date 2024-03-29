package com.example.myapplicationj9.Data;

import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.BaseColumns;


public class Contract implements BaseColumns {
    public static final String SharedPrefrances = "MySharedPreference";
    public static final String Language = "Language";
    public static final String Language_En = "English";
    public static final String Language_Ar = "Arabic";
    public static final String FirstTime = "FirstTime";
    public static final String Table_Cities_Name = "Cities";
    public static final String Table_Monuments_Name = "Info";

    public static final String Column_Photo_Cities = "photo_city";
    public static final String Column_Name_Cities_En = "city_en";
    public static final String Column_Info_Cities_En = "Info_en_city";
    public static final String Column_Name_Cities_Ar = "city_ar";
    public static final String Column_Info_Cities_Ar = "Info_ar";

    public static final String Column_Name_Monuments_En = "monuments_en";
    public static final String Column_Info_Monuments_En = "Info_en_monuments";
    public static final String Column_Name_Monuments_Ar = "monuments_ar";
    public static final String Column_Info_Monuments_Ar = "Info_ar_monuments";
    public static final String Column_Photo_Monuments = "photo_monuments";
    public static final String Column_Prize_En_Monuments = "prize_En";
    public static final String Column_Prize_Ar_Monuments = "prize_Ar";
    public static final String Column_City_Name_Monuments = "City_Monuments";

    public static final String Authority = "com.example.myapplicationj9";
    public static final String Row_Uri_Cities = "content://"+ Authority + "/" + Table_Cities_Name +"/";
    public static final String Row_Uri_Info = "content://"+ Authority + "/" + Table_Monuments_Name +"/";
    public static final String Table_Uri_Cities ="content://"+ Authority + "/" + Table_Cities_Name ;
    public static final String Table_Uri_Monuments = "content://"+ Authority + "/" + Table_Monuments_Name ;

}
