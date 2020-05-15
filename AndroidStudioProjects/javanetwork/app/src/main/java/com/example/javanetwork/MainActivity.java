package com.example.javanetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    Button b1;
    TextView t1;
    EditText e1;
  public static final String TAG = "mainactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
b1 = findViewById(R.id.get);

e1 = findViewById(R.id.edit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetext();
            }
        });
    }

    private void updatetext() {
     String text= e1.getText().toString();
          // network n = new network();
          // n.execute(text);
        networkcall(text);
    }

     void networkcall(String url)
     {
         OkHttpClient client = new OkHttpClient();
         Request request = new Request.Builder()
                 .url(url)
                 .build();
         client.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(@NotNull Call call, @NotNull IOException e) {

             }

             @Override
             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                       String result = response.body().string();
              //   ArrayList<gitusers> B = parseJSON(result);
                // Log.d(TAG ,"ABCD" +B.size());
                 //Log.d(TAG , "" +B.get(7).getLogin());
                //
                 Gson  GSON = new Gson();
                 adapterapi adapterapi = GSON.fromJson(result, com.example.javanetwork.adapterapi.class);
                 final gitadapter g = new gitadapter(adapterapi.getItems());


                 MainActivity.this.runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         RecyclerView r = findViewById(R.id.recycle);
                         r.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                         r.setAdapter(g);
                     }
                 });
             }
         });

     }
    class network extends AsyncTask<String ,Void ,String>{

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            try {
                URL u = new URL(url);
              HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection() ;
                InputStream input = urlConnection.getInputStream();
                Scanner sc = new Scanner(input);
                sc.useDelimiter("//A");
                if (sc.hasNext())
                {
                    String s = sc.next();
                    return s;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "failed to load";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<gitusers> B = parseJSON(s);
            Log.d(TAG ,"ABCD" +B.size());
            Log.d(TAG , "" +B.get(7).getLogin());
            gitadapter g = new gitadapter(B);
            RecyclerView r = findViewById(R.id.recycle);
            r.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            r.setAdapter(g);
        }
    }

   ArrayList<gitusers> parseJSON(String s)
    {
        ArrayList<gitusers> A = new ArrayList<>();

        //PARSE
        try {
            JSONObject root = new JSONObject(s);
            JSONArray items = root.getJSONArray("items");

            for (int i =0 ; i< items.length() ; i++)
            {
                JSONObject content = items.getJSONObject(i);
                String login = content.getString("login");
                Integer id = content.getInt("id");
                String avatar_url =content.getString("avatar_url");

                gitusers D = new gitusers(login,id,avatar_url);
                A.add(D);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return A;
    }
}
