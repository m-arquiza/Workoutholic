package com.example.workoutholicapp.backend.WorkoutPlans;

import static com.example.workoutholicapp.ui.shop.ShopFragment.mainViewModel;

import com.example.workoutholicapp.MainActivity;
import com.example.workoutholicapp.backend.Logger.Log;
import com.example.workoutholicapp.backend.ViewWorkout.Exercise;
import com.example.workoutholicapp.ui.MainViewModel;

import java.util.LinkedList;
import java.util.List;

/**
 * A workout plan consists of a list of exercises and the
 * name of the plan
 *
 */
public class WorkoutPlan {
    private String name;
    private LinkedList<Log> list;

    private String wholeplan;

    public void setName(String input){
        name = input;
    }
    public String getName(){
        return name;
    }
    public void setList(LinkedList<Log> input){
        list = input;
        String wholeplan = "";
        int indexLog = 0;
        while (indexLog < input.size()) {
            if(indexLog >= 1) {
                wholeplan += "\n";
            }
            Log element = input.get(indexLog);
            wholeplan += (indexLog + 1) + ": " + element.getExercise().getName()
                          + ": " + element.getExercise().getWeight() + " lbs"
                          + " " + element.getExercise().getNumberOfReps() + " reps";
            indexLog++;
        }

        if(indexLog == 0) {
            wholeplan += "Please Log Workout To Create Plan!";
        }
        this.wholeplan = wholeplan;
    }
    public LinkedList<Log> getList(){
        return list;
    }

    public int getWorkoutLength() {
        int result = 0;
        if(list.size() > 0) {
            for(int i=0; i<list.size(); i++) {
                if(list.get(i).getExercise().getName().length() > 25) {
                    result++;
                }
            }
        }
        return result;
    }

    public String getNameList(){
        return wholeplan;
    }

    public int getListSize() {
        return list.size();
    }
}
