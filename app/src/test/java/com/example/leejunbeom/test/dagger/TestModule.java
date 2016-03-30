package com.example.leejunbeom.test.dagger;

import com.example.leejunbeom.bookMarker.network.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter_impl;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter_impl;
import com.example.leejunbeom.bookMarker.util.html.HtmlBookParser;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jun on 16. 3. 28..
 */
@Module
public class TestModule {

    @Provides
    @Singleton
    MainPresenter provideMainPresenter(){
        return new MainPresenter_impl();
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
    BookController provideBookController(){
        return new BookController();
    }

    @Provides
    @Singleton
    BookAddPresenter provideBookAddPresenter(Jericho jerichoImpl,HtmlParser htmlParser,BookController bookController){
        return new BookAddPresenter_impl(jerichoImpl,htmlParser,bookController);
    }

    @Provides
    @Singleton
    Book provideBook(){
        Book book=Mockito.mock(Book.class);
        Mockito.when(book.getDataType()).thenReturn("asd");
        return book;
    }
}
