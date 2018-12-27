package com.avansprojects.antl.ui.login;

import android.util.Log;

import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.ui.login.dto.RegistrationRequestDTO;
import com.avansprojects.antl.ui.login.services.LoginService;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterViewModel extends ViewModel {
    private java.lang.String _userName;
    private String _email;
    private java.lang.String _passWord;

    public void Register() {

        Retrofit retrofit = AntlRetrofit.getRetrofit();

        RegistrationRequestDTO registrationRequestDTO = new RegistrationRequestDTO(_userName, _email, _passWord);
        LoginService service = retrofit.create(LoginService.class);
        Call<String> call = service.register(registrationRequestDTO);
        retrofit2.Response<String> result = null;
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                java.lang.String result = response.toString();
                Log.d(this.getClass().toString(), "Message received: " + result);
            }
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });
    }

    public void setUserName(CharSequence text) {
        this._userName = text.toString();
    }

    public void setPassword(CharSequence text) {
        this._passWord = text.toString();
    }

    public boolean setEmail(CharSequence text) {
        this._email = text.toString();
        return true;
    }
}
