package com.example.workoutholicapp.backend.Logger;

import com.example.workoutholicapp.backend.ViewWorkout.Exercise;
import com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogPlan {
    private List<Log> Loglist = new ArrayList<>();

    /**
     * Adds the exercise and the date to a list of all logs
     *
     * @param  exercise the exercise that will be logged
     * @param  date the current date
     */
    public void LogExercise(Exercise exercise, String date){
        Log log = new Log(date, exercise);
        Loglist.add(log);
    }

    /**
     * Loops through plan and add each exercise and its date to
     * the overall log list
     *
     * @param  exercise the exercise list that will be logged
     * @param  date the current date
     */
    public void LogWorkoutPlan(List<Exercise> exercise, String date){
        Iterator<Exercise> itr = exercise.iterator();
        while(itr.hasNext()) {
            Exercise cur = itr.next();
            Log log = new Log(date, cur);
            Loglist.add(log);
        }
    }

    /**
     * Searches through the list of all logs based on the date
     *
     * @param  date the date of logs you are looking for
     * @return the log that coordinates with that date
     */
    public Log ViewLogByDate(String date){
        Iterator<Log> itr = Loglist.iterator();
        Log output =null;
        while(itr.hasNext()){
            if(itr.next().getDate().equals(date)){
                output = itr.next();
            }
        }
        return output;
    }

    /**
     * Outputs the current LogList and its contents
     *
     * @return the log list
     */
    public List<Log> ViewLog(){
        return Loglist;
    }
}
