package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookAddScreen;

import net.htmlparser.jericho.Source;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jun on 16. 3. 30..
 */
public interface BookAddPresenter {
    public void getBookData(String url);
    public void finishActivity(BookAddScreen bookAddScreen);
}
