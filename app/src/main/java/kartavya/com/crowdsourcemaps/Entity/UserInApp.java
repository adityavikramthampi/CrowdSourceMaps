package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by karta on 27-02-2018.
 */

public class UserInApp {

    private int UserID;

    public UserInApp() {
    }

    public UserInApp(int userID) {
        UserID = userID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
