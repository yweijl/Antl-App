package com.avansprojects.antl.ui.friendAddMenu;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;

public class FriendAddFragment extends Fragment {

    private FriendAddViewModel mViewModel;
    private TextView mExternalFriendCode;
    private TextView mFriendCode;
    private TextView mEditFriendCode;

    public static FriendAddFragment newInstance() {
        return new FriendAddFragment();
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
        mExternalFriendCode = getActivity().findViewById(R.id.editFriendCode);
        mFriendCode = getActivity().findViewById(R.id.friendCode);

        mExternalFriendCode.setText(FriendAddFragmentArgs.fromBundle(getArguments()).getIncomingFriendCode());
        mFriendCode.setText(AntlApp.getContext().getSharedPreferences("antlPrefs", Context.MODE_PRIVATE).getString("code", "Error"));

        Button addButton = getActivity().findViewById(R.id.next_button);
        addButton.setOnClickListener(v -> mViewModel.addFriend(v, mExternalFriendCode.getText().toString()));

        Button shareButton = getActivity().findViewById(R.id.share);
        shareButton.setOnClickListener(v -> mViewModel.share(v));
    }

}
