package com.example.leejunbeom.bookMarker.dagger;

import com.example.leejunbeom.bookMarker.dagger.component.AppComponent;
import com.example.leejunbeom.bookMarker.dagger.component.DaggerAppComponent;
import com.example.leejunbeom.bookMarker.dagger.module.AppModule;

/**
 * Created by Jun on 16. 3. 12..
 */
public class injector {

    private static AppComponent appComponent= DaggerAppComponent.builder().appModule(new AppModule()).build();
    public static AppComponent get() {
        return appComponent;
    }

}
