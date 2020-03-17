package sinhalacoder.com.wedagedara.models;

public abstract class WedaGedaraModel {
    private double latitude;
    private double longitude;

    WedaGedaraModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    WedaGedaraModel() {
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
}
