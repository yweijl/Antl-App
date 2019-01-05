package com.avansprojects.antl.ui.createEvent;

import android.widget.TextView;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import androidx.fragment.app.Fragment;

public class DatePickerFactory {

    private Fragment _fragment;

    public DatePickerFactory(Fragment _fragment) {
        this._fragment = _fragment;
    }

    public DatePickerDialog getDateDialog(int dateViewId, int timeViewId) {
        DateDialog dialog = new DateDialog();
        return dialog.getInstance(dateViewId, timeViewId);
    }

    public TimePickerDialog getTimeDialog(int textViewId) {
        TimeDialog dialog = new TimeDialog();
        return dialog.getInstance(textViewId);
    }

    public class TimeDialog implements TimePickerDialog.OnTimeSetListener{

        private int _textViewId;

        private TimePickerDialog getInstance(int textViewId){
            _textViewId = textViewId;
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
            String time = hourOfDay+" : "+minute+"    /";
            TextView timeTextView = _fragment.getView().findViewById(_textViewId);
            timeTextView.setText(time);
        }
    }

    public class DateDialog implements DatePickerDialog.OnDateSetListener {

        private int _dateViewId;
        private int _timeViewId;

        private DatePickerDialog getInstance(int dateViewId, int timeViewId) {
            _dateViewId = dateViewId;
            _timeViewId = timeViewId;

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
            String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
            TextView dateTextView = _fragment.getView().findViewById(_dateViewId);
            dateTextView.setText(date);
            TimePickerDialog eventTime = getTimeDialog(_timeViewId);

            assert _fragment.getFragmentManager() != null;
            eventTime.show(_fragment.getFragmentManager(), "TimePickerDialog");
        }
    }
}