package com.avansprojects.antl.ui.createEvent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.avansprojects.antl.R;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreateEventDateFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.create_event_name_fragment, container, false);

        Calendar now = Calendar.getInstance();
//        DatePickerDialog dpd = DatePickerDialog.newInstance(
//                MainActivity.this,
//                now.get(Calendar.YEAR), // Initial year selection
//                now.get(Calendar.MONTH), // Initial month selection
//                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
//        );

        DatePickerDialog dialog = onDateSet(this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));


        // If you're calling this from a support Fragment
        dpd.show(getFragmentManager(), "Datepickerdialog");
        // If you're calling this from an AppCompatActivity
        // dpd.show(getSupportFragmentManager(), "Datepickerdialog");

        return rootView;
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: "+hourOfDay+"h"+minute+"m"+second;
        timeTextView.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        dateTextView.setText(date);
    }
}
