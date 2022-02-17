package com.ideasoft.dailyexpense;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExpenseDetailsActivity extends AppCompatActivity {

   private TextView expenseType, expenseAmount, expenseDate, expenseTime;
   private ImageView imageDocument;
    private String image = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        imageDocument = findViewById(R.id.imageDocument);


        Intent intent = getIntent();
        String type = intent.getStringExtra("expenseType");
        String Amount = intent.getStringExtra("expenseAmount");
        String date = intent.getStringExtra("expenseDate");
        String time = intent.getStringExtra("expenseTime");
        String imageDoc = intent.getStringExtra("expenseDocument");

        if (imageDoc != null){
            Bitmap bitmap = decodeBase64(image);
            imageDocument.setImageBitmap(bitmap);
        }

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

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}