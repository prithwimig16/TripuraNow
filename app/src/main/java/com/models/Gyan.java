package com.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Prithwi on 11/04/18.
 */

public class Gyan implements Parcelable  {

    protected Gyan(Parcel in) {
        gyan_id = in.readLong();
        gyan_date = in.readString();
        gyan_headline = in.readString();
        gyan_content = in.readString();
        gyan_image_url = in.readString();
    }

    public Gyan (JSONObject jObj){
        this.gyan_id=jObj.optInt("gyan_id");
        this.gyan_date=jObj.optString("date");
        this.gyan_content=jObj.optString("content");
        this.gyan_headline=jObj.optString("headline");
        this.gyan_image_url=jObj.optString("image_url");
    }

    public Gyan (){}

    public static final Creator<Gyan> CREATOR = new Creator<Gyan>() {
        @Override
        public Gyan createFromParcel(Parcel in) {
            return new Gyan(in);
        }

        @Override
        public Gyan[] newArray(int size) {
            return new Gyan[size];
        }
    };

    public long getGyan_id() {
        return gyan_id;
    }

    public void setGyan_id(long gyan_id) {
        this.gyan_id = gyan_id;
    }

    public String getGyan_date() {
        return gyan_date;
    }

    public void setGyan_date(String gyan_date) {
        this.gyan_date = gyan_date;
    }

    public String getGyan_headline() {
        return gyan_headline;
    }

    public void setGyan_headline(String gyan_headline) {
        this.gyan_headline = gyan_headline;
    }

    public String getGyan_content() {
        return gyan_content;
    }

    public void setGyan_content(String gyan_content) {
        this.gyan_content = gyan_content;
    }

    public String getGyan_image_url() {
        return gyan_image_url;
    }

    public void setGyan_image_url(String gyan_image_url) {
        this.gyan_image_url = gyan_image_url;
    }

    protected  long gyan_id;
    protected String gyan_date;
    protected String gyan_headline;
    protected String gyan_content;
    protected String gyan_image_url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(gyan_id);
        dest.writeString(gyan_date);
        dest.writeString(gyan_headline);
        dest.writeString(gyan_content);
        dest.writeString(gyan_image_url);
    }
}
