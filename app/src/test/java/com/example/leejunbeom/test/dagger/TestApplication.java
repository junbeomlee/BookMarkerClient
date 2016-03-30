package com.example.leejunbeom.test.dagger;

//import com.example.leejunbeom.bookMarker.dagger.DaggerTestApplication_TestApplicationComponent;
import com.example.leejunbeom.bookMarker.dagger.application.AppApplication;
import com.example.leejunbeom.bookMarker.ui.activity.BookAddActivity;
import com.example.leejunbeom.bookMarker.ui.activity.BookInfoActivity;
import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

//import com.example.leejunbeom.bookMarker.dagger.application.DaggerTestApplication_TestApplicationComponent;

/**
 * Created by Jun on 16. 3. 28..
 */
public class TestApplication extends AppApplication {


    @Singleton
    @Component(modules = {TestModule.class})
    public interface TestApplicationComponent extends ApplicationComponent {
        void inject(MainActivity mainActivity);
        void inject(BookAddActivity bookAddActivity);
        void inject(BookInfoActivity bookInfoActivity);
    }

    private TestApplicationComponent component;

    @Override public void onCreate() {
        super.onCreate();
        component=DaggerTestApplication_TestApplicationComponent.builder().testModule(new TestModule()).build();
    }

    @Override public ApplicationComponent component() {
        return component;
    }
}