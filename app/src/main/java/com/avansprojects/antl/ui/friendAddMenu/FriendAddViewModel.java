package com.avansprojects.antl.ui.friendAddMenu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.helpers.Authentication;
import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.ui.login.dto.FriendRequestDto;
import com.avansprojects.antl.ui.login.services.FriendService;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FriendAddViewModel extends ViewModel {
    private String mUserCode;

    public FriendAddViewModel(){
        mUserCode = AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).getString("code", "");
    }

    public void addFriend(View view, String friendCode) {
        if (mUserCode.equals(""))
        {
            Authentication.logout(view);
            return;
        }

        FriendRequestDto friendRequestDto = new FriendRequestDto(friendCode);

        Retrofit retrofit = AntlRetrofit.getRetrofit();

        FriendService service = retrofit.create(FriendService.class);
        Call<FriendRequestDto> call = service.addFriend("Bearer " + AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).getString("token", ""), friendRequestDto);
        retrofit2.Response<FriendRequestDto> result = null;
        call.enqueue(new Callback<FriendRequestDto>() {
            @Override
            public void onResponse(Call<FriendRequestDto> call, Response<FriendRequestDto> response) {
                String result = response.toString();

                if (response.code() == 200) {


                    Navigation.findNavController(view).navigateUp();
                }
            }

            @Override
            public void onFailure(Call<FriendRequestDto> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });


    }

    public void share(View view) {
        if (mUserCode.equals(""))
        {
            Authentication.logout(view);
            return;
        }

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.antl.nl/friendRequest/" + mUserCode);
        sendIntent.setType("text/plain");
        view.getContext().startActivity(sendIntent);
    }
}
