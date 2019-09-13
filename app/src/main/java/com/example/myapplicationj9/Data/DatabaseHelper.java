package com.example.myapplicationj9.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplicationj9.R;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_Name = "Egypt.db";
    private static final String Create_Method_Cities = "CREATE TABLE " + Contract.Table_Cities_Name + "("
            + Contract.Column_Name_Cities_En + " TEXT," + Contract.Column_Photo_Cities + " TEXT," +
            Contract.Column_Info_Cities_En + " TEXT ," + Contract.Column_Name_Cities_Ar + " TEXT,"  +
            Contract.Column_Info_Cities_Ar + " TEXT)";
    private static final String Drop_Table_Cities ="DROP IF EXISTS " +Contract.Table_Cities_Name ;

    public DatabaseHelper(Context context ) {
        super(context, Database_Name, null, 1);
    }



    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Method_Cities);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
