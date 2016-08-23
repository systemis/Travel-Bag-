package com.example.caocsdl.datepickerandroid;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int year_x,month_x,day_x;
    static final int Dialog_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showdate(View view)
    {
        showDialog(Dialog_id);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == Dialog_id)
        {
            return new DatePickerDialog(MainActivity.this,dbPicketL,year_x,month_x,day_x);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener dbPicketL = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;month_x = monthOfYear;day_x = dayOfMonth;
            Toast.makeText(MainActivity.this,String.valueOf(year_x+" "+month_x+" "+day_x), Toast.LENGTH_SHORT).show();
        }
    };
}
