package sinhalacoder.com.wedagedara.models;
/*---------------------o----------o----------------------
 * Created by Blasanka on 27,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

public class Place {
    // naming conventions are specified to match with firebase realtime database data format to avoid abnormal behaviours
    private String place_name;
    private String location;
    private String duration;
    private String distance;
    private long contact;

    public Place() {
    }

    public Place(String place_name, String location, String duration, String distance, long contact) {
        this.place_name = place_name;
        this.location = location;
        this.duration = duration;
        this.distance = distance;
        this.contact = contact;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }
}
