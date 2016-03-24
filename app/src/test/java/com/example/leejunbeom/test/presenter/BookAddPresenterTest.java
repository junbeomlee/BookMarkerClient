package com.example.leejunbeom.test.presenter;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jun on 16. 3. 24..
 */
public class BookAddPresenterTest {

    //@Inject
    //BookAddPresenter bookAddPresenter;

    @Before
    public void setUp(){
        //injector.get().inject(this);
    }

    @After
    public void tearDown(){

    }

    @Test
    public void should_inject_Jericho_work(){
       // assertNotNull("get Jericho faild", bookAddPresenter.getJericho());
    }
}
