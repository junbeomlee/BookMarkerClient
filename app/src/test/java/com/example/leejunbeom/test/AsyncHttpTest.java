package com.example.leejunbeom.test;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.example.leejunbeom.bookMarker.network.BMHttpClient;
import com.example.leejunbeom.bookMarker.ui.MainActivity;
import com.example.leejunbeom.test.testObjectSets.threadPool.ThreadPool;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import static org.junit.Assert.*;

/**
 * Created by Jun on 16. 3. 16..
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AsyncHttpTest {

    private BMHttpClient bmHttpClient;
    private InstrumentationTestCase runnerHelper;
    public JSONObject responseJson;
    public MainActivity activity;
    public StringEntity entity;


    @Before
    public void setUp() throws JSONException {
        bmHttpClient=new BMHttpClient();
        runnerHelper = new InstrumentationTestCase();
        responseJson = new JSONObject();
    }

    @Test
    public void postHttpTest() throws Throwable {

        final String requset="test";
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        entity = new StringEntity(requset.toString());

        bmHttpClient.post(activity.getApplicationContext(), "https://edu.chat/api/login/", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                        // Handle resulting parsed JSON response here
                System.out.print(response.toString());
                responseJson = response;
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.out.print(res.toString());
                try {
                    responseJson.put("success", "fail");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //Thread.sleep(2000);


        //assertEquals("false", responseJson.toString());

    }
}
