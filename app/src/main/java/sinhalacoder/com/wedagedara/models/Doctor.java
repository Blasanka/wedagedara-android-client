package sinhalacoder.com.wedagedara.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Doctor extends WedaGedaraModel implements Parcelable {
    // naming conventions are specified to match with firebase realtime database data format to avoid abnormal behaviours
    private String doctor_id;
    private String name;
    private String location;
    private String image_url;
    private String phone_number;
    private String type; // paramparika, rajaye anumatha
    private String description;
    private double latitude;
    private double longitude;

    // for search
    private String search_name;
    private String search_location;

    public Doctor() {
        super();
    }

    public Doctor(String doctor_id, String name, String location, String image_url, String phone_number, String type, String description, double latitude, double longitude, String search_name, String search_location) {
        super(latitude, longitude);
        this.doctor_id = doctor_id;
        this.name = name;
        this.location = location;
        this.image_url = image_url;
        this.phone_number = phone_number;
        this.type = type;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.search_name = search_name;
        this.search_location = search_location;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }

    public String getSearch_location() {
        return search_location;
    }

    public void setSearch_location(String search_location) {
        this.search_location = search_location;
    }

    protected Doctor(Parcel in) {
        doctor_id = in.readString();
        name = in.readString();
        location = in.readString();
        image_url = in.readString();
        phone_number = in.readString();
        type = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        search_name = in.readString();
        search_location = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doctor_id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(image_url);
        dest.writeString(phone_number);
        dest.writeString(type);
        dest.writeString(description);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(search_name);
        dest.writeString(search_location);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Doctor> CREATOR = new Parcelable.Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };
}