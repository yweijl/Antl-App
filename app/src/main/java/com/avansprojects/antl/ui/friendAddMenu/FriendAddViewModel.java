package com.avansprojects.antl.ui.friendAddMenu;

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
}
