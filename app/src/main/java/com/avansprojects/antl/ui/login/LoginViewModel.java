package com.avansprojects.antl.ui.login;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.ui.login.dto.LoginRequestDTO;
import com.avansprojects.antl.ui.login.services.LoginService;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginViewModel extends ViewModel {
    private String _userName;
    private String _passWord;

    public void Login() {

        Retrofit retrofit = AntlRetrofit.getRetrofit();



        LoginRequestDTO loginRequest = new LoginRequestDTO(_userName, _passWord);
        LoginService service = retrofit.create(LoginService.class);
        Call<String> call = service.login(loginRequest);
        retrofit2.Response<String> result = null;
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.toString();
                result = response.body();

                SharedPreferences.Editor edit;
                edit= AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).edit();
                edit.putString("token",result);
                Log.i("Login",result);
                edit.commit();
                Log.i("SharedToken", AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).getString("token", "No token"));

                Log.d(this.getClass().toString(), "Message received: " + result);
            }
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });
    }

    public void setUser(CharSequence text) {
        _userName = text.toString();
    }

    public void setPassword(CharSequence text) {
        _passWord = text.toString();
    }
}
