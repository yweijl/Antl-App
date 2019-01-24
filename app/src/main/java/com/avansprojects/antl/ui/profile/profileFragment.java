package com.avansprojects.antl.ui.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.Authentication;
import com.avansprojects.antl.infrastructure.entities.User;

import java.util.List;
import java.util.Objects;

public class profileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private TextView userNameTextView;
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView emailTextView;
    private TextView phoneNumberTextView;

    private List<User> mUserList;

    public static profileFragment newInstance() {
        return new profileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        Button logoutButton = getActivity().findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(Authentication::logout);

        userNameTextView = getActivity().findViewById(R.id.editUserName);
        firstNameTextView = getActivity().findViewById(R.id.editFirstName);
        lastNameTextView = getActivity().findViewById(R.id.editLastName);
        emailTextView = getActivity().findViewById(R.id.editEmail);


        final Observer<List<User>> messageObserver = users -> {
            if (users.size() == 1) {
                User mainUser = users.get(0);
                userNameTextView.setText(mainUser.getUserName());
                firstNameTextView.setText(mainUser.firstName);
                lastNameTextView.setText(mainUser.lastName);
                emailTextView.setText(mainUser.email);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.getUsers().observe(this, messageObserver);
    }
}
