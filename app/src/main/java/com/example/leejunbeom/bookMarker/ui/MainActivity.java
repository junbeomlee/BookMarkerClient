package com.example.leejunbeom.bookMarker.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.test.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
        IntentIntegrator integrator = new IntentIntegrator(this);
        //integrator.setCaptureActivity(QRCodeActivity.class);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();

        /*Intent intent = new Intent(
                "com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);*/

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if ((scanResult != null) && (scanResult.getContents() != null)) {
            String data = scanResult.getContents();

            Toast.makeText(this, data,
                        Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }





}
