package com.artem.app.ui.fragments.posts;

import androidx.annotation.NonNull;

import com.artem.app.adapters.PostAdapter;
import com.artem.app.api.ApiApp;
import com.artem.app.api.RetrofitFactory;
import com.artem.app.api.models.PostsModel;

import java.util.Collections;

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
        onRefresh();
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

    @Override
    public void onRefresh() {
        _view.setRefresh(true);
        api.getPost().enqueue(new Callback<PostsModel>() {
            @Override
            public void onResponse(@NonNull Call<PostsModel> call, @NonNull Response<PostsModel> response) {
                if (response.body() != null) {
//                    Log.e("TAG_RESPONSE", new Gson().toJson(response.body()) );
                    Collections.reverse(response.body().getTours());
                    adapter.setPosts(response.body().getTours());
                }
                else
                    _view.toast("Error load");
                _view.setRefresh(false);
            }

            @Override
            public void onFailure(@NonNull Call<PostsModel> call, @NonNull Throwable t) {
                _view.toast(t.getMessage());
                _view.setRefresh(false);
            }
        });
    }
}
