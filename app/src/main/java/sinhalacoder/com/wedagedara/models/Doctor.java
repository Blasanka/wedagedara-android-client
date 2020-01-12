package sinhalacoder.com.wedagedara.models;
/*---------------------o----------o----------------------
 * Created by Blasanka on 27,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.os.Parcel;
import android.os.Parcelable;

public class Doctor implements Parcelable {
    // naming conventions are specified to match with firebase realtime database data format to avoid abnormal behaviours
    private String doctor_id;
    private String full_name;
    private String location;
    private String image_url;
    private String phone_number;
    private String type; // paramparika, rajaye anumatha
    private String description;

    // for search
    private String search_full_name;
    private String search_location;

    public Doctor() {
    }

    public Doctor(String doctor_id, String full_name, String location, String image_url, String phone_number, String type, String description, String search_full_name, String search_location) {
        this.doctor_id = doctor_id;
        this.full_name = full_name;
        this.location = location;
        this.image_url = image_url;
        this.phone_number = phone_number;
        this.type = type;
        this.description = description;
        this.search_full_name = search_full_name;
        this.search_location = search_location;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public String getSearch_full_name() {
        return search_full_name;
    }

    public void setSearch_full_name(String search_full_name) {
        this.search_full_name = search_full_name;
    }

    public String getSearch_location() {
        return search_location;
    }

    public void setSearch_location(String search_location) {
        this.search_location = search_location;
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

    protected Doctor(Parcel in) {
        doctor_id = in.readString();
        full_name = in.readString();
        location = in.readString();
        image_url = in.readString();
        phone_number = in.readString();
        type = in.readString();
        description = in.readString();
        search_full_name = in.readString();
        search_location = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doctor_id);
        dest.writeString(full_name);
        dest.writeString(location);
        dest.writeString(image_url);
        dest.writeString(phone_number);
        dest.writeString(type);
        dest.writeString(description);
        dest.writeString(search_full_name);
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