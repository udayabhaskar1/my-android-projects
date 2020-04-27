package com.example.dynamicfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListFrag extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        String meta = null;
        List<Customer> customers = null;

        Bundle bundle = getArguments();
        if (bundle != null) {
            meta = bundle.getString("meta");
            customers = (ArrayList<Customer>)bundle.getSerializable("customers");
        }

        View v = inflater.inflate(R.layout.list_frag, container, false);
        ListView list = v.findViewById(R.id.listView1);
        list.setAdapter(new SimpleAdapter(getActivity(), customers,
                R.layout.row, new String[]{ "name", "address" },
                new int[]{R.id.nameView, R.id.addrView}));

        return v;
    }
}

