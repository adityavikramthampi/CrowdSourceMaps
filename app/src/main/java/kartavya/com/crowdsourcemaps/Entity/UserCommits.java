package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by HP on 2/27/2018.
 */

public class UserCommits {

    private String UserID;
    private int NoOfValidations;
    private int NoOfImagesAdded;
    private int NoOfQuestionsAnswered;
    private int NoOfPlacesAdded;

    public UserCommits() {
    }

    public UserCommits(String userID, int noOfValidations, int noOfImagesAdded, int noOfQuestionsAnswered, int noOfPlacesAdded) {
        UserID = userID;
        NoOfValidations = noOfValidations;
        NoOfImagesAdded = noOfImagesAdded;
        NoOfQuestionsAnswered = noOfQuestionsAnswered;
        NoOfPlacesAdded = noOfPlacesAdded;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getNoOfValidations() {
        return NoOfValidations;
    }

    public void setNoOfValidations(int noOfValidations) {
        NoOfValidations = noOfValidations;
    }

    public int getNoOfImagesAdded() {
        return NoOfImagesAdded;
    }

    public void setNoOfImagesAdded(int noOfImagesAdded) {
        NoOfImagesAdded = noOfImagesAdded;
    }

    public int getNoOfQuestionsAnswered() {
        return NoOfQuestionsAnswered;
    }

    public void setNoOfQuestionsAnswered(int noOfQuestionsAnswered) {
        NoOfQuestionsAnswered = noOfQuestionsAnswered;
    }

    public int getNoOfPlacesAdded() {
        return NoOfPlacesAdded;
    }

    public void setNoOfPlacesAdded(int noOfPlacesAdded) {
        NoOfPlacesAdded = noOfPlacesAdded;
    }
}
