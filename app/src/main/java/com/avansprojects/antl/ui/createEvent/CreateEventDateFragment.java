package com.avansprojects.antl.ui.createEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreateEventDateFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_event_date_fragment, container, false);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
        TextView firstDateTextView = getView().findViewById(R.id.firstEventDate);
        firstDateTextView.setText(date);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatePickerDialog dialog = DatePickerDialog.newInstance(
                this, CalendarHelper.getCurrentYear(), CalendarHelper.getCurrentMonth(), CalendarHelper.getCurrentDay()
        );

        dialog.setOkText(R.string.ok);
        dialog.setCancelText(R.string.cancel);
        dialog.setOkColor(R.style.TextColor);
        dialog.setCancelColor(R.style.TextColor);

        Button addDateButton = getView().findViewById(R.id.addDateToEvent);
        addDateButton.setOnClickListener(x -> {
            assert getFragmentManager() != null;
            dialog.show(getFragmentManager(), "Datepickerdialog");
        });
    }
}
