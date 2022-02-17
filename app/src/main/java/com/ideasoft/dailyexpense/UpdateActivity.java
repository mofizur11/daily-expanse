package com.ideasoft.dailyexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {


    EditText expenseType, expenseAmount, expenseDate, expenseTime;
    Button updateBtn;
    DBHandler dbHandler;

    String type, amount, date, time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Expense");

        expenseType = findViewById(R.id.expenseType);
        expenseAmount = findViewById(R.id.expanseAmount);
        expenseDate = findViewById(R.id.expanseDate);
        expenseTime = findViewById(R.id.expanseTime);
        updateBtn = findViewById(R.id.expenseUpdate);

        dbHandler = new DBHandler(this);

        int uId = getIntent().getIntExtra("expenseId", 1);
        type = getIntent().getStringExtra("expenseUpdateType");
        amount = getIntent().getStringExtra("expenseUpdateAmount");
        date = getIntent().getStringExtra("expenseUpdateDate");
        time = getIntent().getStringExtra("expenseUpdateTime");

        expenseType.setText(type);
        expenseAmount.setText(amount);
        expenseDate.setText(date);
        expenseTime.setText(time);

        expenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate();
            }
        });

        expenseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime();
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dbHandler.update_expense(uId,expenseType.getText().toString(),expenseAmount.getText().toString(),expenseDate.getText().toString(),expenseTime.getText().toString());
                Toast.makeText(UpdateActivity.this, "Update Successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                finish();
                startActivity(intent);


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


    private void chooseTime() {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(UpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                //   expanseTime.setText(selectedHour + ":" + selectedMinute);
                String time;
                if (selectedHour >= 0 && selectedHour < 12) {
                    time = selectedHour + " : " + selectedMinute + " AM";
                } else {
                    if (selectedHour != 12) {
                        selectedHour = selectedHour - 12;
                    }
                    time = selectedHour + " : " + selectedMinute + " PM";
                }

                expenseTime.setText(time);
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