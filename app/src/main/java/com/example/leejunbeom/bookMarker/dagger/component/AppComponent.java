package com.example.leejunbeom.bookMarker.dagger.component;

import com.example.leejunbeom.bookMarker.dagger.module.AppModule;
import com.example.leejunbeom.bookMarker.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jun on 16. 3. 12..
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}