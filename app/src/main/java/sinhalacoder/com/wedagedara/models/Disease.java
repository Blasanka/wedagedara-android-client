package sinhalacoder.com.wedagedara.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Disease implements Parcelable {
    private String name;
    private String description;
    private String cause;
    private String solution;
    private String medication_goods;
    private String prepare_method;

    public Disease() {
    }

    public Disease(String name, String description, String cause, String solution, String medication_goods, String prepare_method) {
        this.name = name;
        this.description = description;
        this.cause = cause;
        this.solution = solution;
        this.medication_goods = medication_goods;
        this.prepare_method = prepare_method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getMedication_goods() {
        return medication_goods;
    }

    public void setMedication_goods(String medication_goods) {
        this.medication_goods = medication_goods;
    }

    public String getPrepare_method() {
        return prepare_method;
    }

    public void setPrepare_method(String prepare_method) {
        this.prepare_method = prepare_method;
    }

    protected Disease(Parcel in) {
        name = in.readString();
        description = in.readString();
        cause = in.readString();
        solution = in.readString();
        medication_goods = in.readString();
        prepare_method = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(cause);
        dest.writeString(solution);
        dest.writeString(medication_goods);
        dest.writeString(prepare_method);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Disease> CREATOR = new Parcelable.Creator<Disease>() {
        @Override
        public Disease createFromParcel(Parcel in) {
            return new Disease(in);
        }

        @Override
        public Disease[] newArray(int size) {
            return new Disease[size];
        }
    };
}