package com.example.workoutholicapp.backend.WorkoutPlans;

import static com.example.workoutholicapp.backend.ViewWorkout.ViewWorkout.JSONmapper;
import static com.example.workoutholicapp.backend.ViewWorkout.ViewWorkout.View;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorkoutPlans {
    private static List<WorkoutPlan> workoutplans = new ArrayList<>();

    /**
     * Creates a workout plan and adds that to the list of
     * all the workout plans
     *
     * @param  inputList List of exercises to be made into a workout plan
     * @param  name the name of that plan
     */
    public static void Create(List<Exercise> inputList, String name){
        WorkoutPlan plan = new WorkoutPlan();
        plan.setList(inputList);
        plan.setName(name);
        workoutplans.add(plan);
    }

    /**
     * Searches for a workout plan in the current list of plans
     * using the name of the plan
     *
     * @param  input the name of the plan you are searching for
     * @return List<Exercises> that coordinates to the name
     */
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

    /**
     * Outputs the all the workout plans so far
     *
     * @return List<WorkoutPlan> list of all plans
     */
    public List<WorkoutPlan> ViewWorkoutPlans(){
        return workoutplans;
    }
}
