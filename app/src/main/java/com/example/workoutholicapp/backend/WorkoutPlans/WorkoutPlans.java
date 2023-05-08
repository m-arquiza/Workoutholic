package com.example.workoutholicapp.backend.WorkoutPlans;

import static com.example.workoutholicapp.backend.ViewWorkout.ViewWorkout.JSONmapper;
import static com.example.workoutholicapp.backend.ViewWorkout.ViewWorkout.View;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorkoutPlans {
    private static List<WorkoutPlan> workoutplans = new ArrayList<>();
    public static void main(String[] args){
        List<Exercise> list = JSONmapper(View("chest"));
        Create(list,"chest workouts");
        List<Exercise> test = Choose("chest workouts");
        System.out.println(test.size());
        Iterator<Exercise> itr = test.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next().getName());
        }
    }
    public static void Create(List<Exercise> inputList, String name){
        WorkoutPlan plan = new WorkoutPlan();
        plan.setList(inputList);
        plan.setName(name);
        workoutplans.add(plan);
    }
    public static List<Exercise> Choose(String input){
        Iterator<WorkoutPlan> itr = workoutplans.iterator();
        List<Exercise> output =null;
        while(itr.hasNext()){
            WorkoutPlan next = itr.next();
            if(next.getName().equals(input)){
                output = next.getList();
            }
        }
        return output;
    }
}
