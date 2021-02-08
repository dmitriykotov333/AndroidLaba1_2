package com.kotdev.statements.app.presenters;

import com.kotdev.statements.PresenterBase;
import com.kotdev.statements.app.models.ModelAccounts;
import com.kotdev.statements.app.views.ContractAccounts;
import com.kotdev.statements.di.main.MainScope;
import com.kotdev.statements.room.profile.Account;
import com.kotdev.statements.room.profile.AccountView;
import com.kotdev.statements.ui.adapters.AdapterAccounts;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

@MainScope
public class PresenterAccounts extends PresenterBase<ContractAccounts.ViewContractAccounts> implements ContractAccounts.Accounts {

    private final ModelAccounts model;
    private AdapterAccounts adapter;

    @Inject
    public PresenterAccounts(ModelAccounts model)
    {
        this.model = model;
    }



    public void setAdapter(AdapterAccounts adapter) {
        this.adapter = adapter;
    }

    public AdapterAccounts getAdapter() {
        return adapter;
    }


    public Flowable<List<AccountView>> getAccountsView() {
        return model.getAccountsView();
    }

    @Override
    public Flowable<List<Account>> getAccounts() {
        return model.getAccounts();
    }

    public Integer getSize(long id) {
        return model.getSizePosts(id);
    }

    @Override
    public void deleteAccount(Account account) {
        model.deleteAccount(account);
    }

}
