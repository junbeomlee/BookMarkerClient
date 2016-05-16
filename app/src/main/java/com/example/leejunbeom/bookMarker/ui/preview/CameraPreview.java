package com.example.leejunbeom.bookMarker.ui.preview;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.leejunbeom.bookMarker.model.SIFT;

import java.io.IOException;

/**
 * Created by Jun on 16. 4. 11..
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {

    String TAG = "CAMERA";
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private int imageFormat;
    private boolean bProcessing = false;
    private byte[] FrameData = null;
    private ImageView cameraPreview;
    private Context context;

    public CameraPreview(Context context, Camera camera,ImageView cameraPreview) {
        super(context);
        this.context = context;
        mCamera = camera;
        this.cameraPreview=cameraPreview;
        // SurfaceHolder 가 가지고 있는 하위 Surface가 파괴되거나 업데이트 될경우 받을 콜백을 세팅한다
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated 되었지만 3.0 이하 버젼에서 필수 메소드라서 호출해둠.
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Surface가 생성되었으니 프리뷰를 어디에 띄울지 지정해준다. (holder 로 받은 SurfaceHolder에 뿌려준다.
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "portrait");
                mCamera.setDisplayOrientation(90);
                parameters.setRotation(90);
            } else {
                parameters.set("orientation", "landscape");
                mCamera.setDisplayOrientation(0);
                parameters.setRotation(0);
            }
            mCamera.setParameters(parameters);

            mCamera.unlock();
//set up MediaRecorder
            mCamera.reconnect();
            mCamera.setPreviewDisplay(holder);
            //mCamera.setPreviewCallback((Camera.PreviewCallback)context);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 프리뷰 제거시 카메라 사용도 끝났다고 간주하여 리소스를 전부 반환한다
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera.Size getBestPreviewSize(int width, int height)
    {
        Camera.Size result=null;
        Camera.Parameters p = mCamera.getParameters();
        for (Camera.Size size : p.getSupportedPreviewSizes()) {
            if (size.width<=width && size.height<=height) {
                if (result==null) {
                    result=size;
                } else {
                    int resultArea=result.width*result.height;
                    int newArea=size.width*size.height;

                    if (newArea>resultArea) {
                        result=size;
                    }
                }
            }
        }
        return result;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // 프리뷰를 회전시키거나 변경시 처리를 여기서 해준다.
        // 프리뷰 변경시에는 먼저 프리뷰를 멈춘다음 변경해야한다.
        if (mHolder.getSurface() == null){
            // 프리뷰가 존재하지 않을때
            return;
        }

        // 우선 멈춘다
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // 프리뷰가 존재조차 하지 않는 경우다
        }

        // 프리뷰 변경, 처리 등을 여기서 해준다.
        Camera.Parameters parameters = mCamera.getParameters();
        Camera.Size size = getBestPreviewSize(w, h);
        parameters.setPreviewSize(size.width, size.height);
        mCamera.setParameters(parameters);
        // 새로 변경된 설정으로 프리뷰를 재생성한다
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {



        if (imageFormat == ImageFormat.NV21)
        {
            //We only accept the NV21(YUV420) format.
            if ( !bProcessing )
            {
                Log.d("asdasdasd","onPreviewFramE");
                FrameData = data;
                int width= camera.getParameters().getPictureSize().width;
                int height = camera.getParameters().getPictureSize().height;
                int rgb[]= new int[width*height];
                decodeYUV420SP(rgb, data, width, height);
                Bitmap bitmap=Bitmap.createBitmap(rgb, width, height, Bitmap.Config.RGB_565);
                SIFT sift = new SIFT();
                bitmap=sift.sift(bitmap);
                this.cameraPreview.setImageBitmap(bitmap);
            }
        }
    }

    static public void decodeYUV420SP(int[] rgba, byte[] yuv420sp, int width, int height) {
        final int frameSize = width * height;

        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0) y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }

                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0) r = 0; else if (r > 262143) r = 262143;
                if (g < 0) g = 0; else if (g > 262143) g = 262143;
                if (b < 0) b = 0; else if (b > 262143) b = 262143;

                //rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
                // rgba, divide 2^10 ( >> 10)
                rgba[yp] = ((r << 14) & 0xff000000) | ((g << 6) & 0xff0000) | ((b >> 2) | 0xff00);
            }
        }
    }
}