package com.avansprojects.antl.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.infrastructure.dtos.LoginRequestDTO;
import com.avansprojects.antl.infrastructure.dtos.UserDto;
import com.avansprojects.antl.infrastructure.services.LoginService;
import com.avansprojects.antl.infrastructure.services.UserService;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public final class Authentication {

    public static void logout(View view) {
        SharedPreferences.Editor edit;
        edit = AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).edit();
        edit.putString("token", "");
        edit.putString("code", "");
        edit.commit();
        new AntlDatabase.DropDatabaseAsync().execute();
        Navigation.findNavController(view).navigate(R.id.go_to_login);
    }

    public static boolean verify(View view) {
        // Check if logged in
        String token = AntlApp.getContext().getSharedPreferences("antlPrefs", MODE_PRIVATE).getString("token", "");
        if (token.isEmpty())
        {
            logout(view);
            return false;
        }

        return true;
    }

    public static void login(View view, LoginRequestDTO loginRequest, ProgressBar progressBar)
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

                    Log.i("Claim: ", jwt.getClaim("EID").asString());

                    // create applicationUser
                    createApplicationUser(jwt.getClaim("EID").asString());

                    NavController navController = Navigation.findNavController(view);
                    navController.navigateUp();
                }
                else {
                    Toast toast = Toast.makeText(AntlApp.getContext(), R.string.error_code_login, Toast.LENGTH_SHORT);
                    toast.setText("Login failed with error code: " + response.code());
                    toast.show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast toast = Toast.makeText(AntlApp.getContext(), R.string.error_code_login, Toast.LENGTH_SHORT);
                toast.setText("Something went wrong, please try again later");
                toast.show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private static void createApplicationUser(String id) {
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

                    result = response.body().getExternalId();
                    SharedPreferences.Editor edit;
                    edit = AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).edit();
                    edit.putString("code", result);
                    Log.i("Code:", result);
                    edit.commit();


                    new AntlDatabase.SetMainUser(AntlDatabase.getDatabase(AntlApp.getContext()), response.body()).execute();
                }
                else{
                    Toast toast = Toast.makeText(AntlApp.getContext(), R.string.error_code_login, Toast.LENGTH_SHORT);
                    toast.setText("Error code: " + response.code());
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable throwable) {
                Log.e(this.toString(), "User could not be retrieved");
            }
        });
    }
}
