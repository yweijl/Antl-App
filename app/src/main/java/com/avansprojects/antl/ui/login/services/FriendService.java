package com.avansprojects.antl.ui.login.services;

import com.avansprojects.antl.ui.login.dto.FriendDto;
import com.avansprojects.antl.ui.login.dto.FriendRequestDto;
import com.avansprojects.antl.ui.login.dto.UserDto;

import java.util.List;

import androidx.room.Delete;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FriendService {
    @GET("/api/Friendship/get")
    Call<List<FriendDto>> getFriends(@Header("authorization") String token);

    @POST("/api/Friendship")
    Call<String> addFriend(@Header("authorization") String token, @Body FriendRequestDto dto);

    @DELETE("/api/Friendship/delete")
    Call<String> deleteFriend(@Header("authorization") String token, @Body FriendDto dto);
}