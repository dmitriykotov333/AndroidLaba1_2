package com.kotdev.statements.app.models;

import com.kotdev.statements.room.Database;
import com.kotdev.statements.room.post.PostAccount;
import com.kotdev.statements.room.profile.Account;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Module
public class ModelGlobal {

    private final Database database;

    @Inject
    public ModelGlobal(Database database) {
        this.database = database;
    }

    public Flowable<List<PostAccount>> getPosts(long id) {
        return database.getDatabase()
                .accountDao()
                .getPosts(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<Account> getAccount(long id) {
        return database.getDatabase()
                .accountDao()
                .getAccount(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Integer getSizePosts(long id) {
        return database.getDatabase()
                .accountDao()
                .getSizePosts(id);
    }

    public void dispose() {

    }


}
