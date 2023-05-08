package com.example.workoutholicapp.backend.Logger;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;

public class Log {
    private String date;
    private Exercise exercise;

    public void setDate(String input){
        date = input;
    }
    public String getDate(){
        return date;
    }

    public void setExercise(Exercise input){
        exercise = input;
    }
    public Exercise getExercise(){
        return exercise;
    }
}
