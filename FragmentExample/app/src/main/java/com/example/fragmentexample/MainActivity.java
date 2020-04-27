package com.example.fragmentexample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ListFrag.IListFrag {
    ArrayList<Customer> customers = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init data
        customers.add(new Customer("Tan", "Sentosa Cove"));
        customers.add(new Customer("Wong", "Upper Thomson"));

        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ListFrag) {
            ListFrag frag = (ListFrag) fragment;
            frag.setParent(this);
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}



