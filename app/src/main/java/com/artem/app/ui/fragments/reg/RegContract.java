package com.artem.app.ui.fragments.reg;

import com.artem.app.ui.base.BasePresenter;

public interface RegContract {

    interface View {
        void toast(String message);

        String getLogin();
        String getPassword();
        String getPasswordAgain();

        void incorrectLogin();
        void incorrectPassword();

        void transactionLogin();
    }

    interface Presenter extends BasePresenter {
        void sendReg();
    }

}
