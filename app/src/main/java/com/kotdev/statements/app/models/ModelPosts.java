package com.kotdev.statements.app.models;

import com.kotdev.statements.room.Database;
import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.post.PostAccount;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Module
public class ModelPosts {

    private final Database database;
    private Disposable disposable;

    @Inject
    public ModelPosts(Database database) {
        this.database = database;
    }


    public Flowable<List<Post>> getPosts() {
        return database.getDatabase()
                .postDao()
                .getAll()
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<PostAccount>> getPostAccount() {
        return database.getDatabase()
                .postDao()
                .getPostWithAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void deletePost(Post post) {
        disposable = database.getDatabase()
                .postDao()
                .delete(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void dispose() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

}
