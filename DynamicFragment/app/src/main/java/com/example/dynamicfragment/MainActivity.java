package com.example.dynamicfragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Customer> customers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init data
        customers.add(new Customer("Tan", "Sentosa Cove"));
        customers.add(new Customer("Wong", "Upper Thomson"));

        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = new Bundle();
        bundle.putString("meta", "x101");
        bundle.putSerializable("customers", customers);

        Fragment frag = new ListFrag();
        frag.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.frag1, frag);
        trans.commit();
    }
}
