package kartavya.com.crowdsourcemaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kartavya.com.crowdsourcemaps.Entity.Place;

public class Questions extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Place newPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("places");

        newPlace = (Place)getIntent().getSerializableExtra("Place");
    }

    public void addPlaceToDatabase(View v) {
        databaseReference.push().setValue(newPlace);
    }
}
