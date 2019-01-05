package com.avansprojects.antl.ui.createEvent;

import android.content.Context;
import android.widget.TextView;

import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import androidx.fragment.app.Fragment;


public class SetDatePickerDate implements DatePickerDialog.OnDateSetListener {

    private int _textViewId;
    private Fragment _fragment;

    public SetDatePickerDate(int textViewId, Fragment fragment) {
        _textViewId = _textViewId;
        _fragment = fragment;

        DatePickerDialog dialog = DatePickerDialog.newInstance(
                this, CalendarHelper.getCurrentYear(), CalendarHelper.getCurrentMonth(), CalendarHelper.getCurrentDay()
        );

        dialog.setOkText(R.string.ok);
        dialog.setCancelText(R.string.cancel);
        dialog.setOkColor(R.style.TextColor);
        dialog.setCancelColor(R.style.TextColor);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
        TextView DateTextView = _fragment.getView().findViewById(_textViewId);
        DateTextView.setText(date);
    }
}
