package sinhalacoder.com.wedagedara.models;
/*---------------------o----------o----------------------
 * Created by Blasanka on 27,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

public class Doctor {
    // naming conventions are specified to match with firebase realtime database data format to avoid abnormal behaviours
    private String doctor_id;
    private String full_name;
    private String location;
    private String image_url;
    private String phone_number;

    public Doctor() {
    }

    public Doctor(String doctor_id, String full_name, String location, String image_url, String phone_number) {
        this.doctor_id = doctor_id;
        this.full_name = full_name;
        this.location = location;
        this.image_url = image_url;
        this.phone_number = phone_number;
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
}
