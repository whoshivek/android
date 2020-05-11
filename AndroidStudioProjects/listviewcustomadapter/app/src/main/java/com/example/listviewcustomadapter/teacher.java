package com.example.listviewcustomadapter;

import java.util.ArrayList;

public class teacher {
    String teacher;
    String course;

    public teacher(String teacher, String course) {
        this.teacher = teacher;
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCourse() {
        return course;
    }

    public static ArrayList<teacher> get1teacherinfo ()
    {
        ArrayList<teacher> teachers = new ArrayList<>();

        teachers.add(new teacher("shivek" ,"a"));
        teachers.add(new teacher("shiek" ,"ab"));
        teachers.add(new teacher("shvek" ,"abb"));
        teachers.add(new teacher("shik" ,"ad"));
        teachers.add(new teacher("shivk" ,"advd"));
        return teachers;

    }

}
