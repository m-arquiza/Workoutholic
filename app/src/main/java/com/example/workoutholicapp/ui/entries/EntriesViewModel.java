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
    private String weight;


    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getMuscle() {
        return this.muscle;
    }

    public void setWorkout(String workout) { this.workout = workout; }

    public void setWeight(String weight) { this.weight = weight; }

    public String getWeight() { return this.weight; }

    public String getWorkout() { return this.workout; }

    public String getDate() {
        String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        return date;
    }
}