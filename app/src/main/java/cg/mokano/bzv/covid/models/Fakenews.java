package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Fakenews implements Parcelable {
    private String id;
    private String description;
    private String urlRS;
    private String urlFb;
    private long timestamp;
    private int validation;

    public Fakenews() {
    }

    protected Fakenews(Parcel in) {
        id = in.readString();
        description = in.readString();
        urlRS = in.readString();
        urlFb = in.readString();
        timestamp = in.readLong();
        validation = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(description);
        dest.writeString(urlRS);
        dest.writeString(urlFb);
        dest.writeLong(timestamp);
        dest.writeInt(validation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Fakenews> CREATOR = new Creator<Fakenews>() {
        @Override
        public Fakenews createFromParcel(Parcel in) {
            return new Fakenews(in);
        }

        @Override
        public Fakenews[] newArray(int size) {
            return new Fakenews[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlRS() {
        return urlRS;
    }

    public void setUrlRS(String urlRS) {
        this.urlRS = urlRS;
    }

    public String getUrlFb() {
        return urlFb;
    }

    public void setUrlFb(String urlFb) {
        this.urlFb = urlFb;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getValidation() {
        return validation;
    }

    public void setIsValidation(int validation) {
        this.validation = validation;
    }
}
