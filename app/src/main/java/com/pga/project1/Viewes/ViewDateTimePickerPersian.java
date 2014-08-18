package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.pga.project1.R;
import com.pga.project1.Utilities.SolarCalendar;

/**
 * Created by ashkan on 8/18/2014.
 */
public class ViewDateTimePickerPersian extends LinearLayout {

    int year;
    int month;
    int day;

    int hour;
    int minute;


    NumberPicker hourPicker;
    NumberPicker minutePicker;

    NumberPicker yearPicker;
    NumberPicker monthPicker;
    NumberPicker dayPicker;


    public ViewDateTimePickerPersian(Context context, AttributeSet attrs) {
        super(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewNameValue);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_datepicker, this, true);

        hourPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_hour);
        minutePicker = (NumberPicker) findViewById(R.id.numPic_datepicker_minute);

        yearPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_year);
        monthPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_month);
        dayPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_day);

        setHourPicker();
        setMinutePicker();

        setYearPicker();
        setMonthPicker();
        setDayPicker();

        SolarCalendar sc = new SolarCalendar();

        int year = a.getInt(R.styleable.ViewDateTimePickerPersian_year, sc.getYear());
        int month = a.getInt(R.styleable.ViewDateTimePickerPersian_month, sc.getMonth());
        int day = a.getInt(R.styleable.ViewDateTimePickerPersian_year, sc.getDay());

        int hour = a.getInt(R.styleable.ViewDateTimePickerPersian_hour, sc.getHour());
        int minute = a.getInt(R.styleable.ViewDateTimePickerPersian_minute, sc.getMinute());

        this.setDate(year, month, day, hour, minute);

        a.recycle();

    }

    private void setDate(int year, int month, int day, int hour, int minute) {

        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setHour(hour);
        this.setMinute(minute);
    }

    public void setCurrentDate() {

        SolarCalendar sc = new SolarCalendar();

        this.setYear(sc.getYear());
        this.setMonth(sc.getYear());
        this.setDay(sc.getYear());

        invalidate();
        requestLayout();
    }

    public void SetDateTime(int year, int month, int day, int hour, int minute ){

        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);

        invalidate();
        requestLayout();
    }

    private void setDayPicker() {

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
    }
    private void setMonthPicker() {

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
    }
    private void setYearPicker() {

        yearPicker.setMinValue(1300);
        yearPicker.setMaxValue(1500);
    }
    private void setHourPicker() {

        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);
    }
    private void setMinutePicker() {

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
    }


    private void setYear(int year) {
        this.year = year;
        this.yearPicker.setValue(year);
    }

    private void setMonth(int month) {
        this.month = month;
        this.monthPicker.setValue(month);
    }

    private void setDay(int day) {
        this.day = day;
        this.dayPicker.setValue(day);
    }

    public void setHour(int hour) {
        this.hour = hour;
        this.hourPicker.setValue(hour);
    }

    public void setMinute(int minute) {
        this.minute = minute;
        this.minutePicker.setValue(minute);
    }


    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public SolarCalendar getDate(){
        SolarCalendar sc = new SolarCalendar(year, month, day, hour, minute, 0);
        return sc;
    }


}
