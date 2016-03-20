package com.example.leejunbeom.bookMarker.network;

import android.content.Context;
import android.content.Entity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;

/**
 * Created by Jun on 16. 3. 16..
 */
public class BMHttpClient {

    private static final String BASE_URL = "http://54.238.172.227:8080/yeshow";
    private static BMHttpClient bmHttpClient = new BMHttpClient();
    private static AsyncHttpClient client = new AsyncHttpClient();

    private BMHttpClient(){

    }

    public static BMHttpClient getInstance(){
        return bmHttpClient;
    }
    public void post(Context context,String url, HttpEntity entity, String contentType, JsonHttpResponseHandler reponseHandler){
        client.post(context,getAbsoluteUrl(url), entity,contentType,reponseHandler);
    }

    public void get(Context context,String url, HttpEntity entity, String contentType, JsonHttpResponseHandler reponseHandler){
        client.get(context,getAbsoluteUrl(url), entity,contentType,reponseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
