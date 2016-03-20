package com.example.leejunbeom.test;

import android.app.Activity;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.example.leejunbeom.bookMarker.network.Network_impl;
import com.example.leejunbeom.bookMarker.ui.MainActivity;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder_impl;
import com.example.leejunbeom.bookMarker.util.log.BMLogger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Jun on 16. 3. 19..
 */

public class AsyncHttpTest extends ActivityInstrumentationTestCase2<MainActivity> {

    AsyncHttpClient httpClient;
    private Activity myActivity;
    public String aasd;

    public AsyncHttpTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myActivity = this.getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void test_shoudld_asynchttpjsonpost_work() throws Throwable {

        //given
        final CountDownLatch signal = new CountDownLatch(1);

        JSONObject storeData = new JSONObject();
        storeData.put("store_number", 10011);
        JsonBuilder jsonBuilder = new JsonBuilder_impl();
        JSONObject finalReqData = jsonBuilder.buildRequestData(storeData, "ST00101");
        final StringEntity entity = new StringEntity(finalReqData.toString());


        //when
        runTestOnUiThread(new Runnable() { // THIS IS THE KEY TO SUCCESS
            @Override
            public void run() {
                if (Network_impl.getInstance().isOnline(myActivity))
                    Network_impl.getInstance().post(myActivity, "/Store/GetList", entity, "application/json", new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                            // Handle resulting parsed JSON response here
                            signal.countDown();

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                            signal.countDown();

                        }
                    });
            }
        });


        try {
            signal.await(30, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //then
        assertEquals(0, signal.getCount());

    }
}
