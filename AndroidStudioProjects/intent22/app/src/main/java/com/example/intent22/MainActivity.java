package com.example.intent22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="mfa";

    EditText e;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        e= findViewById(R.id.editText3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = e.getText().toString();
                Uri u = Uri.parse(str);

                Intent i = new Intent(Intent.ACTION_VIEW, u);

try {
    startActivity(i);
}
                    catch (ActivityNotFoundException anfe)
                    {
                        Log.e(TAG,"no url found",anfe);
                        Toast.makeText(MainActivity.this, "sorry url not found", Toast.LENGTH_SHORT).show();
                    }


            }
        });
    }
}
