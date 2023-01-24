package com.example.myfirstorienteeringapp;

import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myfirstorienteeringapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private Marker marker;

    private final LatLng waypoint_start = new LatLng(54.589946051431504, -5.915203594731079);
    private final LatLng waypoint_one = new LatLng(54.5874609815581, -5.917041440036081);
    private final LatLng waypoint_two = new LatLng(54.582719324299, -5.920816804487261);
    private final LatLng waypoint_three = new LatLng(54.58232876421499, -5.916697026827998);
    private final LatLng waypoint_four = new LatLng(54.5802451580488, -5.9170784556896345);
    private final LatLng waypoint_five = new LatLng(54.580036821083354, -5.913632481954809);
    private final LatLng waypoint_six = new LatLng(54.58378672350581, -5.9120210550159635);
    private final LatLng waypoint_seven = new LatLng(54.58708797667794, -5.914132381043083);
    private final LatLng waypoint_finish = new LatLng(54.58956537625695, -5.911469836964723);

    // creating array list for adding all our waypoints.
    private ArrayList<LatLng> waypointArraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Add 8 waypoints to a map of Ormeau Park Belfast
        waypointArraylist = new ArrayList<>();
        waypointArraylist.add(waypoint_start);
        waypointArraylist.add(waypoint_one);
        waypointArraylist.add(waypoint_two);
        waypointArraylist.add(waypoint_three);
        waypointArraylist.add(waypoint_four);
        waypointArraylist.add(waypoint_five);
        waypointArraylist.add(waypoint_six);
        waypointArraylist.add(waypoint_seven);
        waypointArraylist.add(waypoint_finish);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap = googleMap;
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(20.0f);

        for (int i = 0; i < waypointArraylist.size(); i++) {


            // below line is use to add marker to each location of our array list.
            if (i == 0) {
                // set first marker as start
                marker = mMap.addMarker(new MarkerOptions().position(waypointArraylist.get(i)).title("Start")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

            } else if (i < 8) {
                marker = mMap.addMarker(new MarkerOptions().position(waypointArraylist.get(i)).title(String.valueOf(i)));
                marker.setTag(String.valueOf(i));
            } else {
                // set last marker as finish
                marker = mMap.addMarker(new MarkerOptions().position(waypointArraylist.get(i)).title("Finish")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }


            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(waypointArraylist.get(i)));

        }

        PolylineOptions polylineOptions=new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(4);
        polylineOptions.addAll(waypointArraylist);
        mMap.addPolyline(polylineOptions);

    }
}
