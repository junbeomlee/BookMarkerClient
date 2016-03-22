package com.example.leejunbeom.test;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.example.leejunbeom.bookMarker.network.BMHttpClient;
import com.example.leejunbeom.bookMarker.ui.MainActivity;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder_impl;
import com.example.leejunbeom.bookMarker.util.log.BMLogger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import static org.junit.Assert.*;
/**
 * Created by Jun on 16. 3. 22..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class JsonParserTest {

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void test(){

    }
}
