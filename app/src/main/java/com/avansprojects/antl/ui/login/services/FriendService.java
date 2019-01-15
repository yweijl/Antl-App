package com.avansprojects.antl.ui.login.services;

import com.avansprojects.antl.ui.login.dto.FriendDto;
import com.avansprojects.antl.ui.login.dto.FriendRequestDto;
import com.avansprojects.antl.ui.login.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FriendService {
    @GET("/api/Relationship/user/{code}")
    Call<List<FriendDto>> getFriends(@Header("authorization") String token, @Path("code") String code);

    @POST("/api/Relationship")
    Call<FriendRequestDto> addFriend(@Header("authorization") String token, @Body FriendRequestDto dto);
}