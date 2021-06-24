package com.artem.app.api;

import com.artem.app.api.models.PostModel;
import com.artem.app.api.models.PostsModel;
import com.artem.app.api.models.RegLoginModel;
import com.artem.app.api.models.StatusModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiApp {
    @POST("/get/acc")
    Call<StatusModel> login(@Body RegLoginModel model);

    @POST("/add/acc")
    Call<StatusModel> reg(@Body RegLoginModel model);

    @POST("/add/post")
    Call<StatusModel> sendPost(@Body PostModel model);

    @GET("/get/tours")
    Call<PostsModel> getPost();
}
