package com.ideasoft.dailyexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity {

    EditText expanseType, expanseAmount, expenseDate, expanseTime;
    Button expanseAdd;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //change mainActivity action bar title
        getSupportActionBar().setTitle(getResources().getString(R.string.text_add_expense));

        expanseType = findViewById(R.id.expenseType);
        expanseAmount = findViewById(R.id.expanseAmount);
        expenseDate = findViewById(R.id.expanseDate);
        expanseTime = findViewById(R.id.expanseTime);

        expanseAdd = findViewById(R.id.expenseAdd);



        expenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseDate();
            }
        });

        expanseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseTime();
            }
        });


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(AddExpenseActivity.this);


        expanseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String expanse = expanseType.getText().toString();
                String amount = expanseAmount.getText().toString();
                String date = expenseDate.getText().toString();
                String time = expanseTime.getText().toString();


                // validating if the text fields are empty or not.
                if (expanse.isEmpty() && amount.isEmpty() && date.isEmpty()) {
                    expanseType.setError("Please Enter Your Expanse Type");
                    expanseAmount.setError("Please Enter Your Expanse Amount");
                    expenseDate.setError("Please Select Date ");
                    return;
                }

                // on below line we are calling a method to add new
                // expanse to sqlite data and pass all our values to it.
                dbHandler.addNewExpanse(expanse, amount, date, time);

                // after adding the data we are displaying a toast message.
                Toast.makeText(AddExpenseActivity.this, "Expanse added Successfully!", Toast.LENGTH_SHORT).show();


                finish();
            }
        });


    }



    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(final DatePicker view, final int year, final int month,
                                          final int dayOfMonth) {

                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year, month, dayOfMonth);
                        String dateString = sdf.format(calendar.getTime());

                        expenseDate.setText(dateString); // set the date
                    }
                }, year, month, day); // set date picker to current date

        datePicker.show();

        datePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(final DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }


    private void chooseTime(){
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(AddExpenseActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
             //   expanseTime.setText(selectedHour + ":" + selectedMinute);
                String time;
                if (selectedHour>=0 && selectedHour<12){
                     time = selectedHour + " : "+selectedMinute+ " AM";
                } else {
                    if (selectedHour != 12) {
                        selectedHour = selectedHour - 12;
                    }
                    time = selectedHour + " : " + selectedMinute + " PM";
                }

                expanseTime.setText(time);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}