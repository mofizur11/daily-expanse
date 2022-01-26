package com.ideasoft.dailyexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddExpenseActivity extends AppCompatActivity {

    EditText expanseType, expanseAmount, expanseDate, expanseTime;
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
        expanseDate = findViewById(R.id.expanseDate);
        expanseTime = findViewById(R.id.expanseTime);

        expanseAdd = findViewById(R.id.expenseAdd);


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(AddExpenseActivity.this);



        expanseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String expanse = expanseType.getText().toString();
                String amount = expanseAmount.getText().toString();
                String date = expanseDate.getText().toString();
                String time = expanseTime.getText().toString();



                // validating if the text fields are empty or not.
                if (expanse.isEmpty() && amount.isEmpty() && date.isEmpty() ) {
                    expanseType.setError("Please Enter Your Expanse Type");
                    expanseAmount.setError("Please Enter Your Expanse Amount");
                    expanseDate.setError("Please Select A Date ");
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
                expanseDate.setText("");
                expanseTime.setText("");


            }
        });



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