package kartavya.com.crowdsourcemaps;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;

    private ProgressDialog progressDialog;

    private static final int RC_SIGN_IN = 123;

    Location placeLocation;

    SeekBar inputRadius;
    TextView showRadius;

    int radius;
    static final int maxRadiusValue = 15;

    public void addPlace(View view) {
        Intent intent = new Intent(this, AddPlace.class);

        intent.putExtra("Latitude", placeLocation.getLatitude());
        intent.putExtra("Longitude", placeLocation.getLongitude());

        startActivity(intent);
    }

    public void nearbyPlace(View view) {
        Intent intent = new Intent(this, NearbyPlaces.class);

        intent.putExtra("latitude",placeLocation.getLatitude());
        intent.putExtra("longitude",placeLocation.getLongitude());
        intent.putExtra("radius",radius);

        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1000, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Getting location, please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

//        startActivity(new Intent(this,PlaceDetails.class));
//        finish();

//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {

        }
//
//        mFusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            // Logic to handle location object
//                            Toast.makeText(MainActivity.this, "Got location "+location, Toast.LENGTH_SHORT).show();
//                        }else Toast.makeText(MainActivity.this, "La di la", Toast.LENGTH_SHORT).show();
//                    }
//                });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        onDragSeekBar();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                placeLocation = location;
                // Add a marker in Sydney, Australia,
                // and move the map's camera to the same location.
                LatLng currentPlace = new LatLng(location.getLatitude(), location.getLongitude());

                //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(currentPlace)
                        .title("Current place")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPlace,15));//Zoom level varies from 1 to 20

                progressDialog.dismiss();
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
        };

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            LatLng currentPlace = new LatLng(location.getLatitude(), location.getLongitude());
//
//            //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//
//            mMap.addMarker(new MarkerOptions().position(currentPlace)
//                    .title("Current place")
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPlace,15));//Zoom level varies from 1 to 20
        }
    }

    @SuppressLint("SetTextI18n")
    public void onDragSeekBar () {

        inputRadius = findViewById(R.id.radiusSeekBar);
        showRadius = findViewById(R.id.showRadius);

        inputRadius.setMax(maxRadiusValue);

        showRadius.setText("Selected : " + inputRadius.getProgress() + " km");

        inputRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                radius = inputRadius.getProgress()*1000;
                showRadius.setText("Selected : " + inputRadius.getProgress() + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                radius = inputRadius.getProgress()*1000;
                showRadius.setText("Selected : " + inputRadius.getProgress() + " km");
            }
        });
    }
}
