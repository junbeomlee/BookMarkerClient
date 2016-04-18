package com.example.leejunbeom.bookMarker.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.leejunbeom.bookMarker.SwipeMenuListView.SwipeMenuCreator_impl;
import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.bookMarker.model.pojo.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.network.Network_impl;
import com.example.leejunbeom.bookMarker.ui.adapter.BookAdapter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.Mainscreen;
import com.example.leejunbeom.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    private BookAdapter_impl bAdapter;

    @Inject
    MainPresenter mainPresenter;

    @Bind(R.id.bookAddButton)
    Button bookAddButton;

    @Bind(R.id.listView)
    SwipeMenuListView listView;

    @Bind(R.id.ocrTestButton)
    Button ocrTestButton;

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

        bAdapter = new BookAdapter_impl(this.getApplicationContext());
        listView.setAdapter(bAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator_impl(this.getApplicationContext());
        listView.setMenuCreator(creator);
        addListener();
    }


    public void addListener(){

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //Book item = mBookList.get(position); // 예시
                switch (index) {
                    case 0:
                        onCallMenuItemClick(position);
                        break;
                }
                return false;
            }
        });

        //Long Click Listener Implement
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onCallItemClick(position);
            }
        });
    }

    private void onCallMenuItemClick(int position){
        this.mainPresenter.onListViewMenuItemClick(position);
    }
    private void onCallItemClick(int position){
        this.mainPresenter.onListViewItemClick(position, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.refreshListViewData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.bookAddButton, R.id.ocrTestButton})
    public void onCallClick(View v){
        switch(v.getId()){
            case R.id.bookAddButton:
                this.mainPresenter.onBookAddButtonClick(this);
                break;
            case R.id.ocrTestButton:
                this.mainPresenter.onOcrTestButtonClick(this);
        }
        //this.mainPresenter.onBookAddButtonClick(this);
    }


    /**
     * test
     */
    @Override
    public void launchAddBookActivity() {
        if(Network_impl.getInstance().isOnline(this)){
            Intent intent = new Intent(this, ImageMatchingActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Network Error!!",
                    Toast.LENGTH_LONG).show();
        }
    }


    //test
    @Override
    public void launchNaviActivity(Book book) {
        Intent intent = new Intent(this,NaviActivity.class);
        intent.putExtra("mark",book.getMark());
        startActivity(intent);
    }

    @Override
    public void launchOcrActivity(){
        Intent intent = new Intent(this,OcrActivity.class);
        startActivity(intent);

    }


    /**
     * test
     * @param bookController
     */
    @Subscribe
    public void onSetBookList(BookController bookController){
        this.bAdapter.setBookData(bookController.getBookList());
        this.bAdapter.notifyDataSetChanged();
    }

    //test
    public SwipeMenuListView getListView() {
        return listView;
    }

    //test
    public MainPresenter getMainPresenter(){
        return this.mainPresenter;
    }

    // test
    public Button getAddButton() {
        return bookAddButton;
    }
}

