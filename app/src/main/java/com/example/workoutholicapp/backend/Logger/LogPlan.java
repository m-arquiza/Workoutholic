package com.example.workoutholicapp.backend.Logger;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;
import com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan;

import java.util.Iterator;
import java.util.List;

public class LogPlan {
    private List<Log> Loglist;
    public static void main(String[] args){

    }
    public void LogExercise(Exercise exercise, String date){
        Log log = new Log();
        log.setExercise(exercise);
        log.setDate(date);
        Loglist.add(log);
    }

    public Log ViewLog(String date){
        Iterator<Log> itr = Loglist.iterator();
        Log output =null;
        while(itr.hasNext()){
            if(itr.next().getDate().equals(date)){
                output = itr.next();
            }
        }
        return output;
    }
}
