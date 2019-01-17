package com.avansprojects.antl.helpers;

import com.avansprojects.antl.R;
import com.avansprojects.antl.listeners.DatePickerListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import java.util.Date;

import androidx.fragment.app.Fragment;

public class DatePickerFactory {

    private Fragment mFragment;
    private DatePickerListener mListener;
    private Date mDate;
    private Date mTime;

    public DatePickerFactory(Fragment fragment, DatePickerListener datePickerListener) {
        mFragment = fragment;
        mListener = datePickerListener;
    }

    public void getInstance() {

        assert mFragment.getFragmentManager() != null;

        DateDialog dateDialog = new DateDialog();
        DatePickerDialog datePicker = dateDialog.getInstance();
        datePicker.show(mFragment.getFragmentManager(), "DatePickerDialog");
    }

    public Date getDateTime(){
        return CalendarHelper.joinDateTime(mDate, mTime);
    }

    public void setTime(int hourOfDay, int minute) {
        mTime = CalendarHelper.setTime(hourOfDay, minute);
    }


    public class TimeDialog implements TimePickerDialog.OnTimeSetListener{
        public TimePickerDialog getInstance(){
            TimePickerDialog dialog = TimePickerDialog.newInstance(
                    this, CalendarHelper.getCurrentHours(), CalendarHelper.getCurrentMinutes(), true
            );

            dialog.setOkText(R.string.ok);
            dialog.setCancelText(R.string.cancel);
            dialog.setOkColor(R.style.TextColor);
            dialog.setCancelColor(R.style.TextColor);

            return dialog;
        }

        @Override
        public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
            mTime = CalendarHelper.setTime(hourOfDay, minute);
            mListener.addDateToListFromDatePicker(getDateTime());
        }
    }

    public class DateDialog implements DatePickerDialog.OnDateSetListener {

        public DatePickerDialog getInstance() {

            DatePickerDialog dialog = DatePickerDialog.newInstance(
                    this, CalendarHelper.getCurrentYear(), CalendarHelper.getCurrentMonth(), CalendarHelper.getCurrentDay()
            );

            dialog.setOkText(R.string.ok);
            dialog.setCancelText(R.string.cancel);
            dialog.setOkColor(R.style.TextColor);
            dialog.setCancelColor(R.style.TextColor);

            return dialog;
        }

        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            mDate = CalendarHelper.setDate(year, monthOfYear, dayOfMonth);
            assert mFragment.getFragmentManager() != null;
            TimeDialog timeDialog = new TimeDialog();
            TimePickerDialog timePicker = timeDialog.getInstance();
            timePicker.show(mFragment.getFragmentManager(), "TimePickerDialog");
        }
    }
}