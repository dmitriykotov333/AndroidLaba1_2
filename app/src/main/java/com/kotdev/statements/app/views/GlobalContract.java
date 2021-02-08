package com.kotdev.statements.app.views;

import com.kotdev.statements.MvpPresenter;
import com.kotdev.statements.room.post.PostAccount;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public class GlobalContract {


    public interface ViewContractGlobal {


    }

    public interface Global extends MvpPresenter<ViewContractGlobal> {

        Flowable<List<PostAccount>> getPosts(long id);

    }

}
