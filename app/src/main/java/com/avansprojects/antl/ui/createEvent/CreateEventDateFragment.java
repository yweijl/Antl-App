package com.avansprojects.antl.ui.createEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.helpers.DatePickerFactory;
import com.avansprojects.antl.ui.eventOverview.EventOverviewAdapter;
import com.avansprojects.antl.ui.eventOverview.EventOverviewViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.avansprojects.antl.R;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CreateEventDateFragment extends Fragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_event_date_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        RecyclerView mRecyclerView = getView().findViewById(R.id.eventDateRecyclerView);
        EventDateAdapter adapter = new EventDateAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter.addItem();

        DatePickerFactory datePickerFactory = new DatePickerFactory(this);

        DatePickerFactory.DateDialog firstDateDialog = datePickerFactory.getDateDialog(R.id.firstEventDate);
        DatePickerDialog firstEventDate = firstDateDialog.getInstance();
        Button firstDateButton = getView().findViewById(R.id.addDateToEvent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        DatePickerFactory datePickerFactory = new DatePickerFactory(this);
//
//        DatePickerFactory.DateDialog firstDateDialog = datePickerFactory.getDateDialog(R.id.firstEventDate);
//        DatePickerFactory.DateDialog secondDateDialog = datePickerFactory.getDateDialog(R.id.secondEventDate);
//        DatePickerFactory.DateDialog thirdDateDialog = datePickerFactory.getDateDialog(R.id.thirdEventDate);
//        DatePickerFactory.DateDialog fourthDateDialog = datePickerFactory.getDateDialog(R.id.fourthEventDate);
//
//        DatePickerFactory.TimeDialog firstTimeDialog = datePickerFactory.getTimeDialog(R.id.firstEventTime);
//        DatePickerFactory.TimeDialog secondTimeDialog = datePickerFactory.getTimeDialog(R.id.secondEventTime);
//        DatePickerFactory.TimeDialog thirdTimeDialog = datePickerFactory.getTimeDialog(R.id.thirdEventTime);
//        DatePickerFactory.TimeDialog fourthTimeDialog = datePickerFactory.getTimeDialog(R.id.fourthEventTime);
//
//        DatePickerDialog secondEventDate = secondDateDialog.getInstance();
//        DatePickerDialog thirdEventDate = thirdDateDialog.getInstance();
//        DatePickerDialog fourthEventDate = fourthDateDialog.getInstance();
//
//        TimePickerDialog firstEventTime = firstTimeDialog.getInstance();
//        TimePickerDialog secondEventTime = secondTimeDialog.getInstance();
//        TimePickerDialog thirdEventTime = thirdTimeDialog.getInstance();
//        TimePickerDialog fourthEventTime = fourthTimeDialog.getInstance();
//
//        Button firstDateButton = getView().findViewById(R.id.addDateToEvent);
//        Button secondDateButton = getView().findViewById(R.id.addDateToEvent1);
//        Button thirdDateButton = getView().findViewById(R.id.addDateToEvent2);
//        Button fourthDateButton = getView().findViewById(R.id.addDateToEvent3);
//
//
//
//
//        secondDateButton.setOnClickListener(x -> {
//            assert getFragmentManager() != null;
//            secondEventDate.show(getFragmentManager(), "Datepickerdialog");
//        });
//
//        thirdDateButton.setOnClickListener(x -> {
//            assert getFragmentManager() != null;
//            thirdEventDate.show(getFragmentManager(), "Datepickerdialog");
//        });
//
//        fourthDateButton.setOnClickListener(x -> {
//            assert getFragmentManager() != null;
//            fourthEventDate.show(getFragmentManager(), "Datepickerdialog");
//        });
    }
}
