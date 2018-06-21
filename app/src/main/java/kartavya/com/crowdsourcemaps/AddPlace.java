package kartavya.com.crowdsourcemaps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kartavya.com.crowdsourcemaps.Entity.Place;

public class AddPlace extends AppCompatActivity {
    private static final int PHOTO_PICKER = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    EditText nameOfPlace;
    EditText placeReview;
    List<String> imageURIs;
    LatLng currPlaceLocation;
    EditText timings;
    EditText rating;

    Button mPhotoPickerButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage mFirebaseStorage;
    StorageReference mChatPhotosStorageReference;
    Uri imageUri;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        progressDialog = new ProgressDialog(AddPlace.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Uploading image, please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //progressDialog.show();

        nameOfPlace = (EditText)findViewById(R.id.newPlaceName);
        placeReview = (EditText)findViewById(R.id.newPlaceReview);
        timings = (EditText)findViewById(R.id.timings);
        rating = (EditText)findViewById(R.id.ratings);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("places");

        mFirebaseStorage = FirebaseStorage.getInstance();
        mChatPhotosStorageReference = mFirebaseStorage.getReference().child("place_photos");

        imageURIs = new ArrayList<String>();

        currPlaceLocation = new LatLng(getIntent().getDoubleExtra("Latitude",-0),getIntent().getDoubleExtra("Longitude",-0));

        //Selecting photos from system
        mPhotoPickerButton = (Button) findViewById(R.id.newPlaceImage);
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PHOTO_PICKER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PHOTO_PICKER && resultCode == RESULT_OK){
            progressDialog.show();
            Uri selectedImageUri = data.getData();
            //Toast.makeText(this, selectedImageUri.getLastPathSegment(), Toast.LENGTH_SHORT).show();
            StorageReference photoRef = mChatPhotosStorageReference.child(selectedImageUri.getLastPathSegment());

            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Uri downloadURL = taskSnapshot.getDownloadUrl();
                    imageURIs.add(downloadURL.toString());
                    Toast.makeText(AddPlace.this, imageURIs.toString(), Toast.LENGTH_LONG).show();
//                    FriendlyMessage friendlyMessage = new FriendlyMessage(null, mUsername, downloadURL.toString());
//                    mMessagesDatabaseReference.push().setValue(friendlyMessage);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add) {

            Place newPlace = new Place(nameOfPlace.getText().toString(),
                    currPlaceLocation,
                    imageURIs.toString(),
                    timings.getText().toString(),
                    Integer.parseInt(rating.getText().toString()),
                    placeReview.getText().toString()
            );

            databaseReference.push().setValue(newPlace);

            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
