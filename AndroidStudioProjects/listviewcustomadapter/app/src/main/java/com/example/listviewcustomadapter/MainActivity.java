package com.example.listviewcustomadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<teacher> teachers = teacher.get1teacherinfo();
    ListView l1;
teacheradapter teacheradapter = new teacheradapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      l1 = findViewById(R.id.listteacher);
      l1.setAdapter(teacheradapter);



    }

    class teacheradapter extends BaseAdapter{

        @Override
        public int getCount() {
         return    teachers.size();
        }

        @Override
        public teacher getItem(int position) {
            return teachers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View V = getLayoutInflater().inflate(
                    R.layout.listteacher,
                    parent ,
                    false
            );

            TextView textView1 = V.findViewById(R.id.textView);
            TextView textView2 = V.findViewById(R.id.textView2);
            textView1.setText(getItem(position).getTeacher());
            textView2.setText(getItem(position).getCourse());
            return V;
        }

    }
}
