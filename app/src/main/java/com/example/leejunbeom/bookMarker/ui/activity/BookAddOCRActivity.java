package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.ui.preview.CameraPreview;
import com.example.leejunbeom.test.R;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookAddOCRActivity extends AppCompatActivity {

    String TAG = "CAMERA";
    private Context mContext = this;
    private Camera mCamera;
    private CameraPreview mPreview;

    //for OCR
    public static final String DATA_PATH = Environment
            .getExternalStorageDirectory().toString() + "/SimpleAndroidOCR/";

    // You should have the trained data file in assets folder
    // You can get them at:
    // http://code.google.com/p/tesseract-ocr/downloads/list
    public static final String lang = "eng";
    private static final String TAG1 = "SimpleAndroidOCR.java";
    protected String _path;
    private String resultString;

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // JPEG 이미지가 byte[] 형태로 들어옵니다

            File pictureFile = getOutputMediaFile(); //파일 형식 만들고
            if (pictureFile == null) {
                Toast.makeText(mContext, "Error saving!!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                // data to bitmap and modify and data
                fos.write(data);
                fos.close();
                //Thread.sleep(500);

                // ocr 프로세스 시작
                _path = pictureFile.getAbsolutePath();
                String TAG1 = "noduriOCR";
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;

                //_path = DATA_PATH + "ocr2.jpg";
                Log.i("photo path : ", _path);

                Bitmap bitmap = BitmapFactory.decodeFile(_path, options);

                // bitmap image modified needed

                try {
                    ExifInterface exif = new ExifInterface(_path);
                    int exifOrientation = exif.getAttributeInt(
                            ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL);

                    Log.v(TAG1, "Orient: " + exifOrientation);

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

                    Log.v(TAG1, "Rotation: " + rotate);

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
                    Log.e(TAG1, "Couldn't correct orientation: " + e.toString());
                }

                // _image.setImageBitmap( bitmap );

                Log.v(TAG1, "Before baseApi");

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
                Log.v(TAG, "RESULT TEXT: " + recognizedText);
                /*

                if ( recognizedText.length() != 0 ) {
                    _field.setText(_field.getText().toString().length() == 0 ? recognizedText : _field.getText() + " " + recognizedText);
                    _field.setSelection(_field.getText().toString().length());
                }
                */

                // Cycle done.


                mCamera.startPreview();

            } catch (FileNotFoundException e) {
                Log.d(TAG1, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG1, "Error accessing file: " + e.getMessage());
            } /*catch (InterruptedException e) {
				e.printStackTrace();
			}*/
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //OCR DATA PATH ////////////////////////////////////////////////////////
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

        _path = DATA_PATH + "/ocr.jpg";
        ////////////////////////////////////////////////////////////////////////

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_book_add_ocr);

        ScanLineView mDraw = new ScanLineView(this);
        addContentView(mDraw, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        mContext = this;
        // 카메라 인스턴스 생성
        if (checkCameraHardware(mContext)) {
            mCamera = getCameraInstance();

            // 프리뷰창을 생성하고 액티비티의 레이아웃으로 지정합니다
            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);

            Button captureButton = (Button) findViewById(R.id.button_capture_ocr);
            captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // JPEG 콜백 메소드로 이미지를 가져옵니다
                    mCamera.takePicture(null, null, mPicture);
                }
            });
        }
        else{
            Toast.makeText(mContext, "no camera on this device!", Toast.LENGTH_SHORT).show();
        }

    }

    /** 카메라 하드웨어 지원 여부 확인 */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // 카메라가 최소한 한개 있는 경우 처리
            Log.i(TAG, "Number of available camera : "+Camera.getNumberOfCameras());
            return true;
        } else {
            // 카메라가 전혀 없는 경우
            Toast.makeText(mContext, "No camera found!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /** 카메라 인스턴스를 안전하게 획득합니다 */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
            // 사용중이거나 사용 불가능 한 경우
        }
        return c;
    }

    /** 이미지를 저장할 파일 객체를 생성합니다 */
    private static File getOutputMediaFile(){
        // SD카드가 마운트 되어있는지 먼저 확인해야합니다
        // Environment.getExternalStorageState() 로 마운트 상태 확인 가능합니다

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // 굳이 이 경로로 하지 않아도 되지만 가장 안전한 경로이므로 추천함.

        // 없는 경로라면 따로 생성한다.
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCamera", "failed to create directory");
                return null;
            }
        }

        // 파일명을 적당히 생성. 여기선 시간으로 파일명 중복을 피한다.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;

        //mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
        //this._path = new String(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
        Log.i("MyCamera", "Saved at"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));

        return mediaFile;
    }
    @Override
    public void onPause(){
        super.onPause();
        // 보통 안쓰는 객체는 onDestroy에서 해제 되지만 카메라는 확실히 제거해주는게 안전하다.

    }
}

class ScanLineView extends View {

    public ScanLineView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);                    // Cyan
        paint.setStrokeWidth(10);                     // 크기 10

        int centerX = getWidth()/2;
        int centerY = getHeight()/2;

        int rectWidth = getWidth() /3;

        paint.setColor(Color.CYAN);                        // 펜색깔
        paint.setStyle(Paint.Style.STROKE);                 // STROKE 빈화면
        canvas.drawRect(centerX - rectWidth, centerY - rectWidth, centerX + rectWidth, centerY + rectWidth, paint);   // 사각형

        paint.setColor(Color.RED);                        // 펜색깔
        paint.setStyle(Paint.Style.STROKE);                 // STROKE 빈화면
        canvas.drawRect(centerX - rectWidth*3/4, centerY - rectWidth*3/4, centerX + rectWidth*3/4, centerY + rectWidth*3/4, paint);   // 사각형

        super.onDraw(canvas);
    }

}
