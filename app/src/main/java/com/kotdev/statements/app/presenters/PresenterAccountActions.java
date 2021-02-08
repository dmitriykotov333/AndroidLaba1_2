package com.kotdev.statements.app.presenters;

import com.kotdev.statements.PresenterBase;
import com.kotdev.statements.app.models.ModelAccountAction;
import com.kotdev.statements.app.views.ContractAccounts;
import com.kotdev.statements.di.main.MainScope;
import com.kotdev.statements.room.profile.Account;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

@MainScope
public class PresenterAccountActions extends PresenterBase<ContractAccounts.ViewContractAccounts> implements ContractAccounts.CreateAccounts {

    private final ModelAccountAction model;

    @Inject
    public PresenterAccountActions(ModelAccountAction model)
    {
        this.model = model;
    }



    @Override
    public void addAccount(Account account) {
        model.insert(account);
    }

    @Override
    public Flowable<Account> getAccount(long id) {
        return model.getAccount(id);
    }

    @Override
    public void updateAccount(Account account) {
        model.update(account);
    }

    @Override
    public void onDestroy() {
        model.dispose();
    }
}
