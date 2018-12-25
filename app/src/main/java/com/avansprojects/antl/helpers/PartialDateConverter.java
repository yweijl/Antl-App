package com.avansprojects.antl.helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.SHORT;

public class PartialDateConverter {

    public static String getDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
    }

    public static String getMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String month = cal.getDisplayName(Calendar.MONTH, SHORT, Locale.getDefault());

        if (month.endsWith(".")) {
            month = month.substring(0, month.length() - 1);
        }

        return month;
    }

    public static Date setDate(int year, int month, int day){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }
}
