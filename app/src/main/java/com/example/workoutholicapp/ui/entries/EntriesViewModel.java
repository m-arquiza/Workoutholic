package com.example.workoutholicapp.ui.entries;

import androidx.lifecycle.ViewModel;

import com.example.workoutholicapp.backend.Logger.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class EntriesViewModel extends ViewModel {
    private String muscle;
    private String workout;
    private String rep;
    private LinkedList<com.example.workoutholicapp.backend.Logger.Log> logList = new LinkedList<>();

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getMuscle() {
        return this.muscle;
    }

    public void setWorkout(String workout) { this.workout = workout; }

    public void setRep(String rep) { this.rep = rep; }

    public String getRep() { return this.rep; }

    public String getWorkout() { return this.workout; }

    public String getDate() {
        String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        return date;
    }

    public void setList(LinkedList<com.example.workoutholicapp.backend.Logger.Log> list) {
        this.logList = list;
    }

    public LinkedList<com.example.workoutholicapp.backend.Logger.Log> getList() {
        return (LinkedList<Log>)this.logList.clone();
    }
}