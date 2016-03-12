package com.example.leejunbeom.bookMarker.dagger.module;

import com.example.leejunbeom.bookMarker.model.SIFT;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jun on 16. 3. 12..
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    SIFT provideTest(){
        return new SIFT();
    }
}
