package com.example.gonzalo.ud6_a14gonzaloiv;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener{

    // Google Maps variables
    private GoogleMap mMap;
    // Geolocation variables
    LocationManager locManager;
    LocationProvider locProv;
    List<String> listProviders;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Geolocation
        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        locProv = locManager.getProvider(LocationManager.GPS_PROVIDER);
        listProviders = locManager.getAllProviders();
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 ,500, (LocationListener) this);
    }

    // Method for the map once ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions()
                .position(mMap.getCameraPosition().target)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon)));
    }

    // LocationListener interface methods
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Nova posición: " + location.getLatitude() + ", " + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
