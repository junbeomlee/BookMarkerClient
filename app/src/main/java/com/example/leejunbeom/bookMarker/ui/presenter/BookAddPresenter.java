package com.example.leejunbeom.bookMarker.ui.presenter;

import android.text.Html;
import android.util.Log;

import com.example.leejunbeom.bookMarker.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookAddScreen;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;
import com.example.leejunbeom.bookMarker.util.log.BMLogger;

import net.htmlparser.jericho.Source;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jun on 16. 3. 24..
 */
public class BookAddPresenter {

    private BookController bookController;
    private Jericho jericho;
    private HtmlParser htmlParser;

    public BookAddPresenter(Jericho jericho, HtmlParser htmlParser, BookController bookController) {
        this.jericho = jericho;
        this.htmlParser = htmlParser;
        this.bookController = bookController;
    }

    public void getBookData(String url) {

        String[] cidValue = url.split("\\?");

        Observable.
                just("http://library.cau.ac.kr/search/DetailView.ax?sid=1&" + cidValue[1]).map(new Func1<String, Book>() {
            @Override
            public Book call(String s) {
                Source htmltoString = jericho.getURLtoText(s);
                Book book = (Book) htmlParser.sourceToObject(htmltoString);
                return book;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Book>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Book book) {

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

    public Jericho getJericho() {
        return jericho;
    }
}
