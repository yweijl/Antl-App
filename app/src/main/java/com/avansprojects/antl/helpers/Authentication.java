package com.avansprojects.antl.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.auth0.android.jwt.JWT;
import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.avansprojects.antl.infrastructure.daos.UserDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.Contact;
import com.avansprojects.antl.infrastructure.entities.User;
import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.ui.login.dto.LoginRequestDTO;
import com.avansprojects.antl.ui.login.dto.UserDto;
import com.avansprojects.antl.ui.login.services.LoginService;
import com.avansprojects.antl.ui.login.services.UserService;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public final class Authentication {

    public static void logout() {
        SharedPreferences.Editor edit;
        edit = AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).edit();
        edit.putString("token", "");
        edit.commit();
        new AntlDatabase.DropDatabaseAsync();
    }

    public static boolean verify(View view) {
        // Check if logged in
        String token = AntlApp.getContext().getSharedPreferences("antlPrefs", MODE_PRIVATE).getString("token", "");
        if (token.isEmpty())
        {
            Navigation.findNavController(view).navigate(R.id.go_to_login);
            return false;
        }

        return true;
    }

    public static void login(View view, LoginRequestDTO loginRequest)
    {
        Retrofit retrofit = AntlRetrofit.getRetrofit();

        LoginService service = retrofit.create(LoginService.class);
        Call<String> call = service.login(loginRequest);
        retrofit2.Response<String> result = null;
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.toString();

                if (response.code() == 200) {
                    result = response.body();

                    SharedPreferences.Editor edit;
                    edit = AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).edit();
                    edit.putString("token", result);
                    Log.i("Login", result);
                    edit.commit();
                    Log.i("SharedToken", AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).getString("token", "No token"));

                    Log.d(this.getClass().toString(), "Message received: " + result);

                    JWT jwt = new JWT(result);

                    Log.i("Claim: ", jwt.getClaim("UUID").asString());

                    // create applicationUser
                    createApplicationUser(Integer.parseInt(jwt.getClaim("UUID").asString()));

                    NavController navController = Navigation.findNavController(view);
                    navController.navigateUp();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });
    }

    private static void createApplicationUser(int id) {
        Retrofit retrofit = AntlRetrofit.getRetrofit();

        UserService service = retrofit.create(UserService.class);
        Call<UserDto> call = service.getUser("Bearer " + AntlApp.getContext().getSharedPreferences("antlPrefs", MODE_PRIVATE).getString("token", ""), id);
        retrofit2.Response<UserDto> result = null;
        call.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                String result = response.toString();

                if (response.code() == 200) {
                    result = response.toString();
                    Log.i("UserResult", result);

                    new AntlDatabase.SetMainUser(AntlDatabase.getDatabase(AntlApp.getContext()), response.body()).execute();

                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });



//        UserDao userDao = AntlDatabase.getDatabase(AntlApp.getContext().getApplicationContext()).userDao();
//        User user = new User(UserDto);
//
//        userDao.insert(user);
    }
}
