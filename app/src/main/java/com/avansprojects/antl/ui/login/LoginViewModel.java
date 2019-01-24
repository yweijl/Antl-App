package com.avansprojects.antl.ui.login;

import android.view.View;
import android.widget.ProgressBar;

import com.avansprojects.antl.helpers.Authentication;
import com.avansprojects.antl.infrastructure.dtos.LoginRequestDTO;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private String _userName;
    private String _passWord;

    public void Login(View view, ProgressBar progressBar) {

        LoginRequestDTO loginRequest = new LoginRequestDTO(_userName, _passWord);
        Authentication.login(view, loginRequest, progressBar);
    }

    public void setUser(CharSequence text) {
        _userName = text.toString();
    }

    public void setPassword(CharSequence text) {
        _passWord = text.toString();
    }
}
