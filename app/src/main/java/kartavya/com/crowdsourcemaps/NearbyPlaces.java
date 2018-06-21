package kartavya.com.crowdsourcemaps;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import kartavya.com.crowdsourcemaps.Entity.LatLng;
import kartavya.com.crowdsourcemaps.Entity.Place;

public class NearbyPlaces extends AppCompatActivity {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mMessagesDatabaseReference;
    ChildEventListener mChildEventListener;
    MessageAdapter mMessageAdapter;

    private ListView mMessageListView;
    List<Place> places;

    double userLatitude;
    double userLongitude;
    int radius;

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (earthRadius * c);

        return dist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("places");

        Intent intentSelf = getIntent();
        radius = intentSelf.getIntExtra("radius",10000);
        userLatitude = intentSelf.getDoubleExtra("latitude",0);
        userLongitude = intentSelf.getDoubleExtra("longitude",0);

        mMessageListView = findViewById(R.id.messageListView);
        mMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Place currPlace = places.get(i);

                Intent intent = new Intent(NearbyPlaces.this,PlaceDetails.class);

                intent.putExtra("latitude",currPlace.getLat());
                intent.putExtra("longitude",currPlace.getLongi());
                intent.putExtra("imageURI",currPlace.getImageURIs());
                intent.putExtra("review",currPlace.getPlaceReview());
                intent.putExtra("rating",currPlace.getPlaceRating());
                intent.putExtra("timings",currPlace.getTimings());
                intent.putExtra("placeName",currPlace.getPlaceName());

                startActivity(intent);
            }
        });

        places = new ArrayList<>();

        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, places);
        mMessageListView.setAdapter(mMessageAdapter);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Place place = dataSnapshot.getValue(Place.class);
                double placeLat = place.getLat();
                double placeLong = place.getLongi();
                double distance = getDistance(userLatitude,userLongitude,placeLat,placeLong);

                if(distance<radius)
                    mMessageAdapter.add(place);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
    }
}
