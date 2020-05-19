package com.shivek.readandwritejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
  EditText e1;
  TextView t1 ;
  Button b1 , b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.e1);
        b1= findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        t1 = findViewById(R.id.t1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                        File f = ContextCompat.getDataDir(MainActivity.this);
                        File p = new File(f,"abc.txt");

                try {
                    FileInputStream I = new FileInputStream(p);
                    InputStreamReader ir = new InputStreamReader(I);
                    BufferedReader bf = new BufferedReader(ir);


                    StringBuilder pi = new StringBuilder();
                    String text = bf.readLine();

                    while(text != null)
                    {
                            pi.append(text);
                            text = bf.readLine();
                    }

                    String t= pi.toString();
                    t1.setText(t);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = e1.getText().toString();
                File f = ContextCompat.getDataDir(MainActivity.this);
                        File p = new File(f , "abc.txt");



                try {
                    FileOutputStream fos = new FileOutputStream(p, true) ;
                    fos.write(text.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
