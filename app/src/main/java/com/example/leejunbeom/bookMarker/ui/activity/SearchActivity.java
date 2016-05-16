package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.model.SIFT;
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
    private CameraPreview mPreview;
    private ImageView ImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_search);
        mContext = this;

        // 카메라 사용여부 체크
        if (!checkCameraHardware(getApplicationContext())) {
            finish();
        }

        // 카메라 인스턴스 생성
        mCamera = getCameraInstance();

        // 프리뷰창을 생성하고 액티비티의 레아이웃으로 지정
        ImageView = (ImageView) findViewById(R.id.imageView);
        mPreview = new CameraPreview(this, mCamera,ImageView);
        CameraPreviewCallBack cameraPreviewCallBack=new CameraPreviewCallBack();
        mCamera.setPreviewCallback(cameraPreviewCallBack);

        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        // 촬영버튼 등록
        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });
    }

    /**
     * 카메라 사용여부 가능 체크
     *
     * @param context
     * @return
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.i(TAG, "Number of available camera : " + Camera.getNumberOfCameras());
            return true;
        } else {
            Toast.makeText(context, "No camera found!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 카메라 인스턴스 호출
     *
     * @return
     */
    public Camera getCameraInstance() {
        try {
            // open() 의 매개변수로 int 값을 받을 수 도 있는데, 일반적으로 0이 후면 카메라, 1이 전면 카메라를 의미합니다.
            mCamera = Camera.open();

        } catch (Exception e) {
            Log.i(TAG, "Error : Using Camera");
            e.printStackTrace();
        }
        return mCamera;
    }

    /**
     * 이미지를 저장할 파일 객체를 생성
     * 저장되면 Picture 폴더에 MyCameraApp 폴더안에 저장된다. (MyCameraApp 폴더명은 변경가능)
     */
    private static File getOutputMediaFile() {
        //SD 카드가 마운트 되어있는지 먼저 확인
        // Environment.getExternalStorageState() 로 마운트 상태 확인 가능합니다
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");

        // 없는 경로라면 따로 생성
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCamera", "failed to create directory");
                return null;
            }
        }

        // 파일명을 적당히 생성, 여기선 시간으로 파일명 중복을 피한다
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;

        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timestamp + ".jpg");
        Log.i("MyCamera", "Saved at" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        System.out.println(mediaFile.getPath());
        return mediaFile;
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // JPEG 이미지가 byte[] 형태로 들어옵니다.
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                Toast.makeText(mContext, "Error camera image saving", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                //Thread.sleep(500);
                mCamera.startPreview();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };

    public class CameraPreviewCallBack implements Camera.PreviewCallback {

        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            int a =0;

        }
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        /*Size size = mCamera.getParameters().getPreviewSize();
        PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(data, size.width, size.height, 0, 0, size.width, size.height, false);
        HybridBinarizer hybBin = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(hybBin);

        ImageView myImage = (ImageView) findViewById(R.id.foto);

        try {
            result = reader.decode(bitmap);
            Log.d("Result", "Result found!: " + String.valueOf(result));

            myImage.setVisibility(View.VISIBLE);

            if (String.valueOf(result).contentEquals("1"))
                myImage.setImageResource(R.drawable.juan);
            else if (String.valueOf(result).contentEquals("2"))
                myImage.setImageResource(R.drawable.antonio);

        } catch (NotFoundException e1) {

            if (myImage != null)
                myImage.setVisibility(View.INVISIBLE);

            Log.d("NotFoundException", "NotFoundException");
        } finally {
            reader.reset();
        }*/
    }
}
