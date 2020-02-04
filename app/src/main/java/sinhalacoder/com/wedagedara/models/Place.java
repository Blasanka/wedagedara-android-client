package sinhalacoder.com.wedagedara.models;
/*---------------------o----------o----------------------
 * Created by Blasanka on 27,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    // naming conventions are specified to match with firebase realtime database data format to avoid abnormal behaviours
    private String name;
    private String duration;
    private String distance;
    private String image_url;
    private String description;
    private String contact;

    public Place() {
    }

    public Place(String name, String duration, String distance, String image_url, String description, String contact) {
        this.name = name;
        this.duration = duration;
        this.distance = distance;
        this.image_url = image_url;
        this.description = description;
        this.contact = contact;
    }

    public String getPlace_name() {
        return name;
    }

    public void setPlace_name(String name) {
        this.name = name;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected Place(Parcel in) {
        name = in.readString();
        duration = in.readString();
        distance = in.readString();
        image_url = in.readString();
        description = in.readString();
        contact = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(duration);
        dest.writeString(distance);
        dest.writeString(image_url);
        dest.writeString(description);
        dest.writeString(contact);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}