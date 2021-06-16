package com.artem.app.ui.base;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public interface IBaseActivityView {
        void transitionFragment(Fragment toFragment, Fragment fromFragment, String tag);
        void transitionActivity(Intent intent);
}
