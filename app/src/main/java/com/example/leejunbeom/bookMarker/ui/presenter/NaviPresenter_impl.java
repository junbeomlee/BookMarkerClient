package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.NaviScreen;

import javax.inject.Inject;

/**
 * Created by Jun on 16. 3. 28..
 */
public class NaviPresenter_impl implements NaviPresenter{

    private BookController bookController;

    @Inject
    public NaviPresenter_impl(BookController bookController) {
        this.bookController=bookController;
    }

    @Override
    public void onBookSearchButtonClick(NaviScreen naviScreen) {

    }

    @Override
    public void getMap(String symbolicRequest) {

    }

    public BookController getBookController() {
        return bookController;
    }
}
