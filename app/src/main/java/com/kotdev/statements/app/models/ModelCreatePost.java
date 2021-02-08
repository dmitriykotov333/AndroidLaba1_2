package com.kotdev.statements.app.models;

import com.kotdev.statements.room.Database;
import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.profile.Account;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Module
public class ModelCreatePost {

    private final Database database;
    private Disposable disposable;

    @Inject
    public ModelCreatePost(Database database) {
        this.database = database;
    }

    public Flowable<List<Account>> getAccounts() {
        return database.getDatabase()
                .accountDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Long>> getIdAccounts() {
        return database.getDatabase()
                .postDao()
                .getIdAccounts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void insert(Post post) {
        disposable = database.getDatabase()
                .postDao()
                .insert(post)
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
