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
import android.widget.AdapterView;
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
import com.example.leejunbeom.bookMarker.SwipeMenuListView.BookAdapter_impl;

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

public class MainActivity extends AppCompatActivity implements Mainscreen {

    //private AppAdapter mAdapter;
    private BookAdapter_impl bAdapter;
    private ArrayList<Book> mBookList;
    private BookController mBookController;

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


        //Book 예시
        this.mBookList = new ArrayList<Book>();
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setSymbolicRequest("1234");
        book2.setSymbolicRequest("4567");
        this.mBookList.add(book1);
        this.mBookList.add(book2);

        bAdapter = new BookAdapter_impl(this.getApplicationContext(), this.mBookList);
        listView.setAdapter(bAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator_impl(this.getApplicationContext());
        listView.setMenuCreator(creator);

        // clickListener Impl
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Book item = mBookList.get(position); // 예시
                switch (index) {
                    case 0:
                        // not open
                        // delete
//					    delete(item);
                        mBookList.remove(position);
                        bAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),
                                        item.getSymbolicRequest() + " is deleted",
                                        Toast.LENGTH_LONG).show();
                        break;
                    /*
                    case 1:
                        // delete
//					    delete(item);
                        mBookList.remove(position);
                        bAdapter.notifyDataSetChanged();
                        break;
                     */
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
        Intent intent = new Intent(this, BookAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void launchBookInfoActivity() {

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

            TextView textView = (TextView) convertView.findViewById(R.id.book_name);
            textView.setText(mBookList.get(0).getSymbolicRequest());

            return convertView;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}


