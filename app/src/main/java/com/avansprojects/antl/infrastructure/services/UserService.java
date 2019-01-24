package com.avansprojects.antl.infrastructure.services;

import com.avansprojects.antl.infrastructure.dtos.UserDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserService {
    @GET("/api/User/{id}")
    Call<UserDto> getUser(@Header("authorization") String token, @Path("id") String userId);
}