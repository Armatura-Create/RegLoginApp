package com.artem.app.ui.activity;


import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.artem.app.R;
import com.artem.app.databinding.ActivityMainBinding;
import com.artem.app.ui.base.ActivityBase;
import com.artem.app.ui.base.BasePresenter;
import com.artem.app.ui.fragments.login.LoginView;
import com.artem.app.ui.fragments.posts.PostView;
import com.artem.app.ui.fragments.reg.RegView;
import com.artem.app.utils.Prefs;
import com.artem.app.utils.route.IRoute;
import com.artem.app.utils.route.Route;

public class MainView extends ActivityBase<ActivityMainBinding> implements MainContract.View {

    private MainContract.Presenter presenter;
    public IRoute route;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        route = Route.getInstance(this);
        presenter = new MainPresenter(this);
    }

    @Override
    protected void onStartView() {
        if (Prefs.getInstance(this).getString("USER") == null)
            route.transaction("LOGIN", LoginView.newInstance(), null);
        else
            route.transaction("POSTS", PostView.newInstance(), null);
    }

    @Override
    protected void onDestroyView() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void transitionFragment(Fragment toFragment, Fragment fromFragment, String tag) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (tag != null && (tag.equals("POSTS") || tag.equals("LOGIN")))
            beginTransaction.replace(getBinding().container.getId(), toFragment, tag)
                    .commitAllowingStateLoss();
        else if (fromFragment != null)
            beginTransaction.add(getBinding().container.getId(), toFragment, toFragment.getClass().getName()).addToBackStack(tag).hide(fromFragment)
                    .commitAllowingStateLoss();
        else
            beginTransaction.add(getBinding().container.getId(), toFragment, toFragment.getClass().getName()).addToBackStack(tag)
                    .commitAllowingStateLoss();
    }

    @Override
    public void transitionActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}