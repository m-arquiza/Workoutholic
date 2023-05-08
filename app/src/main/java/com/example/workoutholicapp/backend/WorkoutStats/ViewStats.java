package com.example.workoutholicapp.backend.WorkoutStats;

import com.example.workoutholicapp.backend.Logger.Log;
import com.example.workoutholicapp.backend.Logger.LogPlan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewStats {

    public List<Log> ViewStatsViaExercise(LogPlan list, String input){
        List<Log> statList = new ArrayList<>();
        Iterator<Log> itrLog = list.ViewLog().iterator();
        while(itrLog.hasNext()){
            Log cur = itrLog.next();
            if(cur.getDate().equals(input)){
                statList.add(cur);
            }
        }
        return statList;
    }
}
