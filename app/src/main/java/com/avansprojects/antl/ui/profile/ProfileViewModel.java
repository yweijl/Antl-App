package com.avansprojects.antl.ui.profile;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.User;
import com.avansprojects.antl.infrastructure.repositories.UserRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProfileViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> mUsers;
    private User mainUser;
    private String userName;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        mUsers = userRepository.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return mUsers;
    }

    public String getUserName() {
        return userName;
    }
}
