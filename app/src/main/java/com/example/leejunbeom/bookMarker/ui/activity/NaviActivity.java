package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.ui.presenter.NaviPresenter;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookInfoScreen;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.NaviScreen;
import com.example.leejunbeom.test.R;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jun on 16. 3. 17..
 */


/**
 * 액비티에 책 정보를 띄우고
 * 서치버튼을 누르면 custom카메라 액티비티를 띄워서 서칭을 시작한다.
 */

public class NaviActivity extends AppCompatActivity implements NaviScreen{

    @Bind(R.id.searchButton)
    Button searchButton;

    @Bind(R.id.symbolicRequestText)
    TextView symbolicRequestText;

    String symbolicRequest;

    @Inject
    NaviPresenter naviPresenter;

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        ((AppApplication) getApplication()).component().inject(this);
        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);

        Intent intent= getIntent();
        symbolicRequest=intent.getStringExtra("mark");
        symbolicRequestText.setText(symbolicRequest);
        naviPresenter.getMap(symbolicRequest);
    }


    @OnClick(R.id.searchButton)
    public void onSearchButton(){
        naviPresenter.onBookSearchButtonClick(this);
    }


    @Override
    public void launchSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    //test
    public Button getSearchButton() {
        return searchButton;
    }

    //test
    public TextView getSymbolicRequestText() {
        return symbolicRequestText;
    }

    //test
    public String getSymbolicRequest() {
        return symbolicRequest;
    }

    //test
    public NaviPresenter getNaviPresenter() {
        return naviPresenter;
    }
}