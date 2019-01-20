package com.avansprojects.antl.ui.createEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.BottomOffsetDecoration;
import com.avansprojects.antl.helpers.DatePickerFactory;
import com.avansprojects.antl.infrastructure.entities.EventDate;
import com.avansprojects.antl.listeners.DatePickerListener;
import com.avansprojects.antl.listeners.ViewModelListener;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CreateEventDateFragment extends Fragment implements DatePickerListener, ViewModelListener {

    private EventDateAdapter mAdapter;
    private CreateEventDateViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_event_date_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setRecyclerView();
        setDatePickerButton();
    }

    private void setDatePickerButton() {
        DatePickerFactory datePickerFactory = new DatePickerFactory(this, this);
        Button addDateButton = getView().findViewById(R.id.addDateToEvent);
        addDateButton.setOnClickListener(x ->
                datePickerFactory.getInstance());
    }

    private void setRecyclerView() {
        RecyclerView mRecyclerView = getView().findViewById(R.id.eventDateRecyclerView);
        mAdapter = new EventDateAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        float offsetPx = getResources().getDimension(R.dimen.recycler_add_date_offset);
        BottomOffsetDecoration bottomOffsetDecoration = new BottomOffsetDecoration((int) offsetPx, 5);
        mRecyclerView.addItemDecoration(bottomOffsetDecoration);
    }

    @Override
    public void addDateToListFromDatePicker(Date date) {
        EventDate eventDate = new EventDate(date);
        mAdapter.addItem(eventDate);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(CreateEventDateViewModel.class);
    }

    @Override
    public void dataIsChanged() {
        mViewModel.setEventDates(mAdapter.getEventDates());
    }
}
