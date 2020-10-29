package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Thematique implements Parcelable {
    private int id;
    private int embleme;
    private String titre;

    public Thematique() {
    }

    public Thematique(int id, int embleme, String titre) {
        this.id = id;
        this.embleme = embleme;
        this.titre = titre;
    }

    protected Thematique(Parcel in) {
        id = in.readInt();
        embleme = in.readInt();
        titre = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(embleme);
        dest.writeString(titre);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Thematique> CREATOR = new Creator<Thematique>() {
        @Override
        public Thematique createFromParcel(Parcel in) {
            return new Thematique(in);
        }

        @Override
        public Thematique[] newArray(int size) {
            return new Thematique[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmbleme() {
        return embleme;
    }

    public void setEmbleme(int embleme) {
        this.embleme = embleme;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
