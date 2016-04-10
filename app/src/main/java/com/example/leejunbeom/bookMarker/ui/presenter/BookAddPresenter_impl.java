package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.network.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookAddScreen;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import net.htmlparser.jericho.Source;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jun on 16. 3. 24..
 */
public class BookAddPresenter_impl implements BookAddPresenter{

    private BookController bookController;
    private Jericho jericho;

    @Inject
    public BookAddPresenter_impl(Jericho jerichoImpl, BookController bookController) {
        this.jericho=jerichoImpl;
        this.bookController=bookController;
    }

    public void getBookData(String url) {

        String[] cidValue = url.split("\\?");

        this.jericho.postBook(cidValue[1]).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Book>() {
                    @Override
                    public void call(Book book) {
                        //add book
                        bookController.addBook(book);
                        //update bookaddview
                        EventBus.getDefault().post(book);
                        //update listView in mainview
                        EventBus.getDefault().post(bookController);
                    }
                });


    }

    public void finishActivity(BookAddScreen bookAddScreen) {
        bookAddScreen.finishBookAddActivity();
    }

    public BookController getBookController() {
        return bookController;
    }
}
