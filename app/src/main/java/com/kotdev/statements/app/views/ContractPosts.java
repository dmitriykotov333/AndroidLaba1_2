package com.kotdev.statements.app.views;

import com.kotdev.statements.MvpPresenter;
import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.profile.Account;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public class ContractPosts {



    public interface ViewContractPosts  {


    }

    public interface Posts extends MvpPresenter<ViewContractPosts> {

        Flowable<List<Post>> getPosts();

        void deletePosts(Post posts);

    }

    public interface CreatePosts extends MvpPresenter<ViewContractPosts> {

        Flowable<List<Account>> getAccounts();

        Flowable<List<Long>> getIdAccounts();

        void addPosts(Post posts);

        void onDestroy();
    }

}
