package cg.mokano.bzv.covid.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class QuestionReponse implements Parcelable {
    private int id;
    private String question;
    private String reponse;

    public QuestionReponse() {
    }

    protected QuestionReponse(Parcel in) {
        id = in.readInt();
        question = in.readString();
        reponse = in.readString();
    }

    public static final Creator<QuestionReponse> CREATOR = new Creator<QuestionReponse>() {
        @Override
        public QuestionReponse createFromParcel(Parcel in) {
            return new QuestionReponse(in);
        }

        @Override
        public QuestionReponse[] newArray(int size) {
            return new QuestionReponse[size];
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

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(reponse);
    }
}
