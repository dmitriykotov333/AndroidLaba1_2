package com.kotdev.statements.di.main;

import com.kotdev.statements.app.presenters.PresenterAccountActions;
import com.kotdev.statements.app.presenters.PresenterAccounts;
import com.kotdev.statements.app.presenters.PresenterCreatePost;
import com.kotdev.statements.app.presenters.PresenterGlobal;
import com.kotdev.statements.app.presenters.PresenterPosts;
import com.kotdev.statements.app.views.ContractAccounts;
import com.kotdev.statements.app.views.ContractPosts;
import com.kotdev.statements.app.views.GlobalContract;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class MainBindsModule {

    @MainScope
    @Binds
    public abstract ContractAccounts.Accounts bindPresenterPresenterPosts(PresenterAccounts presenterPosts);

    @MainScope
    @Binds
    public abstract ContractAccounts.CreateAccounts bindPresenterAccountActions(PresenterAccountActions presenterAccountActions);

    @MainScope
    @Binds
    public abstract ContractPosts.CreatePosts bindPresenterCreatePost(PresenterCreatePost presenterCreatePost);

    @MainScope
    @Binds
    public abstract ContractPosts.Posts bindPresenterPosts(PresenterPosts presenterPosts);

    @MainScope
    @Binds
    public abstract GlobalContract.Global bindPresenterGlobal(PresenterGlobal presenterGlobal);

}
