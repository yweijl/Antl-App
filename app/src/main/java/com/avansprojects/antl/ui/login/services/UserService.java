package com.avansprojects.antl.ui.login.services;

import com.avansprojects.antl.infrastructure.daos.UserDao;
import com.avansprojects.antl.ui.login.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("/api/user/{id}")
    Call<UserDto> getUser(@Header("authorization") String token, @Path("id") int userId);
}