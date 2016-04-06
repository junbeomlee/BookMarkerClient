package com.example.leejunbeom.bookMarker.dagger.application;

import android.app.Application;

import com.example.leejunbeom.bookMarker.dagger.module.AppModule;
import com.example.leejunbeom.bookMarker.ui.activity.BookAddActivity;
import com.example.leejunbeom.bookMarker.ui.activity.NaviActivity;
import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jun on 16. 3. 28..
 */
public class AppApplication extends Application {

    @Singleton
    @Component(modules = {AppModule.class})
    public interface ApplicationComponent {
        void inject(MainActivity mainActivity);
        void inject(BookAddActivity bookAddActivity);
        void inject(NaviActivity naviActivity);
    }

    private ApplicationComponent component;

    @Override public void onCreate() {
        super.onCreate();
        component = DaggerAppApplication_ApplicationComponent.builder().appModule(new AppModule()).build();
    }

    public ApplicationComponent component() {
        return component;
    }
}
