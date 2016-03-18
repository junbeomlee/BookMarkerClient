package com.example.leejunbeom.bookMarker.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.bookMarker.network.BMHttpClient;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder;
import com.example.leejunbeom.bookMarker.util.json.JsonBuilder_impl;
import com.example.leejunbeom.bookMarker.util.log.BLogger;
import com.example.leejunbeom.test.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class MainActivity extends AppCompatActivity {

    //@Inject
    //SIFT SIFT;

    @Bind(R.id.button)
    Button httpTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //injector.get().inject(this);
        //IntentIntegrator integrator = new IntentIntegrator(this);
        //integrator.setOrientationLocked(true);
        //integrator.initiateScan();
        ButterKnife.bind(this);
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if ((scanResult != null) && (scanResult.getContents() != null)) {
            String data = scanResult.getContents();

            Toast.makeText(this, data,
                        Toast.LENGTH_LONG).show();

        }
    }*/
    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.button)
    public void onCallClick() {
        Log.d("MainActivity","onCall 54");
        JsonBuilder jsonBuilder = new JsonBuilder_impl();
        StringEntity entity = null;
        try {
            JSONObject storeData=new JSONObject();
            storeData.put("store_number", 10011);

            JSONObject finalReqData=jsonBuilder.buildRequestData(storeData, "ST00101");
            entity = new StringEntity(finalReqData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BLogger.log(this, entity.toString());

        BMHttpClient.post(this.getApplicationContext(),"/Store/GetList", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
                Log.d("MainActivity=====",response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("MainActivity====",res.toString());

            }

        });
    }





}
