package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.test.R;

import butterknife.ButterKnife;

/**
 * Created by Jun on 16. 3. 28..
 */
public class SearchActivity extends AppCompatActivity {

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        ((AppApplication) getApplication()).component().inject(this);
        ButterKnife.bind(this);
    }
}
