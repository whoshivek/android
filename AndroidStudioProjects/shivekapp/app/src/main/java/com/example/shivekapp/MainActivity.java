package com.example.shivekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener

{
 public static final String TAG = "actlyfccl";

 Button btn2;
 Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG , "oncreate");
        setContentView(R.layout.activity_main);


        btn2 = findViewById(R.id.button2);
        btn3 =findViewById(R.id.button3);

        btn3.setOnClickListener(this);

             btn2.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Log.d(TAG, "second");
                 }
             });


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG , "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG , "resume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG , "pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG , "stop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG , "destroy");
    }

    public void onButtonclicked(View v)
    {
        Log.d(TAG , "button clicked");

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"3rd");
    }
}
