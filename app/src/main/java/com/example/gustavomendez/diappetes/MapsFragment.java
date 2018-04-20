package com.example.gustavomendez.diappetes;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener{

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location location;
    private Marker marker;
    private final int REQUEST_LOCATION_CODE = 99;
    private RequestQueue mQueue;


    public MapsFragment() {
        // Required empty public constructor
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);

        //mQueue= VolleySingleton.getInstance(this).getRequestQueue();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            googleApiClient = new GoogleApiClient.Builder(this).
                    addConnectionCallbacks(this).
                    addOnConnectionFailedListener(this).
                    addApi(LocationServices.API).build();
            googleApiClient.connect();
            mMap.setMyLocationEnabled(true);

        }
        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        this.location = location;
        if(marker!= null){
            marker.remove();

        }
        LatLng latLng = new LatLng(lat, lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title("Aqui Mero")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_ORANGE));
        this.marker=mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));
        if(googleApiClient != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,this);

        }


    }
    private String getUrlString(){
        return "https://raw.githubusercontent.com/lutangar/cities.json/master/cities.json";
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     *
     private String getJSON() {
     try {
     InputStream inputStream = this.getActivity().getAssets().open("lisgaespanola.json");
     int s = inputStream.available();
     byte[] archivo = new byte[s];
     inputStream.read(archivo);
     inputStream.close();
     return new String(archivo);
     } catch(IOException e) {
     e.printStackTrace();
     }
     return "";
     try {
     JSONObject jsonObject = new JSONObject(getJSON());
     JSONArray jsonArray = jsonObject.getJSONArray("rounds");
     for (int i = 0; i< jsonArray.length(); i++){
     JSONObject jsonObject1 = jsonArray.getJSONObject(i);
     JSONArray matches = jsonObject1.getJSONArray("matches");
     for (int j = 0; j< matches.length(); j++){
     JSONObject unMatch = matches.getJSONObject(j);
     Match m = new Match();
     m.fecha = unMatch.getString("date");
     m.equipo01 = unMatch.getJSONObject("team1").getString("name");
     m.equipo02 = unMatch.getJSONObject("team2").getString("name");
     m.marcador01 = unMatch.getInt("score1");
     m.marcador02 = unMatch.getInt("score1");
     adapter.add(m);

     }
     }
     } catch (JSONException e) {
     e.printStackTrace();
     }
     }
     */
}
