package com.example.leejunbeom.test.dagger;

import com.example.leejunbeom.bookMarker.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter;
import com.example.leejunbeom.bookMarker.ui.presenter.MainPresenter_interface;
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
    MainPresenter_interface provideMainPresenter(){
        //MainPresenter_interface MockMainPresenter = Mockito.mock(MainPresenter_interface.class);
/*
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                MainActivity mainActivity=(MainActivity) args[0];
                //mainActivity.launchAddBookActivity();
                return null;
            }
        }).when(MockMainPresenter).onBookAddButtonClick(any(MainActivity.class));

        Mockito.verify(MockMainPresenter);*/
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
    BookController provideBookController(){
        return new BookController();
    }

    @Provides
    @Singleton
    BookAddPresenter provideBookAddPresenter(Jericho jericho,HtmlParser htmlParser,BookController bookController){
        return new BookAddPresenter(jericho,htmlParser,bookController);
    }

    @Provides
    @Singleton
    Book provideBook(){
        Book book=Mockito.mock(Book.class);
        Mockito.when(book.getDataType()).thenReturn("asd");
        return book;
    }
}
