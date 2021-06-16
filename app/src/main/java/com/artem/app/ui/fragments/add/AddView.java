package com.artem.app.ui.fragments.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.artem.app.R;
import com.artem.app.databinding.FragmentAddBinding;
import com.artem.app.ui.activity.MainView;
import com.artem.app.ui.base.BasePresenter;
import com.artem.app.ui.base.FragmentBase;
import com.artem.app.ui.fragments.reg.RegView;

public class AddView extends FragmentBase<FragmentAddBinding> implements AddContract.View {

    private AddContract.Presenter presenter;

    public static Fragment newInstance() {
        Bundle args = new Bundle();

        Fragment fragment = new AddView();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add;
    }

    @Override
    protected void initFragmentView() {
        presenter = new AddPresenter(this);
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
    public String getTitle() {
        return getBinding().title.getText().toString();
    }

    @Override
    public String getDescription() {
        return getBinding().description.getText().toString();
    }

    @Override
    public String getCost() {
        return getBinding().cost.getText().toString();
    }

    @Override
    public void incorrectTitle() {
        getBinding().title.setError("Wrong");
    }

    @Override
    public void incorrectDescription() {
        getBinding().description.setError("Wrong");
    }

    @Override
    public void incorrectCost() {
        getBinding().cost.setError("Wrong");
    }

    @Override
    public void transactionPosts() {
        requireActivity().onBackPressed();
    }
}
