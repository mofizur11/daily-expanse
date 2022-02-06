package com.ideasoft.dailyexpense.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ideasoft.dailyexpense.AddExpenseActivity;
import com.ideasoft.dailyexpense.ExpanseModal;
import com.ideasoft.dailyexpense.ExpanseAdapter;
import com.ideasoft.dailyexpense.DBHandler;
import com.ideasoft.dailyexpense.R;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment {


    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<ExpanseModal> expanseModalArrayList;
    private DBHandler dbHandler;
    private ExpanseAdapter expanseAdapter;
    private RecyclerView expansesRecycler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("All Expenses");
        View rootView = inflater.inflate(R.layout.expenses_fragment, container, false);

        // initializing our all variables.
        expanseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getContext());

        // getting our expanse array
        // list from db handler class.
        expanseModalArrayList = dbHandler.readExpanse();

        // on below line passing our array list to our adapter class.
        expanseAdapter = new ExpanseAdapter(expanseModalArrayList, getContext());
        expansesRecycler = rootView.findViewById(R.id.expense_recycler);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        expansesRecycler.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        expansesRecycler.setAdapter(expanseAdapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.addExpenses);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddExpenseActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
