package com.example.workoutholicapp.ui.entries;

import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntriesViewModel extends ViewModel {
    private String muscle;


    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getMuscle() {
        return this.muscle;
    }
}