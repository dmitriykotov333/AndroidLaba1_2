package com.kotdev.statements.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Context provideContext(Application application){
        return application.getApplicationContext();
    }

}
