package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Statistique implements Parcelable {
    private String id;
    private Ville ville;
    private Arrondissement arrond;
    private int confimer;
    private int deces;
    private int gueris;
    private String date;

    public Statistique() {
    }

    protected Statistique(Parcel in) {
        id = in.readString();
        ville = in.readParcelable(Ville.class.getClassLoader());
        arrond = in.readParcelable(Arrondissement.class.getClassLoader());
        confimer = in.readInt();
        deces = in.readInt();
        gueris = in.readInt();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(ville, flags);
        dest.writeParcelable(arrond, flags);
        dest.writeInt(confimer);
        dest.writeInt(deces);
        dest.writeInt(gueris);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Statistique> CREATOR = new Creator<Statistique>() {
        @Override
        public Statistique createFromParcel(Parcel in) {
            return new Statistique(in);
        }

        @Override
        public Statistique[] newArray(int size) {
            return new Statistique[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Arrondissement getArrond() {
        return arrond;
    }

    public void setArrond(Arrondissement arrond) {
        this.arrond = arrond;
    }

    public int getConfimer() {
        return confimer;
    }

    public void setConfimer(int confimer) {
        this.confimer = confimer;
    }

    public int getDeces() {
        return deces;
    }

    public void setDeces(int deces) {
        this.deces = deces;
    }

    public int getGueris() {
        return gueris;
    }

    public void setGueris(int gueris) {
        this.gueris = gueris;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
