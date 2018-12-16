package com.avansprojects.antl.ui.eventOverviewList;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avansprojects.antl.R;
import com.avansprojects.antl.dummy.DummyEvents;

import java.util.ArrayList;
import java.util.List;

public class EventOverViewList extends Fragment {

    private EventOverViewListViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static EventOverViewList newInstance() {
        return new EventOverViewList();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        List<DummyEvents> eventsList = new DummyEvents().GetEventsList();

        mAdapter = new EventOverviewAdapter(eventsList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView = mRecyclerView.findViewById(R.id.eventOverviewListFragment);
        mRecyclerView.setAdapter(mAdapter);

        EventOverviewAdapter eventOverviewAdapter = new EventOverviewAdapter(eventsList);
        mRecyclerView.setAdapter(eventOverviewAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.notifyDataSetChanged();

        return inflater.inflate(R.layout.event_overview_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventOverViewListViewModel.class);
        // TODO: Use the ViewModel
    }
}
