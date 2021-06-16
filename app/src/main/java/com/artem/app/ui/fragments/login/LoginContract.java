package com.artem.app.ui.fragments.login;

import android.content.Context;

import com.artem.app.ui.base.BasePresenter;

public interface LoginContract {

    interface View {
        void toast(String message);

        void transactionReg();

        void transactionPost();

        String getLogin();

        String getPassword();

        void incorrectPassword();

        void incorrectLogin();

        Context getContext();
    }

    interface Presenter extends BasePresenter {
        void sendLogin();

        void toRegistration();
    }

}
