package com.example.leejunbeom.test;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.model.SIFT;
import com.example.leejunbeom.bookMarker.ui.MainActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/*
 * Created by Jun on 16. 3. 13..
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest{

    @Rule
    public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);

    @Test
    public void viewTest(){
        onView(withId(R.id.imageView)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.button)).perform(ViewActions.click());
    }
}