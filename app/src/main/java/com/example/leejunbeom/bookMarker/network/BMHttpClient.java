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

    private static final String BASE_URL = "https://api.twitter.com/1/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(Context context,String url, HttpEntity entity, String contentType, JsonHttpResponseHandler reponseHandler){
        client.post(context,getAbsoluteUrl(url), entity,contentType,reponseHandler);
    }

    public static void get(Context context,String url, HttpEntity entity, String contentType, JsonHttpResponseHandler reponseHandler){
        client.get(context,getAbsoluteUrl(url), entity,contentType,reponseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
