package com.example.workoutholicapp.ui.shop;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import android.view.View;


public class ShopViewModel extends ViewModel {

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

    public int foodCount() {
        return foodBought;
    }

    public int waterCount() {
        return waterBought;
    }
}