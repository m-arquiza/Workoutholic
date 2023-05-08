package com.example.workoutholicapp.ui.buddy;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuddyViewModel extends ViewModel {

    private int foodBought = 0;
    private int waterBought = 0;

    public void onFoodClick() {
        Log.d("food button", "food clicked!");
        foodBought++;
    }

    public void onWaterClick() {
        Log.d("water button", "water clicked!");
        waterBought++;
    }
}