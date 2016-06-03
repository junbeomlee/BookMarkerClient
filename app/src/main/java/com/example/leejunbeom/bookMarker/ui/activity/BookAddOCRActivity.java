package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.model.OCR.ocrProcessing.AsyncProcessTask;
import com.example.leejunbeom.bookMarker.ui.preview.CameraPreview;
import com.example.leejunbeom.bookMarker.ui.preview.OCRCameraPreview;
import com.example.leejunbeom.bookMarker.ui.preview.OCRScanLineView;
import com.example.leejunbeom.test.R;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookAddOCRActivity extends AppCompatActivity {

    String TAG = "CAMERA";
    private Context mContext = this;
    private Camera mCamera;
    private OCRCameraPreview mPreview;

    String _path = "hello world path";
    String resultString;
    String outputPath = "result.txt";

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // JPEG 이미지가 byte[] 형태로 들어옵니다

            String TAG1 = "noduri_TAG1";
            File pictureFile = getOutputMediaFile(); //파일 형식 만들고
            _path = pictureFile.getAbsolutePath();
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

                //Remove output file
                deleteFile(outputPath);

                // activity 전환
                Intent results = new Intent(BookAddOCRActivity.this, OCRResultActivity.class);
                results.putExtra("IMAGE_PATH", _path);
                results.putExtra("RESULT_PATH", outputPath);
                Log.i("input path1", _path);
                Log.i("output path1", outputPath);
                startActivity(results);
                finish();
                //mCamera.startPreview();

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


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_book_add_ocr);

        OCRScanLineView mDraw = new OCRScanLineView(this);
        addContentView(mDraw, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        mContext = this;
        // 카메라 인스턴스 생성
        if (checkCameraHardware(mContext)) {
            mCamera = getCameraInstance();

            // 프리뷰창을 생성하고 액티비티의 레이아웃으로 지정합니다
            mPreview = new OCRCameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.ocr_camera_preview);
            preview.addView(mPreview);

            Button captureButton = (Button) findViewById(R.id.button_capture_ocr);
            captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // JPEG 콜백 메소드로 이미지를 가져옵니다
                    mCamera.takePicture(null, null, mPicture);
                    // ocr processing////////////////
                    //Remove output file
                    deleteFile(outputPath);

                    // activity 전환
                    Intent results = new Intent(BookAddOCRActivity.this, OCRResultActivity.class);
                    results.putExtra("IMAGE_PATH", _path);
                    results.putExtra("RESULT_PATH", outputPath);
                    Log.i("input path1", _path);
                    Log.i("output path1", outputPath);
                    startActivity(results);

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
        Log.i("MyCamera", "Saved at"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));

        return mediaFile;
    }
    @Override
    public void onPause(){
        super.onPause();
        // 보통 안쓰는 객체는 onDestroy에서 해제 되지만 카메라는 확실히 제거해주는게 안전하다.

    }

    public void updateResults(Boolean success) {
        if (!success)
            return;
        try {
            StringBuffer contents = new StringBuffer();

            FileInputStream fis = openFileInput(outputPath);
            try {
                Reader reader = new InputStreamReader(fis, "UTF-8");
                BufferedReader bufReader = new BufferedReader(reader);
                String text = null;
                while ((text = bufReader.readLine()) != null) {
                    contents.append(text).append(System.getProperty("line.separator"));

                }
            } finally {
                fis.close();
            }

            //displayMessage(contents.toString());
            String resultText = contents.toString();
            Log.i("result", resultText);
        } catch (Exception e) {
            Log.e("Error: " , e.getMessage());
        }
    }


}

