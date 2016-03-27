package com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.marmup2.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezmoizy.abdulmoiz.warmup2.R;
import com.ezmoizy.abdulmoiz.warmup2.com.ezmoizy.abdulmoiz.warmup2.mapNavigator.Navigator;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
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

    //This method is called once the Map is ready to be interacted with
    //Once ready the map is centered onto McMaster and zoomed in
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //McMaster University coordinates: 43.261926,-79.919182
        LatLng center = new LatLng(43.261926,-79.919182);

        // Move the camera instantly to location with a zoom of 8
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 17));

    }

    //Adds a new marker and shows that markers info window
    public Marker addNewMarker(Double lat, Double lng, String title){
        Marker newMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(title));
        newMarker.showInfoWindow();
        return newMarker;
    }

    public void removeAllMarkers(){
        mMap.clear();
    }

    //A public method allows you to center the map at a location
    public void centerMap(Double lat, Double lng){
        LatLng center = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 17));
    }

    // Testing out map navigator library
    //   This method will assume your starting location is at John Hodgins Engineering Building (JHE)
    //   and that your end location is Mills Library. It will draw a path following available walking routes
    //   from outlined starting and end locations
    public void navigatorTest(){
        removeAllMarkers();
        LatLng startLocation = new LatLng(43.260870, -79.920115);
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(43.260870, -79.920115))
                .radius(10)
                .strokeColor(Color.GREEN));

        LatLng endLocation = new LatLng(43.265071, -79.916544);
        LatLng middle = new LatLng((startLocation.latitude + endLocation.latitude)/2,(startLocation.longitude + endLocation.longitude) / 2 );
        mMap.addMarker(new MarkerOptions().position(endLocation).title("Destination"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(endLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 17));

        Navigator nav = new Navigator(mMap,startLocation,endLocation);
        nav.setMode(3,1);
        nav.findDirections(true);
    }



}