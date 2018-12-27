package com.avansprojects.antl.ui.login.services;

import com.avansprojects.antl.ui.login.dto.LoginRequestDTO;
import com.avansprojects.antl.ui.login.dto.RegistrationRequestDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api")
    Call<String> login(@Body LoginRequestDTO request);

    @POST("/api/register")
    Call<String> register(@Body RegistrationRequestDTO request);
}
