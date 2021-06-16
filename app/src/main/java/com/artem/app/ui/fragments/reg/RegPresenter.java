package com.artem.app.ui.fragments.reg;

import androidx.annotation.NonNull;

import com.artem.app.api.ApiApp;
import com.artem.app.api.RetrofitFactory;
import com.artem.app.api.models.RegLoginModel;
import com.artem.app.api.models.StatusModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegPresenter implements RegContract.Presenter {

    private RegContract.View _view;
    private ApiApp api;

    public RegPresenter(RegContract.View _view) {
        this._view = _view;
        api = RetrofitFactory.getInstance().create("http://45.156.22.39:5000");
    }

    @Override
    public void detachView() {

    }

    @Override
    public void sendReg() {
        if (_view.getLogin().isEmpty()) {
            _view.incorrectLogin();
            return;
        }

        if (_view.getPassword().isEmpty() || (!_view.getPassword().equals(_view.getPasswordAgain()))) {
            _view.incorrectPassword();
            return;
        }

        RegLoginModel regLoginModel = new RegLoginModel();
        regLoginModel.setLogin(_view.getLogin());
        regLoginModel.setPassword(_view.getPassword());

        api.reg(regLoginModel).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(@NonNull Call<StatusModel> call, @NonNull Response<StatusModel> response) {
                if (response.body().getStatus().equalsIgnoreCase("success"))
                    _view.transactionLogin();
                else
                    _view.toast(response.body().getStatus());
            }

            @Override
            public void onFailure(@NonNull Call<StatusModel> call, @NonNull Throwable t) {
                _view.toast(t.getMessage());
            }
        });

    }
}
