package com.avansprojects.antl.ui.friendAddMenu;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.helpers.Authentication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendAddViewModel extends ViewModel {
    private int _friendId = 0;

    public FriendAddViewModel(){
    }

    public int get_data() {
        return _friendId;
    }

    public void addFriend(int i) {
    }

    public void share(View view) {
        String code = AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).getString("code", "");

        if (code == "")
        {
            Authentication.logout(view);
            return;
        }
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.antl.nl/friendRequest/" + code);
        sendIntent.setType("text/plain");
        view.getContext().startActivity(sendIntent);
    }
}
