package com.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Prithwi on 03/04/18.
 */

public class News implements Parcelable {

    protected String newsId;
    protected String newsHeadLine;
    protected String newsContent;
    protected String newsDate;
    protected String newsImageUrl;
    protected String videoUrl;

    public News(JSONObject jsonObject){
        this.newsId=jsonObject.optString("newsId");
        this.newsHeadLine=jsonObject.optString("headline");
        this.newsContent=jsonObject.optString("content");
        this.newsDate=jsonObject.optString("date");
        this.newsImageUrl=jsonObject.optString("image_url");
        this.videoUrl=jsonObject.optString("video_url");
    }

    protected News(Parcel in) {
        newsId = in.readString();
        newsHeadLine = in.readString();
        newsContent = in.readString();
        newsDate = in.readString();
        newsImageUrl = in.readString();
        videoUrl=in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getNewsId() {
        return newsId;
    }

    public String getNewsHeadLine() {
        return newsHeadLine;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public String getNewsImageUrl() {
        return newsImageUrl;
    }
    public String getVideoUrl() {
        return videoUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsId);
        dest.writeString(newsHeadLine);
        dest.writeString(newsContent);
        dest.writeString(newsDate);
        dest.writeString(newsImageUrl);
        dest.writeString(videoUrl);
    }
}
