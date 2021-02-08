package com.kotdev.statements.di;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * This method return AppComponent
     * @return
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
