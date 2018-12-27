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
import android.widget.ScrollView;
import android.widget.TextView;

import com.avansprojects.antl.R;

public class LoginFragment extends Fragment {

    private LoginViewModel _ViewModel;
    private TextView _User;
    private TextView _Password;

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
        _ViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        _User = getView().findViewById(R.id.username_text_input);
        _Password = getView().findViewById(R.id.password_edit_text);

        Button registerButton = getView().findViewById(R.id.register_button);
        registerButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.to_destination_register));

        Button loginButton = getView().findViewById(R.id.next_button);
        loginButton.setOnClickListener((View view) -> {
            _ViewModel.setUser(_User.getText());
            _ViewModel.setPassword(_Password.getText());
            _ViewModel.Login();
        });
    }

}
