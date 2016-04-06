package com.example.leejunbeom.bookMarker.dagger.module;

import com.example.leejunbeom.bookMarker.network.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.NaviPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.NaviPresenter_impl;
import com.example.leejunbeom.bookMarker.util.html.HtmlBookParser;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jun on 16. 3. 12..
 */
@Module
public class AppModule {

   /* @Provides
    @Singleton
    SIFT provideTest(){
        return new SIFT();
    }*/

    @Provides
    @Singleton
    MainPresenter provideMainPresenter(){
        return new MainPresenter_impl();
    }

    @Provides
    @Singleton
    HtmlParser provideHtmlParser(){
        return new HtmlBookParser();
    }

    @Provides
    @Singleton
    Jericho provideJericho(HtmlParser htmlParser){
        return new Jericho(htmlParser);
    }
    @Provides
    @Singleton
    BookController provideBookController(){
        return new BookController();
    }

    @Provides
    @Singleton
    BookAddPresenter provideBookAddPresenter(Jericho jerichoImpl,BookController bookController){
        return new BookAddPresenter_impl(jerichoImpl,bookController);
    }

    @Provides
    @Singleton
    NaviPresenter provideNaviPresenter(BookController bookController){
        return new NaviPresenter_impl(bookController);
    }
}
