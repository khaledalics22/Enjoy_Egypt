package com.example.myapplicationj9.ui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Contact {
    private ImageView imv;
    private Button but;
    private String tx,tm;
    public Uri uri;
    private Drawable drw;
    private int intg;

    public Contact(int in, String t) {
//tm=im;
//but=bu;
tx=t;
//drw=dr;
  intg=in;
    }

    public int getimg() {
        return intg;
    }

    public Button getbutton() {
        return but;
    }

    public String getText1() {
        return tx;
    }

}
