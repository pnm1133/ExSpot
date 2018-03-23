package com.example.nguyephan.findshopspot.presentation.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguyephan.findshopspot.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnPosition;

    private GoogleMap mGoogleMap;
    private CameraPosition mCameraPosition;

    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetactionClient;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    //location mac dinh , zoom mac dinh  ==> dc su dung khi location permission not grant(cap phep)
    private static final LatLng mDefaultLocation = new LatLng(10.8221723, 106.6347005);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSION_REQUEST_ACCESS_FIND_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // vi tri dia ly , noi ma device hien tai dang located, la vi last-known location dc retrieved tu FusedLocationProviderClient
    private Location mLastKnownLocation;

    // keys for storing activity state
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // Dc su dung trong luc chon current place
    private static final int M_MAX_ENTRIES = 5;
    private String[] mLikelyPlaceNames;
    private String[] mLikelyPlaceAddresses;
    private String[] mLikelyPlaceAttributions;
    private LatLng[] mLikelyPlaceLatLngs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // nhan lai location va camera position from saved instance state
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        setContentView(R.layout.activity_main);

        mGeoDataClient = Places.getGeoDataClient(this, null);
        mPlaceDetactionClient = Places.getPlaceDetectionClient(this, null);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

        btnPosition = findViewById(R.id.btn_position);
        btnPosition.setOnClickListener(this);

    }

    // save lai state cua camera va location khi activity vao onPause()
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        if (mGoogleMap != null) {
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            outState.putParcelable(KEY_CAMERA_POSITION, mGoogleMap.getCameraPosition());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
//        LatLng sysney = new LatLng(-33.852, 151.211);
//        LatLng sysney2 = new LatLng(-33.840, 151.210);
//        googleMap.addMarker(new MarkerOptions().position(sysney).title("Mark in sydney"));
//        googleMap.addMarker(new MarkerOptions().position(sysney2).title("Mark in sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sysney2, 15f));

        mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_infor_content, null);

                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });

        getLocationPermission();

        updateLocationUi();

        getDeviceLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSION_REQUEST_ACCESS_FIND_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
                break;
        }

        updateLocationUi();

    }

    private void updateLocationUi() {
        if (mGoogleMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
                Log.e("Exception %s", "Permission granted");
            } else {
                mGoogleMap.setMyLocationEnabled(false);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                Log.e("Exception %s", "Permission  dont granted");
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception %", e.getMessage());
        }
    }

    private void getDeviceLocation() {

        try {
            if(mLocationPermissionGranted){
                Task<Location> locationTask = mFusedLocationProviderClient.getLastLocation();
                if(locationTask == null){
                    Log.e(TAG,"location null");
                }else {
                    locationTask.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful()&& task.getResult() != null) {
                                mLastKnownLocation = task.getResult();
                                LatLng currentLatLng = new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude());
                                mGoogleMap.moveCamera(CameraUpdateFactory
                                        .newLatLngZoom(currentLatLng,DEFAULT_ZOOM));
                                Log.e(TAG,String.valueOf(currentLatLng.latitude)+","+String.valueOf(currentLatLng.longitude));
                                mGoogleMap.addMarker(new MarkerOptions().position(currentLatLng).title("current position"));
                            } else {
                                Log.e(TAG, "Current location is null. Using defaults.");
                                Log.e(TAG, "Exception: %addOnCompleteListener", task.getException());
                                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation,DEFAULT_ZOOM));
                                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                                mGoogleMap.addMarker(new MarkerOptions().position(mDefaultLocation).title("current position"));
                            }
                        }
                    });
                }

            }
        } catch (SecurityException e) {
            Log.e("Exception %s", e.getMessage());
        }
    }

    public void getLocationPermission() {
        //Check permission location
        //if true  => granted location permission  = true
        //if false => requestPersissoins
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG,"permission granted");
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_ACCESS_FIND_LOCATION);
            mLocationPermissionGranted = false;
            Log.e(TAG,"permission  not granted then show dialog");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_position :
                showCurrentPlace();
                break;
        }
    }

    private void showCurrentPlace() {
        if(mGoogleMap == null){
            return;
        }
        Log.e(TAG,"showCurrentPlace");
        try{
            if(mLocationPermissionGranted){
                Task<PlaceLikelihoodBufferResponse> responseTask = mPlaceDetactionClient.getCurrentPlace(null);
                responseTask.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                        if(task.isSuccessful() && task.getResult() != null){
                            PlaceLikelihoodBufferResponse likelihoods = task.getResult();
                            int count;
                            if(likelihoods.getCount() < M_MAX_ENTRIES){
                                count = likelihoods.getCount();
                            }else {
                                count = M_MAX_ENTRIES;
                            }

                            int i = 0;
                            mLikelyPlaceNames = new String[count];
                            mLikelyPlaceAddresses = new String[count];
                            mLikelyPlaceAttributions = new String[count];
                            mLikelyPlaceLatLngs = new LatLng[count];

                            for(PlaceLikelihood placeLikelihood : likelihoods){
                                mLikelyPlaceNames[i] = (String) placeLikelihood.getPlace().getName();
                                mLikelyPlaceAddresses[i] = (String) placeLikelihood.getPlace()
                                        .getAddress();
                                mLikelyPlaceAttributions[i] = (String) placeLikelihood.getPlace()
                                        .getAttributions();
                                mLikelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                                i++;
                                if (i > (count - 1)) {
                                    break;
                                }
                            }
                            likelihoods.release();
                            openPlacesDialog();
                        }
                        else {
                            Log.e(TAG, "Exception: %showCurrentPlace", task.getException());
                        }
                    }
                });
            }else {
                Log.i(TAG, "The user did not grant location permission.");
                mGoogleMap.addMarker(new MarkerOptions()
                        .title(getString(R.string.default_info_title))
                        .position(mDefaultLocation)
                        .snippet(getString(R.string.default_info_title)));
                getLocationPermission();

            }
        }catch (SecurityException e){
            //
        }
    }

    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = mLikelyPlaceLatLngs[which];
                String markerSnippet = mLikelyPlaceAddresses[which];
                if (mLikelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + mLikelyPlaceAttributions[which];
                }

                // Add a marker for the selected place, with an info window
                // showing information about that place.
                mGoogleMap.addMarker(new MarkerOptions()
                        .title(mLikelyPlaceNames[which])
                        .position(markerLatLng)
                        .snippet(markerSnippet));

                // Position the map's camera at the location of the marker.
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        DEFAULT_ZOOM));
            }
        };

        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.pick_place)
                .setItems(mLikelyPlaceNames, listener)
                .show();
    }
}
