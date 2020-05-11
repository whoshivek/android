package com.example.listviewjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    String [] name = new String[]
            {
                    "A","Q" , "E" , "T", "Y"
            };

ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 l1 = findViewById(R.id.lsv);

        ArrayAdapter<String> NAME= new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
            name

        );

        l1.setAdapter(NAME);




    }
}
