package com.artem.app.ui.fragments.posts;

import android.util.Log;

import androidx.annotation.NonNull;

import com.artem.app.adapters.PostAdapter;
import com.artem.app.api.ApiApp;
import com.artem.app.api.RetrofitFactory;
import com.artem.app.api.models.PostModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter implements PostContract.Presenter {

    private PostContract.View _view;
    private ApiApp api;
    private PostAdapter adapter;

    public PostPresenter(PostContract.View _view) {
        this._view = _view;
        api = RetrofitFactory.getInstance().create("http://45.156.22.39:5000");
        adapter = new PostAdapter();

        api.getPost().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
//                if (response.body() != null)
                    Log.e("TAG_RESPONSE", new Gson().toJson(response.body()) );
//                    adapter.setPosts(response.body());
//                else
//                    _view.toast("Error load");
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                _view.toast(t.getMessage());
            }
        });
    }

    @Override
    public void detachView() {

    }

    @Override
    public PostAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void addPost() {
        _view.transactionAdd();
    }

    @Override
    public void logout() {
        _view.logout();
    }
}
