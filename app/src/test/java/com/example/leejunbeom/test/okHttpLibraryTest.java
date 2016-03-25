package com.example.leejunbeom.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jun on 16. 3. 25..
 */
public class okHttpLibraryTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void should_http_get_request_work() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response response=null;

        Request request = new Request.Builder()
                .url("http://library.cau.ac.kr/search/DetailView.ax?sid=1&cid=5241729")
                .build();

        try {
             response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(response.body().string());
       // assertEquals("get http data failed","asd",response.body().toString());
    }

}
