package com.example.leejunbeom.bookMarker.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.util.log.BMLogger;
import com.example.leejunbeom.test.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 16. 3. 17..
 */
public class BookInfoActivity extends AppCompatActivity {


    public static final String KEY_SCAN_RESULT = "SCAN_RESULT";
    public static final String KEY_SCAN_RESULT_FORMAT = "SCAN_RESULT_FORMAT";


    @Bind(R.id.qrcodeInfo)
    TextView qrcodeInfo;

    @Bind(R.id.qrcode_scan_button)
    Button qrcode_scan_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        //injector.get().inject(this);
        ButterKnife.bind(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        Log.d(BMLogger.LOG_TAG, "started");
        Log.d(BMLogger.LOG_TAG,scanResult.toString());
        Log.d(BMLogger.LOG_TAG,scanResult.getContents().toString());
        if ((scanResult != null) && (scanResult.getContents() != null)) {
            String data = scanResult.getContents();
            Toast.makeText(this, data,
                    Toast.LENGTH_LONG).show();
            Log.d(BMLogger.LOG_TAG, data);
            //qrcodeInfo.append(intent.getStringExtra("Value"));
            qrcodeInfo.setText(data);
        }
    }

    @OnClick(R.id.qrcode_scan_button)
    public void onCallBack(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }
}