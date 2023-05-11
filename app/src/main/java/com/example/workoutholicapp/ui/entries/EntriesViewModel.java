package com.example.workoutholicapp.ui.entries;

import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EntriesViewModel extends ViewModel {
    private String muscle;
    private String workout;
    private String rep;


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
}