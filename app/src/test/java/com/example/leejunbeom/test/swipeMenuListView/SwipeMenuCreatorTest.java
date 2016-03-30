package com.example.leejunbeom.test.swipeMenuListView;

import android.content.Context;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by noduritoto on 2016-03-29.
 */
public class SwipeMenuCreatorTest {
    // for Logic Test

    // member var
    private SwipeMenu menu;
    Context context;

    @Before
    public void setUp(){
        this.menu = new SwipeMenu(context);

    }

    @After
    public void tearDown() {

    }

    @Test
    public void test_create(){
        createMenu1(menu);

    }
    @Test
    public void createMenu1(SwipeMenu menu){

    }
}
