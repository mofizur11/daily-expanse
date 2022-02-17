package com.ideasoft.dailyexpense;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private DBHandler dbHandler;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        final ExpanseModal modal = expanseModalArrayList.get(position);
        holder.expanseType.setText(modal.getExpanseType());
        holder.expanseAmount.setText(modal.getExpanseAmount());
        holder.expanseDate.setText(modal.getExpanseDate());
        holder.expanseTime.setText(modal.getExpanseTime());
        holder.expenseItemId.setText("" + modal.getId());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExpenseDetailsActivity.class);
                intent.putExtra("expenseType", modal.getExpanseType());
                intent.putExtra("expenseAmount", modal.getExpanseAmount());
                intent.putExtra("expenseDate", modal.getExpanseDate());
                intent.putExtra("expenseTime", modal.getExpanseTime());
                intent.putExtra("expenseDocument", modal.getImage());
                context.startActivity(intent);
            }
        });

        holder.expenseDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setTitle("Are You Sure?");
                builder.setMessage("You are went to delete is expense");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dbHandler = new DBHandler(context);
                        dbHandler.delete_expense(modal.getId());
                        expanseModalArrayList.remove(position);
                        notifyDataSetChanged();

                        Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.expenseUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("expenseId", modal.getId());
                intent.putExtra("expenseUpdateType", modal.getExpanseType());
                intent.putExtra("expenseUpdateAmount", modal.getExpanseAmount());
                intent.putExtra("expenseUpdateDate", modal.getExpanseDate());
                intent.putExtra("expenseUpdateTime", modal.getExpanseTime());
                context.startActivity(intent);

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
        private TextView expanseType, expanseAmount, expanseDate, expanseTime, expenseItemId;
        private ImageView expenseUpdate, expenseDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            expanseType = itemView.findViewById(R.id.daily_expanse);
            expanseAmount = itemView.findViewById(R.id.daily_amount);
            expanseDate = itemView.findViewById(R.id.daily_date);
            expanseTime = itemView.findViewById(R.id.daily_time);
            expenseItemId = itemView.findViewById(R.id.expense_item_id);
            expenseUpdate = itemView.findViewById(R.id.update);
            expenseDelete = itemView.findViewById(R.id.delete);
        }
    }
}