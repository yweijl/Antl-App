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

public class EventOverviewFragment extends Fragment {

    private EventOverviewViewModel _ViewModel;

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
        _ViewModel = ViewModelProviders.of(this).get(EventOverviewViewModel.class);
        RecyclerView mRecyclerView = getView().findViewById(R.id.eventRecyclerView);
        EventOverviewAdapter adapter = new EventOverviewAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Update the cached copy of the words in the adapter.
        _ViewModel.getAllEvents().observe(this, adapter::setEvents);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _ViewModel = ViewModelProviders.of(this).get(EventOverviewViewModel.class);
        // TODO: Use the ViewModel
    }
}
