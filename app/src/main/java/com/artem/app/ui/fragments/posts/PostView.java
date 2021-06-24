package com.artem.app.ui.fragments.posts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.artem.app.R;
import com.artem.app.databinding.FragmentLoginBinding;
import com.artem.app.databinding.FragmentPostsBinding;
import com.artem.app.databinding.FragmentPostsBindingImpl;
import com.artem.app.ui.activity.MainView;
import com.artem.app.ui.base.BasePresenter;
import com.artem.app.ui.base.FragmentBase;
import com.artem.app.ui.fragments.add.AddView;
import com.artem.app.ui.fragments.login.LoginView;
import com.artem.app.ui.fragments.reg.RegView;
import com.artem.app.utils.Prefs;

public class PostView extends FragmentBase<FragmentPostsBinding> implements PostContract.View {

    private PostContract.Presenter presenter;

    public static Fragment newInstance() {

        Bundle args = new Bundle();

        Fragment fragment = new PostView();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_posts;
    }

    @Override
    protected void initFragmentView() {
        presenter = new PostPresenter(this);
        getBinding().setEvent(presenter);
        getBinding().rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().rvPosts.setAdapter(presenter.getAdapter());

        getBinding().swipe.setOnRefreshListener(presenter);
    }

    @Override
    protected void stopFragment() {

    }

    @Override
    protected void destroyFragment() {

    }

    @Override
    protected void pauseFragment() {

    }

    @Override
    protected void hiddenChangeFragment(Boolean hidden) {

    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void transactionAdd() {
        ((MainView) requireActivity()).route.transaction("ADD", AddView.newInstance(), this);
    }

    @Override
    public void logout() {
        Prefs.getInstance(getContext()).setString("USER", null);
        ((MainView) requireActivity()).route.transaction("LOGIN", LoginView.newInstance(), null);
    }

    @Override
    public void setRefresh(boolean refresh) {
        getBinding().swipe.setRefreshing(refresh);
    }
}
