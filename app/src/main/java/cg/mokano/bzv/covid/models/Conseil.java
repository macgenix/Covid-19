package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Conseil implements Parcelable {
    private int id;
    private String titre;
    private String description;

    public Conseil() {
    }

    protected Conseil(Parcel in) {
        id = in.readInt();
        titre = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titre);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Conseil> CREATOR = new Creator<Conseil>() {
        @Override
        public Conseil createFromParcel(Parcel in) {
            return new Conseil(in);
        }

        @Override
        public Conseil[] newArray(int size) {
            return new Conseil[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
