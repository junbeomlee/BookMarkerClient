package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.Mainscreen;

import javax.inject.Inject;

/**
 * Created by Jun on 16. 3. 24..
 */
public class MainPresenter_impl implements MainPresenter {

    private BookController bookController;

    @Inject
    public MainPresenter_impl(BookController bookController){
        this.bookController=bookController;
    }

    public void onBookAddButtonClick(Mainscreen mainscreen) {
        mainscreen.launchAddBookActivity();
    }

    @Override
    public void onListViewItemClick(int position, Mainscreen mainscreen) {
        Book book=this.bookController.getItem(position);
        mainscreen.launchNaviActivity(book);
    }

    public BookController getBookController() {
        return bookController;
    }
}
