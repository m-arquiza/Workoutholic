package com.example.workoutholicapp.backend.WorkoutPlans;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;

import java.util.List;

public class WorkoutPlan {
    private String name;
    private List<Exercise> list;

    public void setName(String input){
        name = input;
    }
    public String getName(){
        return name;
    }
    public void setList(List<Exercise> input){
        list = input;
    }
    public List<Exercise> getList(){
        return list;
    }
}
