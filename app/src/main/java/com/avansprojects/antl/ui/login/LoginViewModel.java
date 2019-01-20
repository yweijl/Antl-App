package com.avansprojects.antl.ui.login;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.Authentication;
import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.ui.login.dto.LoginRequestDTO;
import com.avansprojects.antl.ui.login.services.LoginService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
