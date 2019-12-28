package sinhalacoder.com.wedagedara.models;
/*---------------------o----------o----------------------
 * Created by Blasanka on 27,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

public class Medication {
    private String name;
    private String image_url;
    private String description;

    public Medication(String name, String image_url, String description) {
        this.name = name;
        this.image_url = image_url;
        this.description = description;
    }

    public Medication() {
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
}
