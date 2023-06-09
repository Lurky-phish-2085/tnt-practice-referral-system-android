package xyz.lurkyphish2085.tntpracticereferralsystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;
import java.util.Objects;

import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.MainViewModel;
import xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents.Transaction;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    // ToDo: Setup the recyclerview

    List<Transaction> transactionList;

    NavController navController;
    Button gotoInput;
    TransactionAdapter adapter;
    MainViewModel mainViewModel;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.transaction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.getTransactionByUser(1).observe(getViewLifecycleOwner(), new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                // ToDo: KEY USER AND UPDATE ADAPTER STUFF
                System.out.println(transactions.size());
                transactionList = transactions;
                adapter.setTransactionList(transactions);
            }
        });

        navController = NavHostFragment.findNavController(this);
        gotoInput = view.findViewById(R.id.goto_input_btn);
        gotoInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == gotoInput.getId()) {
            // Todo: HARDCODED USERKEY
            Bundle bundle = new Bundle();
            bundle.putInt("user-Key", 1);
            navController.navigate(R.id.action_dashboardFragment_to_inputFragment, bundle);
        }
    }
}