package com.example.leejunbeom.test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.junit.Test;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Jun on 16. 4. 15..
 */
public class opencvTest {
    /*
    private FeatureDetector detector = FeatureDetector.create(FeatureDetector.SIFT);
    private DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);

    @Test
    public void testasd() throws Exception {
        Bitmap bookBitMap =BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.booklist);

        Mat rgba = new Mat();
        Mat desc= new Mat();

        Utils.bitmapToMat(bookBitMap, rgba);
        MatOfKeyPoint keyPoints = new MatOfKeyPoint();
        Imgproc.cvtColor(rgba, rgba, Imgproc.COLOR_RGBA2GRAY);
        detector.detect(rgba, keyPoints);

        extractor.compute(rgba, keyPoints, desc);
    }*/
}
