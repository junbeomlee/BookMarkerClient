package com.example.leejunbeom.test.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Button;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.activity.BookAddActivity;
import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;
import com.example.leejunbeom.test.BuildConfig;
import com.example.leejunbeom.test.R;
import com.example.leejunbeom.test.dagger.TestApplication;

import org.greenrobot.eventbus.EventBus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowHandler;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Jun on 16. 3. 24..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,application = TestApplication.class)
public class MainActivityTest {

    MainActivity mainActivity;
    Button bookAddButton;
    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        bookAddButton = (Button) mainActivity.findViewById(R.id.bookAddButton);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void should_mainactivity_setUp_properly_test(){
        assertNotNull("fail setup listview", mainActivity.getListView());
        assertNotNull("fail setUp bookaddbutton",mainActivity.getAddButton());
        assertNotNull("fail setup mainpresenter",mainActivity.getMainPresenter());
    }
    @Test
    public void should_bookAddactivity_start_test() {

        ShadowActivity shadowActivity = shadowOf(mainActivity);

        //when
        bookAddButton.performClick();

        //then
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat("ADDBook activtiy start fail",startedIntent.getComponent().getClassName(),
                equalTo(BookAddActivity.class.getName()));
    }

    @Test
    public void should_bookListdata_change_test(){

        BookController bookControllerMock=Mockito.mock(BookController.class);
        ArrayList<Book> bookArrayList=new ArrayList<Book>();
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setSymbolicRequest("1234");
        book2.setSymbolicRequest("4567");
        bookArrayList.add(book1);
        bookArrayList.add(book2);
        when(bookControllerMock.getBookList()).thenReturn(bookArrayList);
        mainActivity.onSetBookList(bookControllerMock);

        SwipeMenuListView listView=mainActivity.getListView();
        assertEquals("update listView fail", 2, listView.getCount());
    }

    // TODO: 16. 4. 11.
    @Test
    public void test_should_toast_message_when_network_is_unable() throws Exception {
        //mainActivity
    }
}
