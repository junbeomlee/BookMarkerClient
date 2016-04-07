package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.network.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookAddScreen;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.NaviScreen;
import com.example.leejunbeom.bookMarker.util.html.HtmlBookParser;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import net.htmlparser.jericho.Source;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.plugins.RxJavaPlugins;

/**
 * Created by Jun on 16. 3. 24..
 */
public class BookAddPresenter_impl implements BookAddPresenter{

    private BookController bookController;
    private Jericho jericho;
    private HtmlParser htmlParser=new HtmlBookParser();

    @Inject
    public BookAddPresenter_impl(Jericho jerichoImpl, BookController bookController) {
        this.jericho=jerichoImpl;
        this.bookController=bookController;
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
                        /*bookController.addBook(book);
                        //update bookaddview
                        EventBus.getDefault().post(book);
                        //update listView in mainview
                        EventBus.getDefault().post(bookController);*/
                    }
                });
    }

    @Override
    public void finishActivity(BookAddScreen bookAddScreen) {
        bookAddScreen.finishBookAddActivity();
    }
}
