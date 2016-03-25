package com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.marmup2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezmoizy.abdulmoiz.warmup2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Moiz on 15/03/2016.
 */
public class GmapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment fragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.mapfrag);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //made new markers centered on universities
        Marker McMaster = addNewMarker(43.261926,-79.919182,"McMaster University");
        Marker UofT = addNewMarker(43.662653, -79.396356, "University of Toronto");
        Marker Wloo = addNewMarker(43.472040, -80.544804, "University of Waterloo");
        Marker Western = addNewMarker(43.009307, -81.273658, "Wuck Festern");

        // Move the camera instantly to location with a zoom of 8
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(McMaster.getPosition(), 8));

    }

    public Marker addNewMarker(Double lat, Double lng, String title){
        Marker newMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(title));
        return newMarker;
    }

    public void centerMap(Double lat, Double lng){
        LatLng center = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 17));
    }



}