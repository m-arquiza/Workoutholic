package com.example.workoutholicapp.ui.entries;

import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntriesViewModel extends ViewModel {
    private String muscle;
    private String date;


    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getMuscle() {
        return this.muscle;
    }

    public void setDate(String date) {
        this.date = "05/08/2023";
    }

    public String getDate() {
        return this.date;
    }
}