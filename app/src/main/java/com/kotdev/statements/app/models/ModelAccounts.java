package com.kotdev.statements.app.models;

import com.kotdev.statements.room.Database;
import com.kotdev.statements.room.profile.Account;
import com.kotdev.statements.room.profile.AccountView;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Module
public class ModelAccounts {

    private final Database database;
    private Disposable disposable;

    @Inject
    public ModelAccounts(Database database) {
        this.database = database;
    }

    public Flowable<List<Account>> getAccounts() {
        return database.getDatabase()
                .accountDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<AccountView>> getAccountsView() {
        return database.getDatabase()
                .accountDao()
                .getAccountView()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Integer getSizePosts(long id) {
        return database.getDatabase()
                .accountDao()
                .getSizePosts(id);
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
