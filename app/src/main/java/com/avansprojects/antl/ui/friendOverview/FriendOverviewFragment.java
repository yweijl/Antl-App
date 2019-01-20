package com.avansprojects.antl.ui.friendOverview;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avansprojects.antl.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FriendOverviewFragment extends Fragment {

    private FriendOverviewViewModel mViewModel;
    private FriendOverviewAdapter mAdapter;

    public static FriendOverviewFragment newInstance() {
        return new FriendOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        mViewModel = ViewModelProviders.of(this).get(FriendOverviewViewModel.class);
        RecyclerView mRecyclerView = getView().findViewById(R.id.friendRecyclerView);
        mAdapter = new FriendOverviewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Update the cached copy of the words in the adapter.
        mViewModel.getAllContacts().observe(this, mAdapter::setFriends);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FriendOverviewViewModel.class);

        FloatingActionButton friendAddButton = getView().findViewById(R.id.addFriendButton);
        friendAddButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.to_destination_friend_add));
    }

}
