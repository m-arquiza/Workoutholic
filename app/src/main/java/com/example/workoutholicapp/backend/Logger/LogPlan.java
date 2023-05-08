package com.example.workoutholicapp.backend.Logger;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;
import com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogPlan {
    private List<Log> Loglist = new ArrayList<>();
    public static void main(String[] args){

    }

    /**
     * Adds the exercise and the date to a list of all logs
     *
     * @param  exercise the exercise that will be logged
     * @param  date the current date
     */
    public void LogExercise(Exercise exercise, String date){
        Log log = new Log();
        log.setExercise(exercise);
        log.setDate(date);
        Loglist.add(log);
    }

    /**
     * Searches through the list of all logs based on the date
     *
     * @param  date the date of logs you are looking for
     * @return the log that coordinates with that date
     */
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
