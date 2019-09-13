package com.example.myapplicationj9.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CitiesProvider extends ContentProvider {
    private static final int Row_Cities = 3;
    private static final int Table_Cities = 5;
    private static final int Row_Monuments = 7;
    private static final int Table_Monuments = 11;
    private static final UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    DatabaseHelper db;
    SQLiteDatabase sql;
    int match;



    static {
        uriMatcher.addURI(Contract.Authority,Contract.Table_Cities_Name + "/*",Row_Cities);
        uriMatcher.addURI(Contract.Authority,Contract.Table_Monuments_Name + "/*",Row_Monuments);
        uriMatcher.addURI(Contract.Authority,Contract.Table_Cities_Name  ,Table_Cities);
        uriMatcher.addURI(Contract.Authority,Contract.Table_Monuments_Name ,Table_Monuments);
    }
    @Override
    public boolean onCreate() {
        db=new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String s1) {
        match = uriMatcher.match(uri);
        sql = db.getReadableDatabase();
        switch (match)
        {
            case Row_Cities :
                try {
                    String name = uri.getLastPathSegment();
                    Log.i("Ahmed", name +" query row cities");
                    selection = Contract.Column_Name_Cities_En + "=?";
                    selectionArgs = new String[] {name};
                    return sql.query(Contract.Table_Cities_Name,projection,selection,selectionArgs,null,null,null);
                }
                catch (Exception e)
                 {
                    Log.i("Ahmed",e.getMessage());
                 }


            case Row_Monuments :
                try {
                    String name = uri.getLastPathSegment();
                    Log.i("Ahmed", name +" query row monuments");
                    selection = Contract.Column_Name_Monuments_En + "=?";
                    selectionArgs = new String[] {name};
                    return sql.query(Contract.Table_Cities_Name,projection,selection,selectionArgs,null,null,null);
                }
                catch (Exception e)
                {
                    Log.i("Ahmed",e.getMessage());
                }
            case Table_Cities :
                try {
                    Log.i("Ahmed", "Table query Cities");
                    return sql.query(Contract.Table_Cities_Name,null,null,null,null,null,null);
                }
                catch (Exception e)
                {
                    Log.i("Ahmed",e.getMessage());
                }

            case Table_Monuments :
                try {
                    Log.i("Ahmed", "Table query Monuments");
                    return sql.query(Contract.Table_Monuments_Name,null,null,null,null,null,null);
                }
                catch (Exception e)
                {
                    Log.i("Ahmed",e.getMessage());
                }
            default:
                throw new IllegalArgumentException("Cannot query UNKNOWN URI "+uri);


        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        sql =db.getWritableDatabase();
        match = uriMatcher.match(uri);
        switch (match) {
            case Table_Cities:
                try {
                    Log.i("Ahmed", "Table Cities Insert");
                    sql.insert(Contract.Table_Cities_Name, null, contentValues);
                }
                catch (Exception e)
                {
                    Log.i("Ahmed","inside insert Table Cities " + e.getMessage());
                }
                return null;
            case Table_Monuments:
                try {
                    Log.i("Ahmed", "Table Monuments Insert");
                    sql.insert(Contract.Table_Monuments_Name, null, contentValues);
                }
                catch (Exception e)
                {
                    Log.i("Ahmed","inside insert Table Monuments " + e.getMessage());
                }
                return null;
            default:
                throw new IllegalArgumentException("Cannot query UNKNOWN URI "+uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
