package com.example.leejunbeom.bookMarker.dagger.module;

import com.example.leejunbeom.bookMarker.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.bookMarker.network.Network;
import com.example.leejunbeom.bookMarker.network.Network_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
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
        return new MainPresenter();
    }

    @Provides
    @Singleton
    Jericho provideJericho(){
        return new Jericho();
    }



    @Provides
    @Singleton
    HtmlParser provideHtmlParser(){
        return new HtmlBookParser();
    }

    @Provides
    @Singleton
    BookAddPresenter provideBookAddPresenter(Jericho jericho,HtmlParser htmlParser){
        return new BookAddPresenter(jericho,htmlParser);
    }
}
