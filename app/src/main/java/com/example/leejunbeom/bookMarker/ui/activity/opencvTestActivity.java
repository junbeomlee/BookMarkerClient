package com.example.leejunbeom.bookMarker.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

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
import org.opencv.imgproc.Imgproc;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class opencvTestActivity extends AppCompatActivity {

    static {
        System.loadLibrary("opencv_java");
        System.loadLibrary("nonfree");
    }
    private ImageView bookView;
    private ImageView bookListView;
    private ImageView matcherImageView;
    private Bitmap bookListBitMap; // make bitmap from image resource
    private Bitmap bookBitMap;
    private Bitmap matcherBitMap;
    private FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
    private DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.book);
        bookListBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.booklist);

        setContentView(R.layout.activity_opencv_test_activitiy);
        Bitmap.Config conf = Bitmap.Config.ARGB_4444; // see other conf types

        //bookView = (ImageView) this.findViewById(R.id.bookView);
        //bookListView = (ImageView) this.findViewById(R.id.bookListView);
        matcherImageView =(ImageView) this.findViewById(R.id.matcherImageView);

        Mat desc= new Mat();
        Mat desc2= new Mat();
        Mat rgba = new Mat();

        System.out.print("start");
        Utils.bitmapToMat(bookBitMap, rgba);
        MatOfKeyPoint keyPoints = new MatOfKeyPoint();
        Imgproc.cvtColor(rgba, rgba, Imgproc.COLOR_RGBA2GRAY);
        detector.detect(rgba, keyPoints);

        Mat rgba2 = new Mat();
        Utils.bitmapToMat(bookListBitMap, rgba2);
        MatOfKeyPoint keyPoints2 = new MatOfKeyPoint();
        Imgproc.cvtColor(rgba2, rgba2, Imgproc.COLOR_RGBA2GRAY);
        detector.detect(rgba2, keyPoints2);

        extractor.compute(rgba, keyPoints, desc);
        extractor.compute(rgba2, keyPoints2, desc2);

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT);

        MatOfDMatch matches= new MatOfDMatch();

        matcher.match(desc, desc2, matches);

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

        Features2d.drawMatches(rgba, keyPoints, rgba2, keyPoints2, matches, imageOut);
        Bitmap bitmap = Bitmap.createBitmap(imageOut.cols(), imageOut.rows(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(imageOut, bitmap);
        matcherImageView.setImageBitmap(bitmap);

        Log.d("asdasdasdasd",String.valueOf(listOfGoodMatches.size()));
    }

    @Override
    public void onResume() {
        super.onResume();
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
}
