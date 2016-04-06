package com.example.leejunbeom.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.leejunbeom.bookMarker.ui.activity.BookAddActivity;
import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;
import com.example.leejunbeom.bookMarker.ui.activity.NaviActivity;
import com.example.leejunbeom.bookMarker.ui.activity.SearchActivity;
import com.example.leejunbeom.test.BuildConfig;
import com.example.leejunbeom.test.R;
import com.example.leejunbeom.test.dagger.TestApplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Jun on 16. 3. 28..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,application = TestApplication.class)
public class NaviActivityTest {

    NaviActivity naviActivity;

    @Before
    public void setUp() {

        /**
         * intent를 넣어서 activity 생성
         */
        Intent intent = new Intent(ShadowApplication.getInstance().getApplicationContext(), NaviActivity.class);
        intent.putExtra("symbolicRequest","123123");
        naviActivity = Robolectric.buildActivity(NaviActivity.class).withIntent(intent).create().get();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void onCreateTest(){
        assertNotNull("fail bind search button",naviActivity.getSearchButton());
        assertNotNull("fail bind 청구기호 text",naviActivity.getSymbolicRequestText());
        assertEquals("fail put text on 청구기호 text","123123",naviActivity.getSymbolicRequest());
    }

    @Test
    public void naviLaunchSearchActivityTest() {
        ShadowActivity shadowActivity = shadowOf(naviActivity);

        //when
        Button launchSearchActivityButton = naviActivity.getSearchButton();
        launchSearchActivityButton.performClick();

        //then
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat("search activtiy start fail", startedIntent.getComponent().getClassName(),
                equalTo(SearchActivity.class.getName()));
    }
}