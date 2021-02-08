package com.kotdev.statements.di.main;

import com.kotdev.statements.ui.fragments.GlobalAccountFragment;
import com.kotdev.statements.ui.fragments.posts.FirstFragment;
import com.kotdev.statements.ui.fragments.posts.SecondFragment;
import com.kotdev.statements.ui.fragments.profile.AccountFragment;
import com.kotdev.statements.ui.fragments.profile.CreateAccountFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract AccountFragment contributeAccountFragment();

    @ContributesAndroidInjector
    abstract CreateAccountFragment contributeCreateAccountFragment();

    @ContributesAndroidInjector
    abstract FirstFragment contributeFirstFragment();

    @ContributesAndroidInjector
    abstract SecondFragment contributeSecondFragment();

    @ContributesAndroidInjector
    abstract GlobalAccountFragment contributeGlobalAccountFragment();

}
