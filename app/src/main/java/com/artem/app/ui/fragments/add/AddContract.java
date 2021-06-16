package com.artem.app.ui.fragments.add;

import com.artem.app.ui.base.BasePresenter;

public interface AddContract {

    interface View {

        String getTitle();

        String getDescription();

        String getCost();

        void toast(String message);

        void incorrectTitle();

        void incorrectDescription();

        void incorrectCost();

        void transactionPosts();
    }

    interface Presenter extends BasePresenter {
        void sendPost();
    }
}
