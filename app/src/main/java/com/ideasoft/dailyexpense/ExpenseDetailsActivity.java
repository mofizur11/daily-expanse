package com.ideasoft.dailyexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExpenseDetailsActivity extends AppCompatActivity {

    TextView expenseType, expenseAmount, expenseDate, expenseTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Date: " + getIntent().getStringExtra("expenseDate"));

        expenseType = findViewById(R.id.show_expense_type);
        expenseAmount = findViewById(R.id.show_expense_amount);
        expenseDate = findViewById(R.id.show_expense_date);
        expenseTime = findViewById(R.id.show_expense_time);


        Intent intent = getIntent();
        String type = intent.getStringExtra("expenseType");
        String Amount = intent.getStringExtra("expenseAmount");
        String date = intent.getStringExtra("expenseDate");
        String time = intent.getStringExtra("expenseTime");

        expenseType.setText(type);
        expenseAmount.setText(Amount);
        expenseDate.setText(date);
        expenseTime.setText(time);
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