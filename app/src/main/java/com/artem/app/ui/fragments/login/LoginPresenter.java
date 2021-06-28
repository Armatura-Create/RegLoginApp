package com.artem.app.ui.fragments.login;

import androidx.annotation.NonNull;

import com.artem.app.api.ApiApp;
import com.artem.app.api.RetrofitFactory;
import com.artem.app.api.models.RegLoginModel;
import com.artem.app.api.models.StatusModel;
import com.artem.app.utils.Prefs;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View _view;
    private ApiApp api;

    public LoginPresenter(LoginContract.View _view) {
        this._view = _view;
        api = RetrofitFactory.getInstance().create("http://45.156.22.39:5000");
    }

    @Override
    public void detachView() {

    }

    @Override
    public void sendLogin() {
        if (_view.getLogin().isEmpty()) {
            _view.incorrectLogin();
            return;
        }

        if (_view.getPassword().isEmpty()) {
            _view.incorrectPassword();
            return;
        }

        RegLoginModel regLoginModel = new RegLoginModel();
        regLoginModel.setLogin(_view.getLogin());
        regLoginModel.setPassword(_view.getPassword());

        api.login(regLoginModel).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(@NonNull Call<StatusModel> call, @NonNull Response<StatusModel> response) {
                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    regLoginModel.setPermission(response.body().getPermission());
                    Prefs.getInstance(_view.getContext()).setString("USER", new Gson().toJson(regLoginModel));
                    _view.transactionPost();
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatusModel> call, @NonNull Throwable t) {
                _view.toast(t.getMessage());
            }
        });
    }

    @Override
    public void toRegistration() {
        _view.transactionReg();
    }
}
