package com.kotdev.statements.app.models;

import com.kotdev.statements.room.Database;
import com.kotdev.statements.room.profile.Account;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Module
public class ModelAccountAction {

    private final Database database;
    private Disposable disposable;

    @Inject
    public ModelAccountAction(Database database) {
        this.database = database;
    }



    public void update(Account account) {
        disposable = database.getDatabase()
                .accountDao()
                .update(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }


    public void insert(Account account) {
        disposable = database.getDatabase()
                .accountDao()
                .insert(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public Flowable<Account> getAccount(long id) {
        return database.getDatabase()
                .accountDao()
                .getAccount(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void deleteAccount(Account account) {
        disposable = database.getDatabase()
                .accountDao()
                .delete(account)
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
