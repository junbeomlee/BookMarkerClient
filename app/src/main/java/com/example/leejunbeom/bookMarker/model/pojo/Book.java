package com.example.leejunbeom.bookMarker.model.pojo;

import android.media.Image;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jun on 16. 3. 21..
 */

public class Book {

    private String featureUrl;
    private String title;
    private String author;
    private String mark;
    private String mapUrl;

    public Book(){

    }

    public Book(JSONObject jsonBookObject) throws JSONException {
        this.featureUrl = jsonBookObject.getString("feature");
        this.title = jsonBookObject.getString("title");
        this.author = jsonBookObject.getString("author");
        this.mark = jsonBookObject.getString("mark");
        this.mapUrl = jsonBookObject.getString("map");
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeatureUrl() {
        return featureUrl;
    }

    public void setFeatureUrl(String featureUrl) {
        this.featureUrl = featureUrl;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", featureUrl='" + featureUrl + '\'' +
                ", title='" + title + '\'' +
                ", mark='" + mark + '\'' +
                ", mapUrl='" + mapUrl + '\'' +
                '}';
    }
}
