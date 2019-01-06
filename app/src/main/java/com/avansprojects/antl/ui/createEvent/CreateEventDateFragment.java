package com.avansprojects.antl.ui.createEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.avansprojects.antl.helpers.DatePickerFactory;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.avansprojects.antl.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreateEventDateFragment extends Fragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_event_date_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatePickerFactory datePickerFactory = new DatePickerFactory(this);

        DatePickerDialog firstEventDate = datePickerFactory.getDateDialog(R.id.firstEventDate, R.id.firstEventTime);
        DatePickerDialog secondEventDate = datePickerFactory.getDateDialog(R.id.secondEventDate, R.id.secondEventTime);
        DatePickerDialog thirdEventDate = datePickerFactory.getDateDialog(R.id.thirdEventDate, R.id.thirdEventTime);
        DatePickerDialog fourthEventDate = datePickerFactory.getDateDialog(R.id.fourthEventDate, R.id.fourthEventTime);

        Button firstDateButton = getView().findViewById(R.id.addDateToEvent);
        Button secondDateButton = getView().findViewById(R.id.addDateToEvent1);
        Button thirdDateButton = getView().findViewById(R.id.addDateToEvent2);
        Button fourthDateButton = getView().findViewById(R.id.addDateToEvent3);

        firstDateButton.setOnClickListener(x -> {
            assert getFragmentManager() != null;
            firstEventDate.show(getFragmentManager(), "Datepickerdialog");
            secondDateButton.setVisibility(View.VISIBLE);
        });

        secondDateButton.setOnClickListener(x -> {
            assert getFragmentManager() != null;
            secondEventDate.show(getFragmentManager(), "Datepickerdialog");
            thirdDateButton.setVisibility(View.VISIBLE);
        });

        thirdDateButton.setOnClickListener(x -> {
            assert getFragmentManager() != null;
            thirdEventDate.show(getFragmentManager(), "Datepickerdialog");
            fourthDateButton.setVisibility(View.VISIBLE);
        });

        fourthDateButton.setOnClickListener(x -> {
            assert getFragmentManager() != null;
            fourthEventDate.show(getFragmentManager(), "Datepickerdialog");
        });
    }
}
