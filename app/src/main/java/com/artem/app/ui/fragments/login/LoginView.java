package com.artem.app.ui.fragments.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.artem.app.R;
import com.artem.app.databinding.FragmentLoginBinding;
import com.artem.app.ui.activity.MainView;
import com.artem.app.ui.base.BasePresenter;
import com.artem.app.ui.base.FragmentBase;
import com.artem.app.ui.fragments.posts.PostView;
import com.artem.app.ui.fragments.reg.RegView;

import java.util.Objects;

public class LoginView extends FragmentBase<FragmentLoginBinding> implements LoginContract.View{

    private LoginContract.Presenter presenter;

    public static Fragment newInstance() {

        Bundle args = new Bundle();

        Fragment fragment = new LoginView();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initFragmentView() {
        presenter = new LoginPresenter(this);
        getBinding().setEvent(presenter);
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
    public void transactionReg() {
        ((MainView) requireActivity()).route.transaction("REG", RegView.newInstance(), this);
    }

    @Override
    public void transactionPost() {
        ((MainView) requireActivity()).route.transaction("POSTS", PostView.newInstance(), null);
    }

    @Override
    public String getLogin() {
        return getBinding().login.getText().toString();
    }

    @Override
    public String getPassword() {
        return getBinding().password.getText().toString();
    }

    @Override
    public void incorrectPassword() {
        getBinding().password.setError("Wrong");
    }

    @Override
    public void incorrectLogin() {
        getBinding().login.setError("Wrong");
    }
}
