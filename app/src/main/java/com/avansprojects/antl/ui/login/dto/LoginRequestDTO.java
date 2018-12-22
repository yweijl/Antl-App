package com.avansprojects.antl.ui.login.dto;

import com.google.gson.annotations.SerializedName;

public class LoginRequestDTO {
    @SerializedName("userName")
    private String userName;

    @SerializedName("password")
    private String password;


    public LoginRequestDTO(String username, String password) {
        userName = username;
        this.password = password;
    }
}
