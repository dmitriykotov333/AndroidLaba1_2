package com.kotdev.statements.app.presenters;

import com.kotdev.statements.PresenterBase;
import com.kotdev.statements.app.models.ModelGlobal;
import com.kotdev.statements.app.views.GlobalContract;
import com.kotdev.statements.di.main.MainScope;
import com.kotdev.statements.room.post.PostAccount;
import com.kotdev.statements.room.profile.Account;
import com.kotdev.statements.ui.adapters.AdapterGlobal;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

@MainScope
public class PresenterGlobal extends PresenterBase<GlobalContract.ViewContractGlobal> implements GlobalContract.Global {

    private final ModelGlobal model;
    private AdapterGlobal adapter;

    @Inject
    public PresenterGlobal(ModelGlobal model)
    {
        this.model = model;
    }

    public void setAdapter(AdapterGlobal adapter) {
        this.adapter = adapter;
    }

    public AdapterGlobal getAdapter() {
        return adapter;
    }

    public Flowable<Account> getAccount(long id) {
        return model.getAccount(id);
    }

    public Integer getSize(long id) {
        return model.getSizePosts(id);
    }
    @Override
    public Flowable<List<PostAccount>> getPosts(long id) {
        return model.getPosts(id);
    }
}
