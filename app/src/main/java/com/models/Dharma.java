package com.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Prithwi on 11/04/18.
 */

public class Dharma implements Parcelable {

    protected Dharma(Parcel in) {
        dharma_id = in.readLong();
        dharma_date = in.readString();
        dharma_headline = in.readString();
        dharma_content = in.readString();
        dharma_image_url = in.readString();
    }

    public Dharma (JSONObject jObj){
        this.dharma_id=jObj.optInt("gyan_id");
        this.dharma_date=jObj.optString("date");
        this.dharma_headline=jObj.optString("headline");
        this.dharma_content=jObj.optString("content");
        this.dharma_image_url=jObj.optString("image_url");
    }

    public Dharma (){}

    public static final Creator<Dharma> CREATOR = new Creator<Dharma>() {
        @Override
        public Dharma createFromParcel(Parcel in) {
            return new Dharma(in);
        }

        @Override
        public Dharma[] newArray(int size) {
            return new Dharma[size];
        }
    };


    public long getDharma_id() {
        return dharma_id;
    }

    public void setDharma_id(long dharma_id) {
        this.dharma_id = dharma_id;
    }

    public String getDharma_date() {
        return dharma_date;
    }

    public void setDharma_date(String dharma_date) {
        this.dharma_date = dharma_date;
    }

    public String getDharma_headline() {
        return dharma_headline;
    }

    public void setDharma_headline(String dharma_headline) {
        this.dharma_headline = dharma_headline;
    }

    public String getDharma_content() {
        return dharma_content;
    }

    public void setDharma_content(String dharma_content) {
        this.dharma_content = dharma_content;
    }

    public String getDharma_image_url() {
        return dharma_image_url;
    }

    public void setDharma_image_url(String dharma_image_url) {
        this.dharma_image_url = dharma_image_url;
    }

    protected  long dharma_id;
    protected String dharma_date;
    protected String dharma_headline;
    protected String dharma_content;
    protected String dharma_image_url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dharma_id);
        dest.writeString(dharma_date);
        dest.writeString(dharma_headline);
        dest.writeString(dharma_content);
        dest.writeString(dharma_image_url);
    }
}
