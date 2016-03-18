package com.example.leejunbeom.bookMarker.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.model.SIFT;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    //@Inject
    //SIFT SIFT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //injector.get().inject(this);
        /*IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(QRCodeActivity.class);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();*/

        Intent intent = new Intent(
                "com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {


                String contents = intent.getStringExtra("SCAN_RESULT"); // This will contain your scan result
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast.makeText(MainActivity.this, contents, Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }





}
