package com.avansprojects.antl.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

public class FriendRequestDto {
    @SerializedName("friendId")
    private String friendId;

    public FriendRequestDto(String friendId) {
        this.friendId = friendId;
    }
}
