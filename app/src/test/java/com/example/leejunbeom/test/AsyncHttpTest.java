package com.example.leejunbeom.test;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.example.leejunbeom.bookMarker.network.BMHttpClient;
import com.example.leejunbeom.bookMarker.ui.MainActivity;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder_impl;
import com.example.leejunbeom.bookMarker.util.log.BMLogger;
import com.example.leejunbeom.test.testObjectSets.threadPool.ThreadPool;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
import java.util.concurrent.CountDownLatch;
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
    AsyncHttpClient httpClient;


    @Before
    public void setUp() throws JSONException {
        bmHttpClient = BMHttpClient.getInstance();
        runnerHelper = new InstrumentationTestCase();
        responseJson = new JSONObject();
    }

    @Test
    public void postHttpTest() throws Throwable {

        final String requset = "test";
        JSONObject storeData = new JSONObject();
        storeData.put("store_number", 10011);
        JsonBuilder jsonBuilder = new JsonBuilder_impl();
        JSONObject finalReqData = jsonBuilder.buildRequestData(storeData, "ST00101");
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        entity = new StringEntity(finalReqData.toString());


        bmHttpClient.post(activity.getApplicationContext(), "/Store/GetList", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
                Log.d(BMLogger.LOG_TAG, response.toString());
                responseJson = response;
                assertEquals("false", responseJson.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d(BMLogger.LOG_TAG, res.toString());
                try {
                    responseJson.put("as", res.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread.sleep(2000);


        assertEquals("false", responseJson.toString());

    }

    @Test
    public void httpTest() {
        final CountDownLatch signal = new CountDownLatch(1);
        httpClient = new AsyncHttpClient();
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        try {
            runnerHelper.runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    httpClient
                            .get(
                                    "https://api.twitter.com/1/users/show.json?screen_name=TwitterAPI&include_entities=true",
                                    new AsyncHttpResponseHandler() {
                                        private Boolean _resultFlg;

                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                            System.out.print("success" + responseBody.toString());
                                        }

                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                            System.out.print("faile" + responseBody.toString());
                                        }
                                    });
                }

            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();


            try {
                Log.d("testAsyncHttpClient", "signal.await");
                signal.await(20, TimeUnit.SECONDS); // wait for callback
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            assertEquals("asd", 2, 1);
        }
    }
}
