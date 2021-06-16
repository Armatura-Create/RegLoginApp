package com.artem.app.ui.fragments.posts;

import com.artem.app.adapters.PostAdapter;
import com.artem.app.ui.base.BasePresenter;

public interface PostContract {

    interface View {
        void toast(String message);

        void transactionAdd();

        void logout();
    }

    interface Presenter extends BasePresenter {
        PostAdapter getAdapter();
        
        void addPost();
        void logout();
    }
}
