package com.avansprojects.antl.ui.login.services;

import com.avansprojects.antl.ui.login.LoginRequest;
import com.avansprojects.antl.ui.login.dto.AuthorizationCodeDTO;
import com.avansprojects.antl.ui.login.dto.LoginRequestDTO;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TestService {
    @POST("/api")
    Call<AuthorizationCodeDTO> login(@Body LoginRequestDTO request);
}
