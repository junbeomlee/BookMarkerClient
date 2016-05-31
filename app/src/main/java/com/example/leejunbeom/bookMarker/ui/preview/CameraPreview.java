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
import java.util.List;

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
    private Bitmap bookBitMap;
    private asd asd;

    private Context context;
    Handler mHandler = new Handler(Looper.getMainLooper());

    public CameraPreview(int PreviewlayoutWidth, int PreviewlayoutHeight,
                         ImageView CameraPreview, Bitmap bookBitMap,Context context)
    {
        PreviewSizeWidth = PreviewlayoutWidth;
        PreviewSizeHeight = PreviewlayoutHeight;
        this.context=context;
        //imageView2=imageView;
        MyCameraPreview = CameraPreview;
        //bitmap = Bitmap.createBitmap(PreviewSizeWidth, PreviewSizeHeight, Bitmap.Config.ARGB_8888);
        //pixels = new int[PreviewSizeWidth * PreviewSizeHeight];
        this.bookBitMap=bookBitMap;
        asd=new asd(bookBitMap,context);
    }

    @Override
    public void onPreviewFrame(byte[] arg0, Camera arg1) {
        // At preview mode, the frame data will push to here.
        if (imageFormat == ImageFormat.NV21) {
            //We only accept the NV21(YUV420) format.
            if (!bProcessing) {
                //bProcessing=true;

                FrameData = arg0;
                //imageView2.setImageBitmap(bitmap);
                // mHandler.post(DoImageProcessing);
                Camera.Parameters params = mCamera.getParameters();
                int w = params.getPreviewSize().width;
                int h = params.getPreviewSize().height;

                @SuppressWarnings("deprecation")
                int format = params.getPreviewFormat();

                YuvImage image = new YuvImage(arg0, format, w, h, null);

                ByteArrayOutputStream out = new ByteArrayOutputStream();

                Rect area = new Rect(0, 0, w, h);

                image.compressToJpeg(area, 100, out);
                System.out.println("getPreviewSize().width : " + w + "\n"
                        + "params.getPreviewSize().height : " + h + "booksize:" + bookBitMap.getWidth());


                Bitmap asdbitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
                System.out.print(asdbitmap.toString());
                //asd.sift(asdbitmap,imageView2);
                Bitmap bitmap=asd.drawMatchedPoint(asdbitmap);
                if(bitmap!=null){
                    MyCameraPreview.setImageBitmap(bitmap);
                }

                //mCamera.setParameters(params);
                //imageView2.setImageBitmap(asdbitmap);
                //bProcessing=false;
            }
        }
    }

    public void onPause()
    {
        mCamera.stopPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int w, int h)
    {
        Camera.Parameters parameters;

        parameters = mCamera.getParameters();
        //parameters.setPictureSize(MyCameraPreview.getWidth(),MyCameraPreview.getHeight());
        // Set the camera preview size
        //parameters.getPictureSize().width

        //Camera.Parameters.
        List<Camera.Size> tmpList=mCamera.getParameters().getSupportedPreviewSizes();
        Log.d("camera====",parameters.flatten());
        //Camera.Size size = getBestPreviewSize(w, h);
        parameters.setPreviewSize(1920,1088);
        imageFormat = parameters.getPreviewFormat();

        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        mCamera.startPreview();
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