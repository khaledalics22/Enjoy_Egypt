package com.example.myapplicationj9.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplicationj9.R;
import com.example.myapplicationj9.ui.tools.ContactAdapter;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity  {

    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        // Lookup the recyclerview in activity layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.my_recycler_view);





        // Initialize contacts
        // Create adapter passing in the sample user data
        contacts = createContactsList();
        ContactAdapter adapter = new ContactAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

    public static ArrayList<Contact> createContactsList() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact(R.drawable.m3,"text1"));
        contacts.add(new Contact(R.drawable.m1,"text2"));
        contacts.add(new Contact(R.drawable.m2,"text3"));
//////////////////////////////////////
        contacts.add(new Contact(R.drawable.m3,"text1"));
        contacts.add(new Contact(R.drawable.m1,"text2"));
        contacts.add(new Contact(R.drawable.m2,"text3"));


        return contacts;
    }

}

    /*private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

private int[] images={R.drawable.egy,R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.my_recycler_view);
         layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

    }
}
*/