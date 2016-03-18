package com.example.leejunbeom.bookMarker.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.leejunbeom.test.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Jun on 16. 3. 17..
 */
public class QRCodeActivity extends Activity{

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        // 결과값 출력
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(result.getContents() + " [" + result.getFormatName() + "]")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}