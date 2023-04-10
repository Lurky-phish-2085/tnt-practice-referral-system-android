package com.example.rewardsbackend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.rewardsbackend.DBcomponents.MainViewModel;
import com.example.rewardsbackend.DBcomponents.ReferenceKey;
import com.example.rewardsbackend.DBcomponents.Transaction;
import com.example.rewardsbackend.DBcomponents.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel mvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("OnCreate");
        mvm = new ViewModelProvider(this).get(MainViewModel.class);
        mvm.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                System.out.println("Test");
                for(User i : users) {
                    System.out.println("Username :" + i.getName());
                    System.out.println("Password :" + i.getPassword());
                    System.out.println("------------------- References -------------------");
                    mvm.getReferenceByUserKey(i.getId()).observe(MainActivity.this, referenceKeys -> {
                        for(ReferenceKey j : referenceKeys){
                            System.out.println("Reference code: " + j.getCode());
                        }
                    });
                    System.out.println("-------------------Transactions-------------------");
                    mvm.getTransactionByUser(i.getId()).observe(MainActivity.this, transactions -> {
                        for(Transaction k : transactions){
                            System.out.println("Transaction source: " + k.getSource());
                            System.out.println("Transaction amount: " + k.getAmount());
                        }
                    });
                }
            }
        });
    }
}
//                users -> {
//            for(User i : users){
//                System.out.println("Username :" + i.getName());
//                System.out.println("Password :" + i.getPassword());
//                System.out.println("------------------- References -------------------");
//                mvm.getReferenceByUserKey(i.getId()).observe(this, referenceKeys -> {
//                    for(ReferenceKey j : referenceKeys){
//                        System.out.println("Reference code: " + j.getCode());
//                    }
//                });
//                System.out.println("-------------------Transactions-------------------");
//                mvm.getTransactionByUser(i.getId()).observe(this, transactions -> {
//                    for(Transaction k : transactions){
//                        System.out.println("Transaction source: " + k.getSource());
//                        System.out.println("Transaction amount: " + k.getAmount());
//                    }
//                });
//            }
//        });
