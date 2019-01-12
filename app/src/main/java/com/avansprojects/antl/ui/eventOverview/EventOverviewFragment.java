package com.avansprojects.antl.ui.eventOverview;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Rect;
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

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Context.MODE_PRIVATE;

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
        // Remove login page from backstack
        // Navigation.findNavController(getView()).popBackStack(R.id.destination_events, false);

        // Check if logged in
        String token = AntlApp.getContext().getSharedPreferences("antlPrefs", MODE_PRIVATE).getString("token", "");
//        if (token.isEmpty())
//        {
//            Navigation.findNavController(getView()).navigate(R.id.go_to_login);
//        }

        BottomNavigationView menu = getActivity().findViewById(R.id.bottom_nav);
        menu.setVisibility(View.VISIBLE);

        _ViewModel = ViewModelProviders.of(this).get(EventOverviewViewModel.class);
        RecyclerView mRecyclerView = getView().findViewById(R.id.eventRecyclerView);
        EventOverviewAdapter adapter = new EventOverviewAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        float offsetPx = getResources().getDimension(R.dimen.recycler_event_overview_offset);
        BottomOffsetDecoration bottomOffsetDecoration = new BottomOffsetDecoration((int) offsetPx);
        mRecyclerView.addItemDecoration(bottomOffsetDecoration);

        // Update the cached copy of the words in the adapter.
        _ViewModel.getAllEvents().observe(this, adapter::setEvents);
    }

    static class BottomOffsetDecoration extends RecyclerView.ItemDecoration {
        private int mBottomOffset;

        public BottomOffsetDecoration(int bottomOffset) {
            mBottomOffset = bottomOffset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int dataSize = state.getItemCount();
            int position = parent.getChildAdapterPosition(view);
            if (dataSize >= 3 && position == dataSize - 1) {
                outRect.set(0, 0, 0, mBottomOffset);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _ViewModel = ViewModelProviders.of(this).get(EventOverviewViewModel.class);
        FloatingActionButton createEventFab = getView().findViewById(R.id.createEventFab);
        createEventFab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_destination_events_to_Destination_create_event));
        // TODO: Use the ViewModel
    }
}
