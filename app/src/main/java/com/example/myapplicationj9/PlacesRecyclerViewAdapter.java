package com.example.myapplicationj9;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.places.Place;

import java.util.List;

public class PlacesRecyclerViewAdapter  extends
        RecyclerView.Adapter<PlacesRecyclerViewAdapter.ViewHolder> {

    private List<Place> placesList;
    private Context context;

    public PlacesRecyclerViewAdapter(List<Place> list, Context ctx) {
        placesList = list;
        context = ctx;
    }
    @Override
    public int getItemCount() {
        return placesList.size();
    }

    @Override
    public PlacesRecyclerViewAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_item, parent, false);

        PlacesRecyclerViewAdapter.ViewHolder viewHolder =
                new PlacesRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int itemPos = position;
        final Place place = placesList.get(position);
        holder.name.setText(place.getName());
        holder.address.setText(place.getAddress());
        holder.phone.setText(place.getPhoneNumber());
        if(place.getWebsiteUri() != null){
            holder.website.setText(place.getWebsiteUri().toString());
        }

        if(place.getRating() > -1){
            holder.ratingBar.setNumStars((int)place.getRating());
        }else{
            holder.ratingBar.setVisibility(View.GONE);
        }

        holder.viewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOnMap(place);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView address;
        public TextView phone;
        public TextView website;
        public RatingBar ratingBar;

        public Button viewOnMap;

        public ViewHolder(View view) {

            super(view);

            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.address);
            phone = view.findViewById(R.id.phone);
            website = view.findViewById(R.id.website);
            ratingBar = view.findViewById(R.id.rating);

            viewOnMap = view.findViewById(R.id.view_map_b);
        }
    }

    private void showOnMap(Place place){
        FragmentManager fm = ((CurrentLocationNearByPlacesActivity)context)
                .getSupportFragmentManager();

        Bundle bundle=new Bundle();
        bundle.putString("name", (String)place.getName());
        bundle.putString("address", (String)place.getAddress());
        bundle.putDouble("lat", place.getLatLng().latitude);
        bundle.putDouble("lng", place.getLatLng().longitude);

        PlaceOnMapFragment placeFragment = new PlaceOnMapFragment();
        placeFragment.setArguments(bundle);

        fm.beginTransaction().replace(R.id.map_frame, placeFragment).commit();
    }
}
