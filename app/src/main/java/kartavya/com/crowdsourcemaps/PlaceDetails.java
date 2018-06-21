package kartavya.com.crowdsourcemaps;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlaceDetails extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    LatLng latLng;
    String review;
    int rating;
    String imageURI;
    String timings;
    String placeName;

    ImageView placeImage;
    TextView reviewView;
    TextView showTime;

    RatingBar ratingBar;

    public void chatBegin(View view){
        Context context = getApplicationContext();
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage("com.pshkrh.pkchat");
            if (i == null) {
                Toast.makeText(context, "Chat app not found in the phone, please install and then try again", Toast.LENGTH_LONG).show();
                return;
                //throw new ActivityNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            i.putExtra("groupCode",placeName);
            context.startActivity(i);
            return;
        } catch (ActivityNotFoundException e) {
            return;
        }
    }

    public void goToMaps(View view){
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latLng.latitude+","+latLng.longitude);

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        reviewView = findViewById(R.id.addReview);
        showTime = findViewById(R.id.showTime);
        ratingBar = findViewById(R.id.ratingBar);
        placeImage = findViewById(R.id.placeImage);

        Intent intent = getIntent();
        latLng = new LatLng(intent.getDoubleExtra("latitude",19.0639001),
                intent.getDoubleExtra("longitude",72.8349828));
        rating = intent.getIntExtra("rating",0);
        review = intent.getStringExtra("review");
        imageURI = intent.getStringExtra("imageURI");
        timings = intent.getStringExtra("timings");
        placeName = intent.getStringExtra("placeName");

        imageURI = imageURI.substring(1,imageURI.length());

        Glide.with(placeImage.getContext())
                .load(imageURI)
                .into(placeImage);
        reviewView.setText(review);
        showTime.setText(timings);
        ratingBar.setRating(rating);

        Toast.makeText(this, imageURI, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, review, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, ""+rating, Toast.LENGTH_SHORT).show();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng currentPlace = new LatLng(latLng.latitude, latLng.longitude);

        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(currentPlace)
                .title("Current place")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPlace,15));//Zoom level varies from 1 to 20
    }
}
