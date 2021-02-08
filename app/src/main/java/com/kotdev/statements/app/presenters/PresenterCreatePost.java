package com.kotdev.statements.app.presenters;

import com.kotdev.statements.PresenterBase;
import com.kotdev.statements.app.models.ModelCreatePost;
import com.kotdev.statements.app.views.ContractPosts;
import com.kotdev.statements.di.main.MainScope;
import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.profile.Account;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

@MainScope
public class PresenterCreatePost extends PresenterBase<ContractPosts.ViewContractPosts> implements ContractPosts.CreatePosts {

    private final ModelCreatePost model;

    @Inject
    public PresenterCreatePost(ModelCreatePost model)
    {
        this.model = model;
    }


    @Override
    public Flowable<List<Account>> getAccounts() {
        return model.getAccounts();
    }

    @Override
    public Flowable<List<Long>> getIdAccounts() {
        return model.getIdAccounts();
    }

    @Override
    public void addPosts(Post posts) {
        model.insert(posts);
    }

    @Override
    public void onDestroy() {
        model.dispose();
    }
}
