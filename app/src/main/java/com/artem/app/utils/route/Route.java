package com.artem.app.utils.route;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.artem.app.ui.base.IBaseActivityView;

public class Route implements IRoute {

    static Route instance;

    private final IBaseActivityView view;

    public Route(IBaseActivityView view) {
        this.view = view;
    }

    public static Route getInstance(IBaseActivityView view) {
        if (instance == null)
            instance = new Route(view);
        return instance;
    }

    @Override
    public <T> void transaction(String tag, T data, Fragment fromFragment) {
        if (data instanceof Fragment) {
            view.transitionFragment((Fragment) data, fromFragment, tag);
        } else if (data instanceof Intent) {
            view.transitionActivity(new Intent());
        }
    }
}
