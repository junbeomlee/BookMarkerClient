package com.example.leejunbeom.test;

import com.example.leejunbeom.bookMarker.util.json.JsonParser;

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
    public void testJsonParserWhenCorrectData(){
        JSONObject jsonObject= new JSONObject();
        String testString = "{\"map\":\"\",\"success\":true,\"author\":\"문근찬 저.\",\"title\":\"경영학 : 피터 드러커 관점\",\"feature\":\"\",\"mark\":\"658문근찬경2\"}";
        try {
            jsonObject = JsonParser.ParseStringToJson(testString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals("fail parser correct jsonObject", "{\"map\":\"\",\"success\":true,\"author\":\"문근찬 저.\",\"title\":\"경영학 : 피터 드러커 관점\",\"feature\":\"\",\"mark\":\"658문근찬경2\"}",jsonObject.toString());
    }

    @Test(expected=JSONException.class)
    public void testJsonParserWhenFailData() throws JSONException {
        JSONObject jsonObject= new JSONObject();
        String testString = "{\"map\":\"\",\"success\":false,\"author\":\"문근찬 저.\",\"title\":\"경영학 : 피터 드러커 관점\",\"feature\":\"\",\"mark\":\"658문근찬경2\"}";
        JsonParser.ParseStringToJson(testString);

    }
}
