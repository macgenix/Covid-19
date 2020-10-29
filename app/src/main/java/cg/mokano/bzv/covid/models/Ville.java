package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Ville implements Parcelable {
    private String id;
    private String libelle;

    public Ville() {
    }

    public Ville(String libelle) {
        this.libelle = libelle;
    }

    protected Ville(Parcel in) {
        id = in.readString();
        libelle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(libelle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ville> CREATOR = new Creator<Ville>() {
        @Override
        public Ville createFromParcel(Parcel in) {
            return new Ville(in);
        }

        @Override
        public Ville[] newArray(int size) {
            return new Ville[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
