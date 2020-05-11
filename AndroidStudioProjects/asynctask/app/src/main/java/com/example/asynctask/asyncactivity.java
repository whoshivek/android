package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class asyncactivity extends AppCompatActivity {
    public static final String TAG= "async";

    Button b1 , b2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyncactivity);

        b1 = findViewById(R.id.bt1);
        t1 = findViewById(R.id.textt);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abc a = new abc();
                a.execute(5);
            }
        });

        b2 = findViewById(R.id.random);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                t1.setText(String.valueOf(r.nextInt(200)));
            }
        });


    }
    class abc extends AsyncTask<Integer  , Integer , Void>
    {

        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d(TAG ,"start");
            int n = integers[0];
            for (int i =0 ; i< n; i++)
            {
                waitsec();
                publishProgress(i);


            }
            waitnsec(n);
            Log.d(TAG ,"END");
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            t1.setText(String.valueOf(values[0]));
        }

        void waitsec()
        {
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis()<time + 1000);
        }

        void waitnsec(int n)
        {
            for (int i=0 ; i<n ; i++)
            waitsec();
        }
    }
}
