package com.example.workoutholicapp.ui.buddy;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workoutholicapp.ui.shop.ShopViewModel;

public class BuddyViewModel extends ViewModel {

    //    private int foodBought = 0;
//    private int waterBought = 0;
    private MutableLiveData<Integer> foodStorage = new MutableLiveData<>(10);
    private MutableLiveData<Integer> waterStorage = new MutableLiveData<>(10);

    private static MutableLiveData<Integer> totalMoney = new MutableLiveData<>(150);

    public void onFoodClick2() {
        Log.d("food button", "food clicked");
        Integer food = foodStorage.getValue();
        Log.d("food value", food.toString());
        foodStorage.setValue(food + 1);
    }

    public void onFoodClick() {
        Log.d("food button", "food clicked!");
        Integer food = foodStorage.getValue();
        if (food != null && food != 0) {
            foodStorage.setValue(food-1);
        }
    }

    public void onWaterClick() {
        Log.d("water button", "water clicked!");
        Integer water = waterStorage.getValue();
        if (waterStorage != null && water != 0) {
            waterStorage.setValue(water - 1);
        }
    }

    public LiveData<Integer> foodCount() {
        return foodStorage;
    }

    public LiveData<Integer> waterCount() {
        return waterStorage;
    }

    public LiveData<Integer> moneyCount() {
        return totalMoney;
    }
}