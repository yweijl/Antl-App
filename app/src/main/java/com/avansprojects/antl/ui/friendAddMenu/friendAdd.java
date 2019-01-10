package com.avansprojects.antl.ui.friendAddMenu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;

public class friendAdd extends Fragment {

    private FriendAddViewModel mViewModel;
    private TextView textView;

    public static friendAdd newInstance() {
        return new friendAdd();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_add_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FriendAddViewModel.class);
        textView = getActivity().findViewById(R.id.editText);

        Button addButton = getActivity().findViewById(R.id.next_button);
        addButton.setOnClickListener(v -> mViewModel.addFriend(Integer.parseInt(textView.getText().toString())));
    }

}
