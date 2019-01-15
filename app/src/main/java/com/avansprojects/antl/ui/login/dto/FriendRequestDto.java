package com.avansprojects.antl.ui.login.dto;

import com.google.gson.annotations.SerializedName;

public class FriendRequestDto {
    @SerializedName("userIdOne")
    private String myFriendCode;

    @SerializedName("userIdTwo")
    private String friendsCode;

    public FriendRequestDto(String myFriendCode, String friendsCode) {
        this.myFriendCode = myFriendCode;
        this.friendsCode = friendsCode;
    }
}
