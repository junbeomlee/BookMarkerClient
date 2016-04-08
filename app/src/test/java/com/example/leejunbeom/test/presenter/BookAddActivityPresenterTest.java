package com.example.leejunbeom.test.presenter;

import com.example.leejunbeom.bookMarker.network.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter_impl;
import com.example.leejunbeom.bookMarker.util.html.HtmlParser;
import com.example.leejunbeom.test.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jun on 16. 3. 24..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class BookAddActivityPresenterTest {

    //@Inject
    BookAddPresenter_impl bookAddPresenter;
    BookController bookController;
    Jericho jerichoImpl;
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
