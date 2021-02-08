package com.kotdev.statements.di;

import com.kotdev.statements.di.main.MainBindsModule;
import com.kotdev.statements.di.main.MainFragmentBuildersModule;
import com.kotdev.statements.di.main.MainModule;
import com.kotdev.statements.di.main.MainScope;
import com.kotdev.statements.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {


    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class,
                    MainBindsModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();

}
