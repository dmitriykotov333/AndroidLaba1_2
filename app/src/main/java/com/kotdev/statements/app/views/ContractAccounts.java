package com.kotdev.statements.app.views;

import com.kotdev.statements.MvpPresenter;
import com.kotdev.statements.room.profile.Account;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public class ContractAccounts {

    public interface ViewContractAccounts  {


    }

    public interface Accounts extends MvpPresenter<ViewContractAccounts> {

        Flowable<List<Account>> getAccounts();

        void deleteAccount(Account account);

    }

    public interface CreateAccounts extends MvpPresenter<ViewContractAccounts> {

        void addAccount(Account account);

        Flowable<Account> getAccount(long id);

        void updateAccount(Account account);

        void onDestroy();
    }

}
