package com.example.qubin.myapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by qubin on 5/4/17.
 */
public interface GitHubClient {
    String ENDPOINT = "https://api.github.com";
    @GET("/users")
    Call<ArrayList<User>> userList();
}
