package com.example.fragmentexample;

import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;

public class ListFrag extends Fragment {
    IListFrag callback;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ArrayList<Customer> customers = callback.getCustomers();

        View v = inflater.inflate(R.layout.list_frag, container, false);
        ListView list = v.findViewById(R.id.listView1);
        list.setAdapter(new SimpleAdapter(getActivity(), customers,
                R.layout.row, new String[]{ "name", "address" },
                new int[]{R.id.nameView, R.id.addrView}));

        return v;
    }

    public interface IListFrag {
        public ArrayList<Customer> getCustomers();
    }

    public void setParent(IListFrag callback) {
        this.callback = callback;
    }
}

// ArrayList<Customer> customers = ((MainActivity)getActivity()).getCustomers();
