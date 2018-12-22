package com.avansprojects.antl.ui.eventOverviewCard;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avansprojects.antl.R;

public class EventOverviewCard extends Fragment {

    private EventOverviewCardViewModel mViewModel;

    public static EventOverviewCard newInstance() {
        return new EventOverviewCard();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_overview_card_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventOverviewCardViewModel.class);
        // TODO: Use the ViewModel
    }

}
