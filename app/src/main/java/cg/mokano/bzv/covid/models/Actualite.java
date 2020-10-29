package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Actualite implements Parcelable {
    private String id;
    private String source;
    private String titre;
    private String lien;
    private String image;
    private long date;

    public Actualite() {
    }

    protected Actualite(Parcel in) {
        id = in.readString();
        source = in.readString();
        titre = in.readString();
        lien = in.readString();
        image = in.readString();
        date = in.readLong();
    }

    public static final Creator<Actualite> CREATOR = new Creator<Actualite>() {
        @Override
        public Actualite createFromParcel(Parcel in) {
            return new Actualite(in);
        }

        @Override
        public Actualite[] newArray(int size) {
            return new Actualite[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(source);
        dest.writeString(titre);
        dest.writeString(lien);
        dest.writeString(image);
        dest.writeLong(date);
    }
}
