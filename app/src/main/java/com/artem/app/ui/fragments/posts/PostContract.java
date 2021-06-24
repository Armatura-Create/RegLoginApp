package com.artem.app.ui.fragments.posts;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.artem.app.adapters.PostAdapter;
import com.artem.app.ui.base.BasePresenter;

public interface PostContract {

    interface View {
        void toast(String message);

        void transactionAdd();

        void logout();

        void setRefresh(boolean refresh);
    }

    interface Presenter extends BasePresenter, SwipeRefreshLayout.OnRefreshListener {
        PostAdapter getAdapter();
        
        void addPost();
        void logout();

        @Override
        void onRefresh();

        @Override
        void detachView();
    }
}
