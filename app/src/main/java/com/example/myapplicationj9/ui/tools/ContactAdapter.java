package com.example.myapplicationj9.ui.tools;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationj9.R;
import com.example.myapplicationj9.ui.Contact;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView txt;
        public ImageView image;
        public Button button;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            txt = (TextView)itemView.findViewById(R.id.t1);
            image=(ImageView)itemView.findViewById(R.id.i1);
            button = (Button)itemView.findViewById(R.id.b1);
        }

    }
    private ArrayList<Contact> mContacts;

    // Pass in the contact array into the constructor
    public ContactAdapter(ArrayList<Contact> contacts)
    {
        mContacts = contacts;
    }

    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.try1, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Contact contact = mContacts.get(position);

        // Set item views based on your views and data model
        //TextView textView = viewHolder.txt;
        viewHolder.txt.setText(contact.getText1());
        //Button button = viewHolder.button;
        viewHolder.button.setEnabled(true);
        viewHolder.button.setText("Detail");
        //ImageView imge=viewHolder.image;
        //imge.setImageURI(mContacts.get(position).uri);
        viewHolder.image.setImageResource(contact.getimg());



    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}