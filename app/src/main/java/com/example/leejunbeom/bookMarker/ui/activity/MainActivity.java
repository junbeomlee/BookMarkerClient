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
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.bookMarker.ui.adapter.BookAdapter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.Mainscreen;
import com.example.leejunbeom.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

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
    private ArrayList<Book> mBookList;

    @Inject
    MainPresenter mainPresenter;

    @Bind(R.id.bookAddButton)
    Button bookAddButton;

    @Bind(R.id.listView)
    SwipeMenuListView listView;


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


        //Book 예시
        this.mBookList = new ArrayList<Book>();
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setSymbolicRequest("1234");
        book2.setSymbolicRequest("4567");
        this.mBookList.add(book1);
        this.mBookList.add(book2);

        bAdapter = new BookAdapter_impl(this.getApplicationContext());
        bAdapter.setBookData(mBookList);
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
                        // not open
                        // delete
//					    delete(item);
                        //mBookList.remove(position);
                        //bAdapter.notifyDataSetChanged();
                        //Toast.makeText(getApplicationContext(),
                        //        item.getSymbolicRequest() + " is deleted",
                        //        Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
        //Long Click Listener Implement

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getApplicationContext(), mBookList.get(position).getSymbolicRequest() + " is long clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
        Intent intent = new Intent(this, NoduriActivity.class);
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

    @Subscribe
    public void onSetBookList(BookController bookController){
        //Toast.makeText(this,bookController.toString(),Toast.LENGTH_LONG).show();
        this.bAdapter.setBookData(bookController.getBookList());
        this.bAdapter.notifyDataSetChanged();
       // listView.setAdapter(bAdapter);
        //SwipeMenuCreator creator = new SwipeMenuCreator_impl(this.getApplicationContext());
        //listView.setMenuCreator(creator);
    }
}
