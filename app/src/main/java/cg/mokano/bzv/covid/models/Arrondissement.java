package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Arrondissement implements Parcelable {
    private String id;
    private String libelle;
    private Ville ville;

    public Arrondissement() {
    }

    protected Arrondissement(Parcel in) {
        id = in.readString();
        libelle = in.readString();
        ville = in.readParcelable(Ville.class.getClassLoader());
    }

    public static final Creator<Arrondissement> CREATOR = new Creator<Arrondissement>() {
        @Override
        public Arrondissement createFromParcel(Parcel in) {
            return new Arrondissement(in);
        }

        @Override
        public Arrondissement[] newArray(int size) {
            return new Arrondissement[size];
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

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(libelle);
        dest.writeParcelable(ville, flags);
    }

    @Override
    public String toString() {
        return libelle;
    }
}
