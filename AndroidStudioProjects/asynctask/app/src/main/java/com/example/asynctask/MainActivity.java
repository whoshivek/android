package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="asnc" ;

    Button bt1;
    ConstraintLayout c1;
    String [] items = new String[]
            {
                    "hi" ,"hello ", "who" , "are" , "you " , "i" ,"u"
            };
    ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1=findViewById(R.id.button);
        c1 = findViewById(R.id.back);
        l1 = findViewById(R.id.listview);
        ArrayAdapter<String> adap = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1 ,
                items
        );
        l1.setAdapter(adap);

        bt1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Handler h = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"WE HAVE WAIT FOR 5 SEC");
                        c1.setBackgroundColor(Color.RED);
                    }
                };
                h.postDelayed(r ,5000);

               // Log.d(TAG , "onclick"+ System.currentTimeMillis());

            }
            void waitsec()
            {
                long starttime = System.currentTimeMillis();
                while (System.currentTimeMillis() < starttime + 1000);
            }

            void waitnsec(int n)
            {
                for(int i=0 ; i<n ; i++)
                {
                    waitsec();
                }
            }
        });
    }
}
