package com.example.leejunbeom.bookMarker.ui.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.widget.ImageView;

import com.example.leejunbeom.test.R;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.imgproc.Imgproc;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 16. 5. 16..
 */


public class asd{

    static {
        System.loadLibrary("opencv_java");
        System.loadLibrary("nonfree");
    }

    private FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
    private DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
    DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT);

    private Bitmap firstBookBitMap;
    private MatOfKeyPoint keyPoints;
    private Mat firstBookMat;
    private Mat extractBookMat;
    private Context context;

    public asd(Bitmap bookBitMap, Context context){

        this.firstBookBitMap=bookBitMap;
        firstBookMat = new Mat();
        extractBookMat = new Mat();
        Utils.bitmapToMat(bookBitMap, firstBookMat);
        keyPoints = new MatOfKeyPoint();
        Imgproc.cvtColor(firstBookMat, firstBookMat, Imgproc.COLOR_RGBA2GRAY);
        detector.detect(firstBookMat, keyPoints);
        extractor.compute(firstBookMat, keyPoints, extractBookMat);
        this.context=context;

    }

    public void sift(Bitmap inputImage,ImageView imageView){

        Mat rgba = new Mat();
        Utils.bitmapToMat(inputImage, rgba);
        MatOfKeyPoint keyPoints = new MatOfKeyPoint();
        Imgproc.cvtColor(rgba, rgba, Imgproc.COLOR_RGBA2GRAY);
        detector.detect(rgba, keyPoints);
        Features2d.drawKeypoints(rgba, keyPoints, rgba);
        Utils.matToBitmap(rgba, inputImage);
        imageView.setImageBitmap(inputImage);

    }

    public Bitmap drawMatchedPoint(Bitmap CameraBitMap) {

        Mat desc2= new Mat();


        Mat rgba2 = new Mat();
        Utils.bitmapToMat(CameraBitMap, rgba2);
        rgba2=rgba2.colRange(CameraBitMap.getWidth()/3,CameraBitMap.getWidth()/3*2);
        MatOfKeyPoint keyPoints2 = new MatOfKeyPoint();
        Imgproc.cvtColor(rgba2, rgba2, Imgproc.COLOR_RGBA2GRAY);
        detector.detect(rgba2, keyPoints2);


        MatOfDMatch matches= new MatOfDMatch();
        extractor.compute(rgba2, keyPoints2, desc2);


        if(extractBookMat.type() == desc2.type() && extractBookMat.cols() == desc2.cols()){

                matcher.match(extractBookMat, desc2, matches);
                double max_dist = 0;
                double min_dist = 100;
                List<DMatch> matchesList = matches.toList();
                LinkedList<DMatch> listOfGoodMatches = new LinkedList<>();
                MatOfDMatch goodMatches = new MatOfDMatch();


                for (int i = 0; i < matchesList.size() ;i++) {
                    if (matchesList.get(i).distance < 30) {
                        listOfGoodMatches.add(matchesList.get(i));
                    }
                }

                goodMatches.fromList(listOfGoodMatches);

                Mat imageOut = new Mat();

                Features2d.drawMatches(firstBookMat, keyPoints, rgba2, keyPoints2, goodMatches, imageOut);
                Bitmap bitmap = Bitmap.createBitmap(imageOut.cols(), imageOut.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(imageOut, bitmap);
                if(listOfGoodMatches.size()>3){
                    Vibrator vibe = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(1000);
                }
            return bitmap;
        }
        return null;

    }
}
