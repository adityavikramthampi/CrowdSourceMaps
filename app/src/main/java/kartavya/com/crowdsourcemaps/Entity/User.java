package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by karta on 27-02-2018.
 */

public class User {

    private String UserID;
    private String UserName;
    private int UserEmailID;
    private String UserLastKnownLocation;

    public User() {
    }

    public User(String userID, String userName, int userEmailID, String userLastKnownLocation) {
        UserID = userID;
        UserName = userName;
        UserEmailID = userEmailID;
        UserLastKnownLocation = userLastKnownLocation;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getUserEmailID() {
        return UserEmailID;
    }

    public void setUserEmailID(int userEmailID) {
        UserEmailID = userEmailID;
    }

    public String getUserLastKnownLocation() {
        return UserLastKnownLocation;
    }

    public void setUserLastKnownLocation(String userLastKnownLocation) {
        UserLastKnownLocation = userLastKnownLocation;
    }
}
