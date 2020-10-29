package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Gouv implements Parcelable {
    private String id;
    private String titre;
    private long date;
    private String lien;

    public Gouv() {
    }

    protected Gouv(Parcel in) {
        id = in.readString();
        titre = in.readString();
        date = in.readLong();
        lien = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titre);
        dest.writeLong(date);
        dest.writeString(lien);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Gouv> CREATOR = new Creator<Gouv>() {
        @Override
        public Gouv createFromParcel(Parcel in) {
            return new Gouv(in);
        }

        @Override
        public Gouv[] newArray(int size) {
            return new Gouv[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
}
