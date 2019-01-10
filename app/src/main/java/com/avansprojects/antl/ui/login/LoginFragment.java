package com.avansprojects.antl.ui.login;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private TextView mUser;
    private TextView mPassword;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mUser = getView().findViewById(R.id.username_text_input);
        mPassword = getView().findViewById(R.id.password_edit_text);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
        bottomNavigationView.setVisibility(View.GONE);

        Button registerButton = getView().findViewById(R.id.register_button);
        registerButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.to_destination_register));

        Button loginButton = getView().findViewById(R.id.next_button);
        loginButton.setOnClickListener(view -> {
            mViewModel.setUser(mUser.getText());
            mViewModel.setPassword(mPassword.getText());
            mViewModel.Login(view);
        });
    }
}
