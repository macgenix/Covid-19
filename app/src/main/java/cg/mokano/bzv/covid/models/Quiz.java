package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class Quiz implements Parcelable {
    private int id;
    private String question;
    private String rep1;
    private String rep2;
    private String rep3;
    private String boeRep;

    public Quiz() {
    }

    protected Quiz(Parcel in) {
        id = in.readInt();
        question = in.readString();
        rep1 = in.readString();
        rep2 = in.readString();
        rep3 = in.readString();
        boeRep = in.readString();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRep1() {
        return rep1;
    }

    public void setRep1(String rep1) {
        this.rep1 = rep1;
    }

    public String getRep2() {
        return rep2;
    }

    public void setRep2(String rep2) {
        this.rep2 = rep2;
    }

    public String getRep3() {
        return rep3;
    }

    public void setRep3(String rep3) {
        this.rep3 = rep3;
    }

    public String getBoeRep() {
        return boeRep;
    }

    public void setBoeRep(String boeRep) {
        this.boeRep = boeRep;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(rep1);
        dest.writeString(rep2);
        dest.writeString(rep3);
        dest.writeString(boeRep);
    }
}
