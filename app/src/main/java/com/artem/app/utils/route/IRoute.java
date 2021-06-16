package com.artem.app.utils.route;

import androidx.fragment.app.Fragment;

public interface IRoute {
    <T> void transaction(String tag, T data, Fragment fromFragment);
}