package com.ideasoft.dailyexpense.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ideasoft.dailyexpense.DBHandler;
import com.ideasoft.dailyexpense.ExpanseModal;
import com.ideasoft.dailyexpense.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DashboardFragment extends Fragment {

    EditText toDate,fromDate;
    ImageView imageAmount;
    TextView amount;
    DBHandler dbHandler;


    @SuppressLint({"Range", "SetTextI18n"})
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Dashboard");
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);



        toDate = view.findViewById(R.id.toDate);
        fromDate = view.findViewById(R.id.fromDate);
        imageAmount = view.findViewById(R.id.imageAmount);
        amount = view.findViewById(R.id.amount);


        dbHandler = new DBHandler(getContext());


        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDate();
            }
        });

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDate();
            }
        });


        Cursor cursor1 = dbHandler.calculateAllAmount();
        String result = "";
        if (cursor1.moveToNext()){
            result = String.valueOf((cursor1.getInt(cursor1.getColumnIndex("TOTALAMOUNT"))));

        }

        amount.setText("BDT  "+result);


        imageAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor11 = dbHandler.showAmount(String.valueOf(fromDate.getText()),String.valueOf(toDate.getText()));
                String getResult = "";
                if (cursor11.moveToNext()){
                    getResult = String.valueOf((cursor11.getInt(cursor11.getColumnIndex("MYTOTAL"))));
                    amount.setText("BDT  "+getResult);
                }
            }

        });


       return view;
   }

    private void fromDate() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker =
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(final DatePicker view, final int year, final int month,
                                          final int dayOfMonth) {

                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year, month, dayOfMonth);
                        String dateString = sdf.format(calendar.getTime());

                        fromDate.setText(dateString); // set the date
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

    private void toDate() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker =
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(final DatePicker view, final int year, final int month,
                                          final int dayOfMonth) {

                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year, month, dayOfMonth);
                        String dateString = sdf.format(calendar.getTime());

                        toDate.setText(dateString); // set the date
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



}
