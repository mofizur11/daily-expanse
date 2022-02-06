package com.ideasoft.dailyexpense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExpanseAdapter extends RecyclerView.Adapter<ExpanseAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ExpanseModal> expanseModalArrayList;
    private Context context;

    // constructor
    public ExpanseAdapter(ArrayList<ExpanseModal> expanseModalArrayList, Context context) {
        this.expanseModalArrayList = expanseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ExpanseModal modal = expanseModalArrayList.get(position);
        holder.expanseType.setText(modal.getExpanseType());
        holder.expanseAmount.setText(modal.getExpanseAmount());
        holder.expanseDate.setText(modal.getExpanseDate());
        holder.expanseTime.setText(modal.getExpanseTime());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ExpenseDetailsActivity.class);
                intent.putExtra("expenseType",modal.getExpanseType());
                intent.putExtra("expenseAmount",modal.getExpanseAmount());
                intent.putExtra("expenseDate",modal.getExpanseDate());
                intent.putExtra("expenseTime",modal.getExpanseTime());
                context.startActivity(intent);
            }
        });

        holder.expanseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return expanseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView expanseType, expanseAmount, expanseDate, expanseTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            expanseType = itemView.findViewById(R.id.daily_expanse);
            expanseAmount = itemView.findViewById(R.id.daily_amount);
            expanseDate = itemView.findViewById(R.id.daily_date);
            expanseTime = itemView.findViewById(R.id.daily_time);
        }
    }
}