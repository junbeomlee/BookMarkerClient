package com.example.leejunbeom.bookMarker.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.Mainscreen;
import com.example.leejunbeom.test.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements Mainscreen{

    @Inject
    MainPresenter mainPresenter;

    @Bind(R.id.bookAddButton)
    Button bookAddButton;

   // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injector.get().inject(this);
        ButterKnife.bind(this);

        //SwipeMenuListView listView;
        //listView.setMenuCreator();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.bookAddButton)
    public void onCallClick(){
        this.mainPresenter.onBookAddButtonClick(this);
    }

    @Override
    public void launchAddBookActivity() {
        Intent intent = new Intent(this, BookAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void launchBookInfoActivity() {

    }

    @Override
    public void deleteBook() {

    }

    public MainPresenter getMainPresenter(){
        return this.mainPresenter;
    }
}
