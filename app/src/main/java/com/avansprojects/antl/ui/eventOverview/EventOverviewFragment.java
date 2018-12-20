package com.avansprojects.antl.ui.eventOverview;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.avansprojects.antl.R;
import com.avansprojects.antl.dummy.DummyEvents;

import java.util.List;

public class EventOverviewFragment extends Fragment {

    private EventOverViewListViewModel mViewModel;
    private RecyclerView.Adapter mAdapter;

    public static EventOverviewFragment newInstance() {
        return new EventOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
                return inflater.inflate(R.layout.event_overview_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        List<DummyEvents> eventsList = new DummyEvents().GetEventsList();

        RecyclerView mRecyclerView = getView().findViewById(R.id.eventRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new EventOverviewAdapter(eventsList);
        mRecyclerView.setAdapter(mAdapter);

        EventOverviewAdapter eventOverviewAdapter = new EventOverviewAdapter(eventsList);
        mRecyclerView.setAdapter(eventOverviewAdapter);

//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventOverViewListViewModel.class);
        // TODO: Use the ViewModel
    }
}
