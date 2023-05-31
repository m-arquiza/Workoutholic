package com.example.workoutholicapp.backend.Logger;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;

import java.io.Serializable;

public class Log implements Serializable {
    private String date;
    private Exercise exercise;

    public Log(String date, Exercise exercise) {
        this.date = date;
        this.exercise = exercise;
    }

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
