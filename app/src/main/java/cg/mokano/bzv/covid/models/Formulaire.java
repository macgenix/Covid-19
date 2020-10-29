package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@Keep
@IgnoreExtraProperties
public class Formulaire implements Parcelable {
    private String id;
    private String ville;
    private String Profession;
    private String Quartier;
    private int age;
    private double latitude;
    private double longitude;
    private ArrayList<QuestionReponse> autodiagnostics;

    public Formulaire() {
    }

    protected Formulaire(Parcel in) {
        id = in.readString();
        ville = in.readString();
        Profession = in.readString();
        Quartier = in.readString();
        age = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        autodiagnostics = in.createTypedArrayList(QuestionReponse.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(ville);
        dest.writeString(Profession);
        dest.writeString(Quartier);
        dest.writeInt(age);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeTypedList(autodiagnostics);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Formulaire> CREATOR = new Creator<Formulaire>() {
        @Override
        public Formulaire createFromParcel(Parcel in) {
            return new Formulaire(in);
        }

        @Override
        public Formulaire[] newArray(int size) {
            return new Formulaire[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getQuartier() {
        return Quartier;
    }

    public void setQuartier(String quartier) {
        Quartier = quartier;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public ArrayList<QuestionReponse> getAutodiagnostics() {
        return autodiagnostics;
    }

    public void setAutodiagnostics(ArrayList<QuestionReponse> autodiagnostics) {
        this.autodiagnostics = autodiagnostics;
    }
}
