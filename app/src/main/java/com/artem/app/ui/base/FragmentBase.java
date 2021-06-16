package com.artem.app.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.artem.app.ui.activity.MainView;

public abstract class FragmentBase<Binding extends ViewDataBinding> extends Fragment {
    private Binding  binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        initFragmentView();
        return binding.getRoot();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        hiddenChangeFragment(hidden);
    }

    @Override
    public void onStop() {
        stopFragment();
        super.onStop();
    }

    @Override
    public void onPause() {
        pauseFragment();
        super.onPause();
    }


    protected Binding getBinding() {
        return binding;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initFragmentView();

    protected abstract void stopFragment();

    protected abstract void destroyFragment();

    protected abstract void pauseFragment();

    protected abstract void hiddenChangeFragment(Boolean hidden);

    protected abstract BasePresenter getPresenter();

    @Override
    public void onDetach() {
        getPresenter().detachView();
        destroyFragment();
        super.onDetach();
    }

    protected MainView getMainActivity()  {
        return (MainView) getActivity();
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(requireActivity().getWindow().getDecorView().getWindowToken(), 0);
    }

    protected void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(requireActivity().getWindow().getDecorView(), InputMethodManager.SHOW_IMPLICIT);
    }

    public void toast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
