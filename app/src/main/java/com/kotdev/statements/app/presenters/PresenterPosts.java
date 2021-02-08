package com.kotdev.statements.app.presenters;

import com.kotdev.statements.PresenterBase;
import com.kotdev.statements.app.models.ModelPosts;
import com.kotdev.statements.app.views.ContractPosts;
import com.kotdev.statements.di.main.MainScope;
import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.post.PostAccount;
import com.kotdev.statements.ui.adapters.Adapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;


@MainScope
public class PresenterPosts extends PresenterBase<ContractPosts.ViewContractPosts> implements ContractPosts.Posts {

    private final ModelPosts model;
    private Adapter adapter;

    @Inject
    public PresenterPosts(ModelPosts model)
    {
        this.model = model;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public Flowable<List<PostAccount>> getPostAccount() {
        return model.getPostAccount();
    }

    @Override
    public Flowable<List<Post>> getPosts() {
        return model.getPosts();
    }

    @Override
    public void deletePosts(Post post) {
        model.deletePost(post);
    }

}
