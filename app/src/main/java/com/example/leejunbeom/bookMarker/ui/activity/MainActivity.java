package com.example.leejunbeom.bookMarker.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter_interface;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.Mainscreen;
import com.example.leejunbeom.bookMarker.util.log.BMLogger;
import com.example.leejunbeom.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 리스트 뷰로 bookController에 있는 책의 목록을 가져오고
 * 각 책을 누르면 BookInfoActivity가 실행된다. intent로 해당하는 ListView의 책 정보를 저장
 * bookAddbutton을누르면 bookAddActivity로 넘어 간다.
 */

public class MainActivity extends AppCompatActivity implements Mainscreen{

    @Inject
    MainPresenter_interface mainPresenter;

    @Inject
    Book book;

    @Bind(R.id.bookAddButton)
    Button bookAddButton;

   // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //injector.get().inject(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //SwipeMenuListView listView;
        //listView.setMenuCreator();
        ((AppApplication) getApplication()).component().inject(this);
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

    public MainPresenter_interface getMainPresenter(){
        return this.mainPresenter;
    }

    @Subscribe
    public void onSetBookList(BookController bookController){
        Toast.makeText(this,bookController.toString(),Toast.LENGTH_LONG).show();
    }

    public Book getBook(){
        return this.book;
    }
}
