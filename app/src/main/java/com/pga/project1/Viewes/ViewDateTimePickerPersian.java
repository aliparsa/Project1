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


    public ViewDateTimePickerPersian(Context context) {
        super(context);

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

        setCurrentDate();
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
        this.setMonth(sc.getMonth());
        this.setDay(sc.getDay());

        this.setHour(sc.getHour());
        this.setMinute(sc.getMinute());

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

        hourPicker.setDisplayedValues(new String[]{
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23",
        });
    }
    private void setMinutePicker() {

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);

        minutePicker.setDisplayedValues(new String[]{
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
                "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"
        });
    }


    private void setYear(int year) {
        this.yearPicker.setValue(year);
    }

    private void setMonth(int month) {
        this.monthPicker.setValue(month);
    }

    private void setDay(int day) {
        this.dayPicker.setValue(day);
    }

    public void setHour(int hour) {
        this.hourPicker.setValue(hour);
    }

    public void setMinute(int minute) {
        this.minutePicker.setValue(minute);
    }


    public int getYear() {
        return this.yearPicker.getValue();
    }

    public int getMonth() {
        return this.monthPicker.getValue();
    }

    public int getDay() {
        return this.dayPicker.getValue();
    }

    public int getHour() {
        return this.hourPicker.getValue();
    }

    public int getMinute() {
        return this.minutePicker.getValue();
    }

    public SolarCalendar getDate(){

        SolarCalendar sc = new SolarCalendar(getYear(), getMonth(), getDay(), getHour(), getMinute(), 0);
        return sc;
    }


}
