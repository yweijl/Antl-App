package com.avansprojects.antl.ui.login.dto;

import com.google.gson.annotations.SerializedName;

public class FriendDto {
    @SerializedName("userName")
    private String userName;

    @SerializedName("externalId")
    private String externalId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExternalId() {
        return externalId;
    }
}
