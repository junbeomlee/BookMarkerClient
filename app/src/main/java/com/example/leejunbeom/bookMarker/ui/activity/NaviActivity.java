package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.model.BitMapController;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.model.pojo.Book;
import com.example.leejunbeom.bookMarker.ui.adapter.BookAdapter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.NaviPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.NaviPresenter_impl;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.NaviScreen;
import com.example.leejunbeom.test.R;

import java.util.ArrayList;

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

    @Bind(R.id.naviButton)
    Button searchButton;

    @Inject
    NaviPresenter naviPresenter;

    @Bind(R.id.libraryView)
    ImageView libraryView;

    @Bind(R.id.spinner2)
    Spinner spinner;

    Bitmap libraryMainBitMap;

    @Inject
    BitMapController bitMapController;

    private BookAdapter_impl bAdapter;

    //test
    
    //// TODO: 16. 4. 18.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        ((AppApplication) getApplication()).component().inject(this);
        ButterKnife.bind(this);
        libraryView.setImageBitmap(this.rotateImage(bitMapController.getBitMap("0"), 90));
        bAdapter = new BookAdapter_impl(this.getApplicationContext());

        ////문제점 spinner에는 한개의 원소가 추가되어야 하고 나머지는 bookController
        ////문제점 spinner에는 한개의 원소가 추가되어야 하고 나머지는 bookController의 arrayList이다. 효과적인 구조로 refactor해야한다.
        Book computedBook = new Book();
        computedBook.setMark("0");
        ArrayList<Book> bookArrayList= ((NaviPresenter_impl) naviPresenter).getBookController().getBookList();
        ArrayList<Book> spinnerArrayList = new ArrayList<Book>(bookArrayList);
        spinnerArrayList.add(0,computedBook);
        ////
        bAdapter.setBookData(spinnerArrayList);
        spinner.setAdapter(bAdapter);
        addlistener();
    }

    private void addlistener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    libraryView.setImageBitmap(rotateImage(bitMapController.getBitMap("0"), 90));
                }else{
                    Book book = ((NaviPresenter_impl) naviPresenter).getBookController().getBookList().get(position-1);
                    libraryView.setImageBitmap(rotateImage(bitMapController.getBitMap(book.getMark()), 90));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @OnClick(R.id.naviButton)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.libraryView.setImageBitmap(null);
    }

    public Bitmap rotateImage(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }

    //test
    public Button getSearchButton() {
        return searchButton;
    }

    //test
    public NaviPresenter getNaviPresenter() {
        return naviPresenter;
    }
}