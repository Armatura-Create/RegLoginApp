package com.artem.app.ui.fragments.add;

import androidx.annotation.NonNull;

import com.artem.app.api.ApiApp;
import com.artem.app.api.RetrofitFactory;
import com.artem.app.api.models.PostModel;
import com.artem.app.api.models.StatusModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPresenter implements AddContract.Presenter {

    private AddContract.View _view;
    private ApiApp api;

    public AddPresenter(AddContract.View _view) {
        this._view = _view;
        api = RetrofitFactory.getInstance().create("http://45.156.22.39:5000");
    }

    @Override
    public void detachView() {

    }

    @Override
    public void sendPost() {
        if (_view.getTitle().isEmpty()) {
            _view.incorrectTitle();
            return;
        }
        if (_view.getDescription().isEmpty()) {
            _view.incorrectDescription();
            return;
        }

        if (_view.getCost().isEmpty()){
            _view.incorrectCost();
            return;
        }

        PostModel model = new PostModel();

        model.setTitle(_view.getTitle());
        model.setDescription(_view.getTitle());
        model.setCost(_view.getTitle());

        api.sendPost(model).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(@NonNull Call<StatusModel> call, @NonNull Response<StatusModel> response) {
                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    _view.transactionPosts();
                } else
                    _view.toast(response.body().getStatus());
            }

            @Override
            public void onFailure(@NonNull Call<StatusModel> call, @NonNull Throwable t) {
                _view.toast(t.getMessage());
            }
        });
    }
}
