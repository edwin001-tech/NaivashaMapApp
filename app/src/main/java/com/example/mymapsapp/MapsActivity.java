package com.example.mymapsapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MAPS";
    private GoogleMap mMap;
    private LatLng mountEverest = new LatLng(28.001377, 86.928129);
    private LatLng mountKilimanjaro = new LatLng(-3.075558, 37.344363);
    private LatLng theAlps = new LatLng(47.368955, 9.702579);

    //Todo: Create Markers for each mountain
    private Marker everestMarker;
    private Marker kilimanjaroMarker;
    private Marker theAlpsMarker;

    private MarkerOptions everestOptions;
    private MarkerOptions theAlpsOptions;

    private List<MarkerOptions> markerOptionsList;

    private ArrayList<Marker> markerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        markerArrayList = new ArrayList<>();

        everestMarker = mMap.addMarker(new MarkerOptions()
               .position(mountEverest)
               .title("Mt. Everest")
               .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                markerArrayList.add(everestMarker);

        kilimanjaroMarker = mMap.addMarker(new MarkerOptions()
                .position(mountKilimanjaro)
                .title("Mt Kilimanjaro")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                 markerArrayList.add(kilimanjaroMarker);

        theAlpsMarker = mMap.addMarker(new MarkerOptions()
                .position(theAlps)
                .title("The Alps")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                 markerArrayList.add(theAlpsMarker);

        for (Marker marker : markerArrayList) {
            LatLng latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4)); // 1 - 20
            Log.d(TAG, "onMapReady: " + marker.getTitle());
        }

        // Add a marker in Sydney and move the camera
        /*LatLng naivasha = new LatLng(0.7172, 36.4310);

        mMap.addMarker(new MarkerOptions().position(naivasha).title("Marker in Naivasha")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        .alpha(0.9f));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(naivasha,8));*/
    }
}