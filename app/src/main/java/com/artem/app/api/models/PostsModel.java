package com.artem.app.api.models;

import java.util.List;

public class PostsModel {

    private List<PostModel> tours;

    public List<PostModel> getTours() {
        return tours;
    }

    public void setTours(List<PostModel> tours) {
        this.tours = tours;
    }
}
