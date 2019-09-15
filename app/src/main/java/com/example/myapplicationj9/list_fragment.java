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
        ArrayList<Sight> sights=new ArrayList<Sight>();
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

        cities.get(0).setLat(30.06263);
        cities.get(0).setLng(31.24967);
        cities.add(new City("Alex",sights,3,"km",R.drawable.m3,29.97371 ,32.52627));
        cities.add(new City("Cairo",sights,3,"km",R.drawable.m2,30.06263  ,31.24967));
        cities.add(new City("Al-Fayyuum",sights,3,"km",R.drawable.m4,29.30995 ,30.8418));
        cities.add(new City("Sohag",sights,3,"km",R.drawable.m1,26.55695 ,31.69478));
        cities.add(new City("Asyut",sights,3,"km",R.drawable.m2,27.18096 ,31.18368));
        cities.add(new City("Luxur",sights,3,"km",R.drawable.m3));
        cities.add(new City("New Capital",sights,3,"km",R.drawable.m4));
        cities.add(new City("whatever else",sights,3,"km",R.drawable.m1));

        CityAdapter adapter=new CityAdapter(getContext(),cities);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        activity.onItemSelected(position);
    }
}
