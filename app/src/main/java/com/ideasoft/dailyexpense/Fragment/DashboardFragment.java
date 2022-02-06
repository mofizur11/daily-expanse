package com.ideasoft.dailyexpense.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ideasoft.dailyexpense.DBHandler;
import com.ideasoft.dailyexpense.ExpanseModal;
import com.ideasoft.dailyexpense.R;

public class DashboardFragment extends Fragment {

    TextView amount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Dashboard");
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);


        amount = view.findViewById(R.id.bdt_amount);


        DBHandler dbHandler = new DBHandler(getContext());
        ExpanseModal expanseModal = new ExpanseModal();
        amount.setText(dbHandler.fetch_amount(expanseModal.getId()));





        return view;
    }
}
