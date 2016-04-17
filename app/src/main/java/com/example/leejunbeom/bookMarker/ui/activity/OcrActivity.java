package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leejunbeom.test.R;
import com.googlecode.leptonica.android.Pix;
import com.googlecode.leptonica.android.Pixa;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public class OcrActivity extends AppCompatActivity {


    public static final String PACKAGE_NAME = "com.datumdroid.android.ocr.simple";
    public static String DATA_PATH = Environment
            .getExternalStorageDirectory().toString() + "/BookMarkerOCR/";
    //set internal storage
    public String DATA_PATH_INTERNAL;

    // You should have the trained data file in assets folder
    // You can get them at:
    // http://code.google.com/p/tesseract-ocr/downloads/list
    public static final String lang = "eng";

    private static final String TAG = "OcrActivity.java";

    protected Button _button;
    // protected ImageView _image;
    protected EditText _field;
    protected String _path;
    protected boolean _taken;

    protected static final String PHOTO_TAKEN = "photo_taken";

    //클래스 전역변수
    private final String rootFolderName = "/BookMarkerOCR";
    private final String tessdataFolderName = "/tessdata";
    public static String root = null; //메모를 저장하는 폴더의 root dir
    public static String tessDataRoot = null; //메모를 저장하는 폴더 dir

    @Override
    public void onCreate(Bundle savedInstanceState) {

        /*
        DATA_PATH_INTERNAL = getApplicationContext().getFilesDir().toString();
        Log.v("noduritoto", DATA_PATH_INTERNAL);
        File dir2 = new File(DATA_PATH_INTERNAL);
        if(!dir2.exists()){
            Log.v("noduritoto", "Internal storage djqtdj");
            dir2.mkdirs();
            Log.v("noduritoto", "Internal storage created");
        }
        */
        //Log.v(TAG, "DATA_PATH " + DATA_PATH);
        /*

        String[] paths = new String[] { DATA_PATH, DATA_PATH + "tessdata/" };

        for (String path : paths) {
            File dir = new File(path);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.v(TAG, "ERROR: Creation of directory " + path + " on sdcard failed");
                    return;
                } else {
                    Log.v(TAG, "Created directory " + path + " on sdcard");
                }
            }

        }
        */

        /////////gns sujung

        //dir init
        String sdcard= Environment.getExternalStorageState();
        if( ! sdcard.equals(Environment.MEDIA_MOUNTED) ) {
            //SD카드 UNMOUNTED
            Log.d("mstag","sdcard unmounted");
            root = "" + Environment.getRootDirectory().getAbsolutePath() + rootFolderName; //내부저장소의 주소를 얻어옴
        } else {
            //SD카드 MOUNT
            Log.d("mstag","sdcard mounted");
            root = "" + Environment.getExternalStorageDirectory().getAbsolutePath() + rootFolderName; //외부저장소의 주소를 얻어옴
        }
        Log.d("mstag","root dir is => "+root);

        tessDataRoot = root + tessdataFolderName;
        File rootCheck = new File(root);
        if(!rootCheck.isDirectory()){
            Log.d("mstag","root is not Directory");
            return;
        }
        if( ! rootCheck.exists() ) { //최상위 루트폴더 미 존재시
            rootCheck.mkdirs(); Log.d("mstag","root make");
            Log.d("mstag","check making root : " + rootCheck.exists());
        }
        rootCheck = new File(tessDataRoot);


        if( ! rootCheck.exists() ) { //하위 메모저장폴더 미 존재시
            rootCheck.mkdirs(); Log.d("mstag","root-son make");
            Log.d("mstag","check making root-son : " + rootCheck.exists());
            if(!rootCheck.isDirectory()){
                Log.d("mstag","tessDataRoot is not Directory");
                return;
            }
        }

        DATA_PATH = new String(root + "/");
        Log.d("mstag",root);
        Log.d("mstag",DATA_PATH);


        // lang.traineddata file with the app (in assets folder)
        // You can get them at:
        // http://code.google.com/p/tesseract-ocr/downloads/list
        // This area needs work and optimization
        if (!(new File(DATA_PATH + "tessdata/" + lang + ".traineddata")).exists()) {
            try {

                AssetManager assetManager = getAssets();
                InputStream in = assetManager.open("tessdata/" + lang + ".traineddata");
                //GZIPInputStream gin = new GZIPInputStream(in);
                OutputStream out = new FileOutputStream(DATA_PATH
                        + "tessdata/" + lang + ".traineddata");

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                //while ((lenf = gin.read(buff)) > 0) {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                //gin.close();
                out.close();

                Log.v(TAG, "Copied " + lang + " traineddata");
            } catch (IOException e) {
                Log.e(TAG, "Was unable to copy " + lang + " traineddata " + e.toString());
            }
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ocr);

        // _image = (ImageView) findViewById(R.id.image);
        _field = (EditText) findViewById(R.id.field);
        _button = (Button) findViewById(R.id.button);
        _button.setOnClickListener(new ButtonClickHandler());

        _path = DATA_PATH + "/ocr.jpg";
    }

    public class ButtonClickHandler implements View.OnClickListener {
        public void onClick(View view) {
            Log.v(TAG, "Starting Camera app");
            startCameraActivity();
        }
    }

    // Simple android photo capture:
    // http://labs.makemachine.net/2010/03/simple-android-photo-capture/

    protected void startCameraActivity() {
        File file = new File(_path);
        Uri outputFileUri = Uri.fromFile(file);

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG, "resultCode: " + resultCode);

        if (resultCode == -1) {
            onPhotoTaken();
        } else {
            Log.v(TAG, "User cancelled");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(OcrActivity.PHOTO_TAKEN, _taken);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState()");
        if (savedInstanceState.getBoolean(OcrActivity.PHOTO_TAKEN)) {
            onPhotoTaken();
        }
    }

    protected void onPhotoTaken() {
        _taken = true;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        Bitmap bitmap = BitmapFactory.decodeFile(_path, options);

        try {
            ExifInterface exif = new ExifInterface(_path);
            int exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            Log.v(TAG, "Orient: " + exifOrientation);

            int rotate = 0;

            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
            }

            Log.v(TAG, "Rotation: " + rotate);

            if (rotate != 0) {

                // Getting width & height of the given image.
                int w = bitmap.getWidth();
                int h = bitmap.getHeight();

                // Setting pre rotate
                Matrix mtx = new Matrix();
                mtx.preRotate(rotate);

                // Rotating Bitmap
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);
            }

            // Convert to ARGB_8888, required by tess
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        } catch (IOException e) {
            Log.e(TAG, "Couldn't correct orientation: " + e.toString());
        }

        // _image.setImageBitmap( bitmap );

        Log.v(TAG, "Before baseApi");

        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(true);
        baseApi.init(DATA_PATH, lang);
        baseApi.setImage(bitmap);

        String recognizedText = baseApi.getUTF8Text();

        baseApi.end();

        // You now have the text in recognizedText var, you can do anything with it.
        // We will display a stripped out trimmed alpha-numeric version of it (if lang is eng)
        // so that garbage doesn't make it to the display.

        Log.v(TAG, "OCRED TEXT: " + recognizedText);

        if ( lang.equalsIgnoreCase("eng") ) {
            recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9]+", " ");
        }

        recognizedText = recognizedText.trim();

        if ( recognizedText.length() != 0 ) {
            _field.setText(_field.getText().toString().length() == 0 ? recognizedText : _field.getText() + " " + recognizedText);
            _field.setSelection(_field.getText().toString().length());
        }

        // Cycle done.
    }

    // www.Gaut.am was here
    // Thanks for reading
}
