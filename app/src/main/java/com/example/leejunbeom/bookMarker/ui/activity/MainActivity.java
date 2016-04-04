package com.example.leejunbeom.bookMarker.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.leejunbeom.bookMarker.SwipeMenuListView.SwipeMenuCreator_impl;
import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.Mainscreen;
import com.example.leejunbeom.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    private AppAdapter mAdapter;
    private BookA bAdapter;
    private List<Book> mBookList;

    @Inject
    MainPresenter mainPresenter;
    //SwipeMenuCreator smc;

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
        //listView.setMenuCreator();
        ((AppApplication) getApplication()).component().inject(this);

        //SMLV work
        //listView = (SwipeMenuListView) findViewById(R.id.listView);

        /*mBookList = new ArrayList<Book>();
        Book bookExample = new Book();
        bookExample.setSymbolicRequest("1234");

        mAdapter = new AppAdapter();

        listView.setAdapter(mAdapter);*/

       // SwipeMenuCreator smc = new SwipeMenuCreator(this);
        //listView.setMenuCreator(smc);


        ArrayList<String> myString = new ArrayList<String>();
        myString.add("asd");
        myString.add("1111");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myString);
        //listView = (SwipeMenuListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator_impl(this.getApplicationContext());

        listView.setMenuCreator(creator);
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

    @Subscribe
    public void onSetBookList(BookController bookController){
        Toast.makeText(this,bookController.toString(),Toast.LENGTH_LONG).show();
    }

    class AppAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mBookList.size();
        }

        @Override
        public Object getItem(int position) {
            return mBookList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //TextView textView = new TextView();
            //textView.setText(mBookList.get(position));

            TextView textView = (TextView) convertView.findViewById(R.id.listViewText);
            textView.setText(mBookList.get(0).getSymbolicRequest());

            return convertView;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}


