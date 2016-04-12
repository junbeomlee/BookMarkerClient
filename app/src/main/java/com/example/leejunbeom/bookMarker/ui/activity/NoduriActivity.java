package com.example.leejunbeom.bookMarker.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.test.R;

public class NoduriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noduri);


        ImageView imageView = (ImageView) findViewById(R.id.noduriImageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.noduriImageView2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test5);

        imageView.setImageBitmap(bitmap);


        /*
        BitmapDrawable bd = null;
        bd = (BitmapDrawable) getResources().getDrawable(R.drawable.test, null);
        */


        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.test6);

        SIFT sift = new SIFT(imageView2, bitmap2);
        sift.sift();



    }

}
