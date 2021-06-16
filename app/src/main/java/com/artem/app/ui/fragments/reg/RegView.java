package com.artem.app.ui.fragments.reg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.artem.app.R;
import com.artem.app.databinding.FragmentRegBinding;
import com.artem.app.ui.activity.MainView;
import com.artem.app.ui.base.BasePresenter;
import com.artem.app.ui.base.FragmentBase;

public class RegView extends FragmentBase<FragmentRegBinding> implements RegContract.View {

    private RegContract.Presenter presenter;

    public static Fragment newInstance() {

        Bundle args = new Bundle();

        Fragment fragment = new RegView();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_reg;
    }

    @Override
    protected void initFragmentView() {
        presenter = new RegPresenter(this);
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
    public String getLogin() {
        return getBinding().login.getText().toString();
    }

    @Override
    public String getPassword() {
        return getBinding().password.getText().toString();
    }

    @Override
    public String getPasswordAgain() {
        return getBinding().againPassword.getText().toString();
    }

    @Override
    public void incorrectLogin() {
        getBinding().login.setError("Wrong");
    }

    @Override
    public void incorrectPassword() {
        getBinding().password.setError("Wrong");
        getBinding().againPassword.setError("Wrong");
    }

    @Override
    public void transactionLogin() {
        requireActivity().onBackPressed();
    }
}
