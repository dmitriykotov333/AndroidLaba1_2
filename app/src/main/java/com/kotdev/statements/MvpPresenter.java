package com.kotdev.statements;

public interface MvpPresenter<V> {

    void attachView(V mvpView);

    void detachView();

}