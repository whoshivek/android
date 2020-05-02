package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button add;
    TextView answer;
    EditText var1 , var2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add= findViewById(R.id.button);
        answer = findViewById(R.id.textView);
        var1= findViewById(R.id.editText);
        var2= findViewById(R.id.editText2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val1= Integer.valueOf(var1.getText().toString());
                int val2 =Integer.valueOf(var2.getText().toString());
                int val3 = val1 + val2;
                answer.setText(String.valueOf(val3));
            }
        });
    }
}
