package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.ui.preview.CameraPreview;
import com.example.leejunbeom.test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;

/**
 * Created by Jun on 16. 3. 28..
 */
public class SearchActivity extends AppCompatActivity {

    private static String TAG = "CAMERA";

    private Context mContext = this;
    private Camera mCamera;
    private CameraPreview camPreview;
    private ImageView MyCameraPreview = null;
    private ImageView imageView;
    private FrameLayout mainLayout;
    private int PreviewSizeWidth = 640;
    private int PreviewSizeHeight= 480;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_search);
        mContext = this;

        MyCameraPreview = new ImageView(this);

        SurfaceView camView = new SurfaceView(this);
        SurfaceHolder camHolder = camView.getHolder();
        //imageView = (ImageView) findViewById(R.id.imageView2);

        Bitmap bookBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.book2);
        camPreview = new CameraPreview(PreviewSizeWidth, PreviewSizeHeight, MyCameraPreview,bookBitMap,getApplicationContext());

        camHolder.addCallback(camPreview);
        camHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        mainLayout = (FrameLayout) findViewById(R.id.camera_preview);
        mainLayout.addView(camView);
        mainLayout.addView(MyCameraPreview);
    }


    protected void onPause()
    {
        if ( camPreview != null)
            camPreview.onPause();
        super.onPause();
    }
}
