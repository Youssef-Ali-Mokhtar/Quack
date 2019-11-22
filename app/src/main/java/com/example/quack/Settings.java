package com.example.quack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class Settings extends AppCompatActivity {

    private Switch mSwitch;
    private TextView earthquakeDate;
    private TextView tsunamiStartDate;
    private TextView tsunamiEndDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private DatePickerDialog.OnDateSetListener tsunamiStartDateSetListener;
    private DatePickerDialog.OnDateSetListener tsunamiEndDateSetListener;
    String tsunamiStartDateString;
    String tsunamiEndDateString;
    static int tsunamiFlagStart = 0;
    static int tsunamiFlagEnd = 0;
    private static final String TAG = "Settings";
    String date;
    String setYear;
    String setMonth;
    String setDay;
    String minMagnitudeString ="&minmagnitude="+ String.valueOf(0.1);
    String maxMagnitudeString ="&maxmagnitude="+ String.valueOf(9.9);
    static int flagDate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbarSettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tsunamis section

        final TsunamiFragment tsunamiFragment = new TsunamiFragment();

        tsunamiStartDate = findViewById(R.id.tsunami_start_date);
        tsunamiStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Settings.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        tsunamiStartDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        tsunamiStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                tsunamiStartDate.setText(String.format("%d/%s/%d", dayOfMonth, getMonth(month), year));
                tsunamiFlagStart = 1;
                tsunamiStartDateString ="&starttime="+ year+"-"+month+"-"+dayOfMonth;
            }
        };

        tsunamiEndDate = findViewById(R.id.tsunami_end_date);
        tsunamiEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Settings.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        tsunamiEndDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        tsunamiEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                tsunamiEndDate.setText(String.format("%d/%s/%d", dayOfMonth, getMonth(month), year));
                tsunamiFlagEnd = 1;
                tsunamiEndDateString ="&endtime="+ year+"-"+month+"-"+dayOfMonth;
            }
        };

        //Earthquakes section
        earthquakeDate = findViewById(R.id.earthquake_date_id);
        earthquakeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Settings.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                date = String.format("%d/%s/%d", dayOfMonth, getMonth(month), year);
                earthquakeDate.setText(date);
                setYear = String.valueOf(year);
                setMonth = String.valueOf(month);
                setDay = String.valueOf(dayOfMonth);
                flagDate = 1;

            }
        };

        NumberPicker numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(10);
        numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minMagnitudeString = "&minmagnitude="+String.valueOf(newVal);
                //what happens when you pick a number
            }

        });

        NumberPicker numberPicker2 = findViewById(R.id.number_picker2);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(10);
        numberPicker2.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });

        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                maxMagnitudeString = "&maxmagnitude="+String.valueOf(newVal);
                //what happens when you pick a number
            }

        });

        Button setButton = findViewById(R.id.set);

        setButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Tsunamis section
                if(tsunamiFlagStart == 1 && tsunamiFlagEnd == 0) {
                    TsunamiFragment.tsunamiFragmentFlag = 0;
                    tsunamiFragment.setStartAndEndDate(tsunamiStartDateString,"");
                    tsunamiFlagStart = 0;
                } else if(tsunamiFlagStart == 0 && tsunamiFlagEnd == 1){
                    TsunamiFragment.tsunamiFragmentFlag = 0;
                    tsunamiFragment.setStartAndEndDate("",tsunamiEndDateString);
                    tsunamiFlagEnd = 0;
                }else if(tsunamiFlagStart == 1 && tsunamiFlagEnd == 1){
                    TsunamiFragment.tsunamiFragmentFlag = 0;
                    tsunamiFragment.setStartAndEndDate(tsunamiStartDateString,tsunamiEndDateString);
                    tsunamiFlagStart = 0;
                    tsunamiFlagEnd = 0;
                }else if(tsunamiFlagStart == 0 && tsunamiFlagEnd == 0){
                    TsunamiFragment.tsunamiFragmentFlag = 1;
                }

                //Earthquakes section
                EarthquakeFragment.flag2 = 0;

                String adjustedDate = "";

                if(flagDate == 1) {
                    adjustedDate = setYear+"-"+setMonth+"-"+setDay;
                    EarthquakeFragment.adjustedStartDate = adjustedDate;
                }else if(flagDate == 0) {
                    EarthquakeFragment.adjustedStartDate = EarthquakeFragment.todayDate;
                }

                EarthquakeFragment.minMagnitude = minMagnitudeString;
                EarthquakeFragment.maxMagnitude = maxMagnitudeString;

            }
        });


        mSwitch = findViewById(R.id.switch_id);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    EarthquakeFragment.minMagnitude = minMagnitudeString;
                    EarthquakeFragment.maxMagnitude = maxMagnitudeString;
                    EarthquakeFragment.adjustedStartDate = EarthquakeFragment.todayDate;
                    flagDate = 0;
                    EarthquakeFragment.flag2 = 1;
                    TsunamiFragment.tsunamiFragmentFlag = 1;
                    earthquakeDate.setText("Select a date");
                }
            }
        });

    }


    public String getMonth(int month){

        switch (month){
            case 1:
                return "Jan";

            case 2:
                return "Feb";

            case 3:
                return "Mar";

            case 4:
                return "Apr";

            case 5:
                return "Mar";

            case 6:
                return "Jun";

            case 7:
                return "Jul";

            case 8:
                return "Aug";

            case 9:
                return "Sep";

            case 10:
                return "Oct";

            case 11:
                return "Nov";

            case 12:
                return "Dec";

        }
        return null;
    }

}
