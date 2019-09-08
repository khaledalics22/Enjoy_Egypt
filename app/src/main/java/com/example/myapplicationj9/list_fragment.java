package com.example.myapplicationj9;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class list_fragment extends ListFragment {

    public static ArrayList<City> cities;
    public list_fragment() {
        // Required empty public constructor
    }
    private listItem activity;
    public  interface listItem{
        void onItemSelected(int index);
        void showBackArrow();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity=(listItem)context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Comment> comments=new ArrayList<Comment>();
        ArrayList<String> sights=new ArrayList<String>();
        sights.add("Cairo Tower");
        sights.add("bla bla");
        comments.add(new Comment("ahmed","this city is amazing" ));
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
                "sadf;hdsihoiekdsifjllasdofla"));


        cities.add(new City("Giza",sights,3,"km",R.drawable.m2));
        cities.add(new City("whatever else",sights,3,"km",R.drawable.m3));
        cities.add(new City("Cairo",sights,3,"km",R.drawable.m4));
        cities.add(new City("Giza",sights,3,"km",R.drawable.m1));
        cities.add(new City("whatever else",sights,3,"km",R.drawable.m2));
        cities.add(new City("Cairo",sights,3,"km",R.drawable.m3));
        cities.add(new City("Giza",sights,3,"km",R.drawable.m4));
        cities.add(new City("whatever else",sights,3,"km",R.drawable.m1));

        CityAdapter adapter=new CityAdapter(getContext(),cities);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        activity.onItemSelected(position);
    }
}
