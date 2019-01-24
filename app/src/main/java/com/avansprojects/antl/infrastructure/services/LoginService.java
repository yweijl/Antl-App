package com.avansprojects.antl.infrastructure.services;

import com.avansprojects.antl.infrastructure.dtos.LoginRequestDTO;
import com.avansprojects.antl.infrastructure.dtos.RegistrationRequestDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api")
    Call<String> login(@Body LoginRequestDTO request);

    @POST("/api/register")
    Call<String> register(@Body RegistrationRequestDTO request);
}
