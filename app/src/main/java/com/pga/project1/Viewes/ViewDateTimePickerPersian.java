package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.pga.project1.R;
import com.pga.project1.Utilities.PersianCalendar;

/**
 * Created by ashkan on 8/18/2014.
 */
public class ViewDateTimePickerPersian extends LinearLayout {

    NumberPicker hourPicker;
    NumberPicker minutePicker;

    NumberPicker yearPicker;
    NumberPicker monthPicker;
    NumberPicker dayPicker;

    boolean hideTime = false;
    boolean hideDate = false;


    public ViewDateTimePickerPersian(Context context, AttributeSet attrs) {
        super(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewNameValue);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_datepicker, this, true);

        findAndSetViews();

        PersianCalendar pc = new PersianCalendar();

        hideTime = a.getBoolean(R.styleable.ViewDateTimePickerPersian_hideTime, false);
        hideDate = a.getBoolean(R.styleable.ViewDateTimePickerPersian_hideDate, false);


        int year = a.getInt(R.styleable.ViewDateTimePickerPersian_year, pc.getIranianYear());
        int month = a.getInt(R.styleable.ViewDateTimePickerPersian_month, pc.getIranianMonth());
        int day = a.getInt(R.styleable.ViewDateTimePickerPersian_year, pc.getIranianDay());

        int hour = a.getInt(R.styleable.ViewDateTimePickerPersian_hour, pc.getHour());
        int minute = a.getInt(R.styleable.ViewDateTimePickerPersian_minute, pc.getMinute());

        this.setDate(year, month, day, hour, minute);

        a.recycle();

    }


    public ViewDateTimePickerPersian(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_datepicker, this, true);

        findAndSetViews();

        setCurrentDate();
    }


    public ViewDateTimePickerPersian(Context context, PersianCalendar date) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_datepicker, this, true);

        findAndSetViews();

        setDate(date.getIranianYear(), date.getIranianMonth(), date.getIranianDay(), date.getHour(), date.getMinute());
    }

    private void findAndSetViews() {

        hourPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_hour);
        minutePicker = (NumberPicker) findViewById(R.id.numPic_datepicker_minute);

        yearPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_year);
        monthPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_month);
        dayPicker = (NumberPicker) findViewById(R.id.numPic_datepicker_day);

        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {

                int days = PersianCalendar.daysInMonth(i2, getMonth());
                dayPicker.setMaxValue(days);
            }
        });


        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {

                int days = PersianCalendar.daysInMonth(getYear(), i2);

                dayPicker.setMaxValue(days);
                dayPicker.setMaxValue(days);
            }
        });

        setHourPicker();
        setMinutePicker();

        setYearPicker();
        setMonthPicker();
        setDayPicker();
    }

    private void setDate(int year, int month, int day, int hour, int minute) {

        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setHour(hour);
        this.setMinute(minute);

        int days = PersianCalendar.daysInMonth(year, month);
        dayPicker.setMaxValue(days);
    }

    public void setCurrentDate() {

        PersianCalendar pc = new PersianCalendar();

        this.setYear(pc.getIranianYear()); //sc.getYear());
        this.setMonth(pc.getIranianMonth()); //sc.getMonth());
        this.setDay(pc.getIranianDay());//sc.getDay());

        this.setHour(pc.getHour()); //sc.getHour());
        this.setMinute(pc.getMinute());//sc.getMinute());

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

    public PersianCalendar getDate() {

        PersianCalendar pc = new PersianCalendar();
        pc.setIranianDate(getYear(), getMonth(), getDay());
        pc.setTime(getHour(), getMinute(), 0);


        return pc;
    }


}
