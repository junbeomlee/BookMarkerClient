package com.example.leejunbeom.bookMarker.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.test.R;

public class ImageMatchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_matching);


        ImageView siftImageView = (ImageView)findViewById(R.id.siftImageView);
        Bitmap inputImage = BitmapFactory.decodeResource(getResources(), R.drawable.test);

        Log.d("ImageMatching", "init complete");

        Log.d("ImageMatching", "Before sift");
        SIFT sift = new SIFT();
        Log.d("ImageMatching", "new object is created");
        sift.setInputImage(inputImage);
        Log.d("ImageMatching", sift.getInputImage().toString());
        sift.setImageView(siftImageView);
        Log.d("ImageMatching", sift.getImageView().toString());
        sift.sift();
        Log.d("ImageMatching", "after sift");
        //sift.setImageView(siftImageView);
        //sift.setInputImage(inputImage);

    }
}
