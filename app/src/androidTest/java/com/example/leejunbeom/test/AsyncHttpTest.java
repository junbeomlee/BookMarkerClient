package com.example.leejunbeom.test;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.example.leejunbeom.bookMarker.ui.MainActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jun on 16. 3. 19..
 */

@RunWith(AndroidJUnit4.class)
public class AsyncHttpTest extends ActivityInstrumentationTestCase2<MainActivity>{

    AsyncHttpClient httpClient;

    public AsyncHttpTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Before
    public void setUp(){
        httpClient = new AsyncHttpClient();
    }
    @Test
    public void asd() throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);

        this.runTestOnUiThread(new Runnable() {
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
