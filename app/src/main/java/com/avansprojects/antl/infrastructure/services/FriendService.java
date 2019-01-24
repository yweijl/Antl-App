package com.avansprojects.antl.infrastructure.services;

import com.avansprojects.antl.infrastructure.dtos.FriendDto;
import com.avansprojects.antl.infrastructure.dtos.FriendRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FriendService {
    @GET("/api/Friendship/get")
    Call<List<FriendDto>> getFriends(@Header("authorization") String token);

    @POST("/api/Friendship")
    Call<String> addFriend(@Header("authorization") String token, @Body FriendRequestDto dto);

    @HTTP(method = "DELETE", path = "/api/Friendship/delete", hasBody = true)
    Call<String> deleteFriend(@Header("authorization") String token, @Body FriendDto dto);
}