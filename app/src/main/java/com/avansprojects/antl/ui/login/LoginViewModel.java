package com.avansprojects.antl.ui.login;

import android.util.Log;

import com.avansprojects.antl.ui.login.dto.AuthorizationCodeDTO;
import com.avansprojects.antl.ui.login.dto.LoginRequestDTO;
import com.avansprojects.antl.ui.login.services.TestService;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginViewModel extends ViewModel {
    private String _userName;
    private String _passWord;

    public void Login() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:64151")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        LoginRequestDTO loginRequest = new LoginRequestDTO(_userName, _passWord);
        TestService service = retrofit.create(TestService.class);
        Call<AuthorizationCodeDTO> call = service.login(loginRequest);
        retrofit2.Response<AuthorizationCodeDTO> result = null;
        call.enqueue(new Callback<AuthorizationCodeDTO>() {
            @Override
            public void onResponse(Call<AuthorizationCodeDTO> call, Response<AuthorizationCodeDTO> response) {
                String result = response.toString();
                Log.d(this.getClass().toString(), "Message received: " + result);
            }
            @Override
            public void onFailure(Call<AuthorizationCodeDTO> call, Throwable throwable) {
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
