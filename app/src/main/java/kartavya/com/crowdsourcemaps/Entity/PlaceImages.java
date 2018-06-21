package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by HP on 2/27/2018.
 */

public class PlaceImages {

    private String ImageID;
    private String PlaceID;
    private String PlaceImagesURL;

    public PlaceImages() {
    }

    public PlaceImages(String imageID, String placeID, String placeImagesURL) {
        ImageID = imageID;
        PlaceID = placeID;
        PlaceImagesURL = placeImagesURL;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public String getPlaceID() {
        return PlaceID;
    }

    public void setPlaceID(String placeID) {
        PlaceID = placeID;
    }

    public String getPlaceImagesURL() {
        return PlaceImagesURL;
    }

    public void setPlaceImagesURL(String placeImagesURL) {
        PlaceImagesURL = placeImagesURL;
    }
}
