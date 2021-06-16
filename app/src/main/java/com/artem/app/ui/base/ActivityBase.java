package com.artem.app.ui.base;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;

public abstract class ActivityBase<Binding extends ViewDataBinding> extends AppCompatActivity {

    private Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, getLayoutRes());

        setContentView(binding.getRoot());
        initView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager manager = this.getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            super.finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        onStartView();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView();

    @Override
    public void onDestroy() {
        getPresenter().detachView();
        onDestroyView();
        super.onDestroy();
    }

    protected abstract void onStartView();

    protected abstract void onDestroyView();

    protected abstract BasePresenter getPresenter();

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected Binding getBinding() {
        return binding;
    }
}
