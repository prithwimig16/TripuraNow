package com.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.json.JSONObject;

/**
 * Created by Prithwi on 03/04/18.
 */
@Entity
public class News implements Parcelable {

    protected String newsId;
    protected String newsHeadLine;
    protected String newsContent;
    protected String newsDate;
    protected String newsImageUrl;
    protected String videoUrl;



    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setNewsHeadLine(String newsHeadLine) {
        this.newsHeadLine = newsHeadLine;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public void setNewsImageUrl(String newsImageUrl) {
        this.newsImageUrl = newsImageUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    public News(JSONObject jsonObject){
        this.newsId=jsonObject.optString("news_id");
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

    public News(){}

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
