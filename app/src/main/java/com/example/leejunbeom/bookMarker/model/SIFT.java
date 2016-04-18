package com.example.leejunbeom.bookMarker.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Jun on 16. 3. 12..
 */
public class SIFT {

    static {
        System.loadLibrary("opencv_java");
        System.loadLibrary("nonfree");
    }

    private ImageView imageView = null;
    private Bitmap inputImage = null; // make bitmap from image resource
    private FeatureDetector detector = FeatureDetector.create(FeatureDetector.SIFT);


    public SIFT(){

    }

    public SIFT(ImageView _imageView, Bitmap _inputImage){
        this.imageView = _imageView;
        this.inputImage = _inputImage;
    }

    public void sift() {
        Mat rgba = new Mat();
        Utils.bitmapToMat(inputImage, rgba);
        MatOfKeyPoint keyPoints = new MatOfKeyPoint();
        Imgproc.cvtColor(rgba, rgba, Imgproc.COLOR_RGBA2GRAY);
        detector.detect(rgba, keyPoints);
        Features2d.drawKeypoints(rgba, keyPoints, rgba);
        Utils.matToBitmap(rgba, inputImage);
        imageView.setImageBitmap(inputImage);
    }


    public void setImageView(ImageView imageView){
        this.imageView = imageView;
    }

    public ImageView getImageView(){
        return this.imageView;
    }

    public void setInputImage(Bitmap inputImage){
        this.inputImage = inputImage;
    }


    public Bitmap getInputImage(){
        return this.inputImage;
    }

}
