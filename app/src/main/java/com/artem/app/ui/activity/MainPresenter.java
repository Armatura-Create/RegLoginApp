package com.artem.app.ui.activity;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View _view;

    protected MainPresenter(MainContract.View _view){
        this._view = _view;
    }


    @Override
    public void detachView() {

    }
}
