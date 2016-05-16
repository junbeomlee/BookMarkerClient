package com.example.leejunbeom.bookMarker.ui.preview;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Jun on 16. 4. 11..
 */
public class CameraPreview implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private Camera mCamera = null;
    private ImageView MyCameraPreview = null;
    private Bitmap bitmap = null;
    private int[] pixels = null;
    private byte[] FrameData = null;
    private int imageFormat;
    private int PreviewSizeWidth;
    private int PreviewSizeHeight;
    private boolean bProcessing = false;
    private ImageView imageView2;

    Handler mHandler = new Handler(Looper.getMainLooper());

    public CameraPreview(int PreviewlayoutWidth, int PreviewlayoutHeight,
                         ImageView CameraPreview, ImageView imageView)
    {
        PreviewSizeWidth = PreviewlayoutWidth;
        PreviewSizeHeight = PreviewlayoutHeight;
        imageView2=imageView;
        MyCameraPreview = CameraPreview;
        bitmap = Bitmap.createBitmap(PreviewSizeWidth, PreviewSizeHeight, Bitmap.Config.ARGB_8888);
        pixels = new int[PreviewSizeWidth * PreviewSizeHeight];
    }

    @Override
    public void onPreviewFrame(byte[] arg0, Camera arg1) {
        // At preview mode, the frame data will push to here.
        if (imageFormat == ImageFormat.NV21) {
            //We only accept the NV21(YUV420) format.
            if (!bProcessing) {
                FrameData = arg0;
                imageView2.setImageBitmap(bitmap);
                // mHandler.post(DoImageProcessing);
                Camera.Parameters params = mCamera.getParameters();


                int w = params.getPreviewSize().width;

                int h = params.getPreviewSize().height;

                @SuppressWarnings("deprecation")
                int format = params.getPreviewFormat();

                YuvImage image = new YuvImage(arg0, format, w, h, null);

                ByteArrayOutputStream out = new ByteArrayOutputStream();

                Rect area = new Rect(0, 0, w, h);

                image.compressToJpeg(area, 50, out);
                System.out.println("getPreviewSize().width : "+w+"\n"
                        +"params.getPreviewSize().height : "+h);

                Bitmap asdbitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
                imageView2.setImageBitmap(asdbitmap);

            }
        }
    }

    public void onPause()
    {
        mCamera.stopPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
    {
        Camera.Parameters parameters;

        parameters = mCamera.getParameters();
        // Set the camera preview size
        parameters.setPreviewSize(PreviewSizeWidth, PreviewSizeHeight);

        imageFormat = parameters.getPreviewFormat();

        mCamera.setParameters(parameters);

        mCamera.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0)
    {
        mCamera = Camera.open();
        try
        {
            // If did not set the SurfaceHolder, the preview area will be black.
            mCamera.setPreviewDisplay(arg0);
            mCamera.setPreviewCallback(this);
        }
        catch (IOException e)
        {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0)
    {
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

}