package com.example.leejunbeom.test.presenter;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;
import com.example.leejunbeom.test.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jun on 16. 3. 24..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class BookAddActivityPresenterTest {

    //@Inject
    BookAddPresenter bookAddPresenter;
    BookController bookController;
    Jericho jericho;
    HtmlParser htmlParser;
    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void should_inject_Jericho_work(){
       // assertNotNull("get Jericho faild", bookAddPresenter.getJericho());
    }
}
