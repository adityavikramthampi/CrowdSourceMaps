package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by HP on 2/27/2018.
 */

public class PlaceReview {

    private String UserID;
    private String PlaceID;
    private String Review;

    public PlaceReview() {
    }

    public PlaceReview(String userID, String placeID, String review) {
        UserID = userID;
        PlaceID = placeID;
        Review = review;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPlaceID() {
        return PlaceID;
    }

    public void setPlaceID(String placeID) {
        PlaceID = placeID;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }
}
