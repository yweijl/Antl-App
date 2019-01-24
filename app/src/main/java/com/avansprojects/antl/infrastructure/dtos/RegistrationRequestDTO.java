package com.avansprojects.antl.infrastructure.dtos;

import android.provider.ContactsContract.CommonDataKinds.Email;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequestDTO {
    @SerializedName("userName")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;


    public RegistrationRequestDTO(String username, String email, String password) {
        userName = username;
        this.email = email;
        this.password = password;
    }
}
