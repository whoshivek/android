package com.example.fragdynamicjava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class fruits extends Fragment {

    String [] arr = new String[]
            {
                    "a" ,"b" , "c" ,"d","E"
            };

    public fruits() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View i=inflater.inflate(R.layout.fragment_fruits, container, false);

        ArrayAdapter n;
        n = new ArrayAdapter<>(
               this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                arr
        );


        return  i;
    }
}
