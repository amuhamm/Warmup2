package com.ezmoizy.abdulmoiz.warmup2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker on McMaster U and move the camera
        LatLng mcmaster = new LatLng(43.261926, -79.919182);

        Marker mcmaster_marker = mMap.addMarker(new MarkerOptions().position(mcmaster).title("McMaster University"));

        // Move the camera instantly to location with a zoom of 17.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcmaster, 17));

        //made a new marker centered on McMaster
        Marker mcmarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.261926, -79.919182))
                .title("McMaster University"));
    }
}
