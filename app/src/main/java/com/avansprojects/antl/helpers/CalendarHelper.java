package com.avansprojects.antl.helpers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.SHORT;

public class CalendarHelper {

    public static int getCurrentSeconds(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.SECOND);
    }

    public static int getCurrentMinutes(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MINUTE);
    }

    public static int getCurrentHours(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentDay(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMonth(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    public static int getCurrentYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static Date joinDateTime(Date date, Date time){
        Calendar calendar = Calendar.getInstance();

        calendar.set(getNumericYearFromDate(date),
                getNumericMonthFromDate(date),
                getNumericDayFromDate(date),
                getNumericHoursFromDate(time),
                getNumericMinutesFromDate(time));
        return calendar.getTime();
    }

    public static String DateTimeToString(Date date){

        return new SimpleDateFormat("dd-MM-YYYY - HH:mm").format(date);
    }

    public static int getNumericMinutesFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    public static int getNumericHoursFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getNumericYearFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getNumericDayFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getNumericMonthFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static String getDayFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
    }

    public static String getCurrentMonthAbbreviationFromDate(Date date){
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

    public static Date setTime(int hourOfDay, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }
}
