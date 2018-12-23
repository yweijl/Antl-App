package com.avansprojects.antl.ui.login;

import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private TextView _User;
    private TextView _Password;
    private TextView _Email;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        _User = getView().findViewById(R.id.username_text_input);
        _Password = getView().findViewById(R.id.password_edit_text);
        _Email = getView().findViewById(R.id.email_edit_text);
        _Email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        Button button = getView().findViewById(R.id.cancel_button);
        button.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.to_destination_login));

        Button button1 = getView().findViewById(R.id.next_button);
        button1.setOnClickListener(view -> {
            mViewModel.setUserName(_User.getText());
            mViewModel.setPassword(_Password.getText());
            boolean correctEmail = mViewModel.setEmail(_Email.getText());

            if (correctEmail) {
                mViewModel.Register();
            }
        });
    }

}
