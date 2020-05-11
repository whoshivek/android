package com.example.dangerouspemission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button bt1;
EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 =findViewById(R.id.button);
        e1 =findViewById(R.id.editText);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = ContextCompat.checkSelfPermission(MainActivity.this , Manifest.permission.CALL_PHONE);
                if (i==0)
                {
                    CALL();
                }
                else
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new  String[]
                                    {
                                            Manifest.permission.CALL_PHONE
                                    },
                            121
                    );

                }


            }
        });
    }
    void CALL()
    {
        String s = e1.getText().toString();
        Uri U = Uri.parse("tel:" + s);
        Intent i = new Intent(Intent.ACTION_CALL , U);
        startActivity(i);
    }
}
