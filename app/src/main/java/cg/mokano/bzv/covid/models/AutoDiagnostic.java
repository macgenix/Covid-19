package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class AutoDiagnostic implements Parcelable {
    private int id;
    private String qst;
    private String proposition1;
    private String proposition2;
    private int image;
    private String explication1;
    private String explication2;

    public AutoDiagnostic() {
    }

    protected AutoDiagnostic(Parcel in) {
        id = in.readInt();
        qst = in.readString();
        proposition1 = in.readString();
        proposition2 = in.readString();
        image = in.readInt();
        explication1 = in.readString();
        explication2 = in.readString();
    }

    public static final Creator<AutoDiagnostic> CREATOR = new Creator<AutoDiagnostic>() {
        @Override
        public AutoDiagnostic createFromParcel(Parcel in) {
            return new AutoDiagnostic(in);
        }

        @Override
        public AutoDiagnostic[] newArray(int size) {
            return new AutoDiagnostic[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQst() {
        return qst;
    }

    public void setQst(String qst) {
        this.qst = qst;
    }

    public String getProposition1() {
        return proposition1;
    }

    public void setProposition1(String proposition1) {
        this.proposition1 = proposition1;
    }

    public String getProposition2() {
        return proposition2;
    }

    public void setProposition2(String proposition2) {
        this.proposition2 = proposition2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getExplication1() {
        return explication1;
    }

    public void setExplication1(String explication1) {
        this.explication1 = explication1;
    }

    public String getExplication2() {
        return explication2;
    }

    public void setExplication2(String explication2) {
        this.explication2 = explication2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(qst);
        dest.writeString(proposition1);
        dest.writeString(proposition2);
        dest.writeInt(image);
        dest.writeString(explication1);
        dest.writeString(explication2);
    }
}
