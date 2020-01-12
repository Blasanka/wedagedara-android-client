package sinhalacoder.com.wedagedara.models;
/*---------------------o----------o----------------------
 * Created by Blasanka on 27,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.os.Parcel;
import android.os.Parcelable;

public class Medication implements Parcelable {
    private String name;
    private String image_url;
    private String description;
    private String locations;

    public Medication() {
    }

    public Medication(String name, String image_url, String description, String locations) {
        this.name = name;
        this.image_url = image_url;
        this.description = description;
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", description='" + description + '\'' +
                ", locations='" + locations + '\'' +
                '}';
    }

    protected Medication(Parcel in) {
        name = in.readString();
        image_url = in.readString();
        description = in.readString();
        locations = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image_url);
        dest.writeString(description);
        dest.writeString(locations);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Medication> CREATOR = new Parcelable.Creator<Medication>() {
        @Override
        public Medication createFromParcel(Parcel in) {
            return new Medication(in);
        }

        @Override
        public Medication[] newArray(int size) {
            return new Medication[size];
        }
    };
}