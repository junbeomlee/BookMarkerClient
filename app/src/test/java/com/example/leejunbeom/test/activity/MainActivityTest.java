package com.example.leejunbeom.test.activity;

import android.content.Intent;
import android.widget.Button;

import com.example.leejunbeom.bookMarker.ui.activity.BookAddActivity;
import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;
import com.example.leejunbeom.test.BuildConfig;
import com.example.leejunbeom.test.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Jun on 16. 3. 24..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@Implements(isInAndroidSdk = false)
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
    public void should_bookAddactivity_start_test() {

        ShadowActivity shadowActivity = shadowOf(mainActivity);

        //when
        bookAddButton.performClick();

        //then
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat("ADDBook activtiy start fail",startedIntent.getComponent().getClassName(),
                equalTo(BookAddActivity.class.getName()));
    }
}
