package kartavya.com.crowdsourcemaps.Entity;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2/27/2018.
 */

public class Place{
    private String placeName;
    //private LatLng placeCoordinates;
    private double lat;
    private double longi;
    private int placeRating;
    //private List<String> imageURIs;
    private String imageURIs;
    private String timings;
    private String placeReview;

    public Place() {
    }

    public Place(String placeName, LatLng placeCoordinates, String imageURI, String timings, int placeRating, String placeReview) {
        this.placeName = placeName;
        //this.placeCoordinates = placeCoordinates;
        this.lat = placeCoordinates.latitude;
        this.longi = placeCoordinates.longitude;
        this.imageURIs = imageURI;
        this.timings = timings;
        this.placeRating = placeRating;
        this.placeReview = placeReview;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public String getImageURIs() {
        return imageURIs;
    }

    public void setImageURIs(String imageURIs) {
        this.imageURIs = imageURIs;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public int getPlaceRating() {
        return placeRating;
    }

    public void setPlaceRating(int placeRating) {
        this.placeRating = placeRating;
    }

    public String getPlaceReview() {
        return placeReview;
    }

    public void setPlaceReview(String placeReview) {
        this.placeReview = placeReview;
    }
}
