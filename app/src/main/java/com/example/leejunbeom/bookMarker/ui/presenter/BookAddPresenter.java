package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookAddScreen;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import net.htmlparser.jericho.Source;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Jun on 16. 3. 24..
 */
public class BookAddPresenter {

    private Jericho jericho;
    private HtmlParser htmlParser;

    public BookAddPresenter() {

    }

    public BookAddPresenter(Jericho jericho,HtmlParser htmlParser){
        this.jericho=jericho;
        this.htmlParser=htmlParser;
    }

    public void getBookData(String url){
        Source htmlSource=jericho.getURLtoText(url);
        Book book = (Book)htmlParser.sourceToObject(htmlSource);
        EventBus.getDefault().post(book);
    }

    public void finishActivity(BookAddScreen bookAddScreen){
        bookAddScreen.finishBookAddActivity();
    }

    public Jericho getJericho(){
        return jericho;
    }
}
