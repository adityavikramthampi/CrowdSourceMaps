package kartavya.com.crowdsourcemaps.Entity;

/**
 * Created by HP on 2/27/2018.
 */

public class PlaceDateAndTime {

    private String WhenToVisit;

    public PlaceDateAndTime() {
    }

    public PlaceDateAndTime(String whenToVisit) {
        WhenToVisit = whenToVisit;
    }

    public String getWhenToVisit() {
        return WhenToVisit;
    }

    public void setWhenToVisit(String whenToVisit) {
        WhenToVisit = whenToVisit;
    }
}
