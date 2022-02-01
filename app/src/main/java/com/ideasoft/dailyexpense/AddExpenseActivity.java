package com.ideasoft.dailyexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity {

    EditText expanseType, expanseAmount, expanseTime;
    Button expanseAdd, expanseDate;
    private DBHandler dbHandler;
    private Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //change mainActivity action bar title
        getSupportActionBar().setTitle(getResources().getString(R.string.text_add_expense));

        expanseType = findViewById(R.id.expenseType);
        expanseAmount = findViewById(R.id.expanseAmount);
        expanseDate = findViewById(R.id.expanseDate);
        expanseTime = findViewById(R.id.expanseTime);

        expanseAdd = findViewById(R.id.expenseAdd);

        expanseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonDate_onClick(view);
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
                String time = expanseTime.getText().toString();


                // validating if the text fields are empty or not.
                if (expanse.isEmpty() && amount.isEmpty()) {
                    expanseType.setError("Please Enter Your Expanse Type");
                    expanseAmount.setError("Please Enter Your Expanse Amount");
                    expanseTime.setError("Please Select Time ");
                    Toast.makeText(AddExpenseActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // expanse to sqlite data and pass all our values to it.
                dbHandler.addNewExpanse(expanse, amount, date, time);

                // after adding the data we are displaying a toast message.
                Toast.makeText(AddExpenseActivity.this, "Expanse added Successfully!", Toast.LENGTH_SHORT).show();


                expanseType.setText("");
                expanseAmount.setText("");
                expanseTime.setText("");


            }
        });


    }


    public void buttonDate_onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.date_picker_dialog);
        AlertDialog alertDialog = builder.show();
        CalendarView calendarView = alertDialog.findViewById(R.id.calendarViewDate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendarView_onSelectedDayChange(view, year, month, dayOfMonth);
            }
        });
    }

    private void calendarView_onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = simpleDateFormat.parse(year + "-" + month + "-" + dayOfMonth + "-");

        } catch (Exception e) {
            date = null;
        }
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