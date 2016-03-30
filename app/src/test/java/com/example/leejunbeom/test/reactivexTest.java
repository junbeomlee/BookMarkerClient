package com.example.leejunbeom.test;

import com.example.leejunbeom.bookMarker.network.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.util.html.HtmlBookParser;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import net.htmlparser.jericho.Source;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;


/**
 * Created by Jun on 16. 3. 25..
 */
public class reactivexTest {

    private Jericho jerichoImpl;
    private HtmlParser htmlParser;
    @Before
    public void setUp(){
        this.jerichoImpl = new Jericho();
        this.htmlParser = new HtmlBookParser();
    }

    @Test
    public void eventTest(){

        Observable.
                just("http://library.cau.ac.kr/search/DetailView.ax?sid=1&cid=63353").map(new Func1<String, Book>() {
            @Override
            public Book call(String s) {

                Source htmltoString = jerichoImpl.getURLtoText(s);
                Book book = (Book) htmlParser.sourceToObject(htmltoString);
                return book;
            }
        }).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers
                        .mainThread())
                .subscribe(new Subscriber<Book>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Book book) {


                    }
                });

    }
}
