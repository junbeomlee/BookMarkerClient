package com.example.leejunbeom.bookMarker.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.model.SIFT;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    SIFT SIFT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector.get().inject(this);
        Log.i("asdasd", this.SIFT.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
    }



}
