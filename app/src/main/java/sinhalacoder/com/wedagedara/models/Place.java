package sinhalacoder.com.wedagedara.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Place extends WedaGedaraModel implements Parcelable {
    // naming conventions are specified to match with firebase realtime database data format to avoid abnormal behaviours
    private String name;
    private String duration;
    private String distance;
    private String image_url;
    private String description;
    private String phone_number;
    private double latitude;
    private double longitude;

    public Place() {
        super();
    }

    public Place(String name, String duration, String distance, String image_url, String description, String phone_number, double latitude, double longitude) {
        super(latitude, longitude);
        this.name = name;
        this.duration = duration;
        this.distance = distance;
        this.image_url = image_url;
        this.description = description;
        this.phone_number = phone_number;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    protected Place(Parcel in) {
        name = in.readString();
        duration = in.readString();
        distance = in.readString();
        image_url = in.readString();
        description = in.readString();
        phone_number = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
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
        dest.writeString(phone_number);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
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