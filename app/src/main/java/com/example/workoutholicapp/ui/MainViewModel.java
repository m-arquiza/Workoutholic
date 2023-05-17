package com.example.workoutholicapp.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> foodStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>(150);


    private MutableLiveData<boolean[]> toyStorage = new MutableLiveData<>(new boolean[6]);


    public void shopFoodClick() { // increases inventory and decreases money
        Integer food = foodStorage.getValue();
        Integer money = totalMoney.getValue();
        if (money >= 20) {
            foodStorage.setValue(food + 1);
            totalMoney.setValue(money - 20);
        }
    }
    public void buddyFoodClick() { // decreases inventory
        Integer food = foodStorage.getValue();
        if (food != 0) {
            foodStorage.setValue(food-1);
        }
    }

    public void shopWaterClick() { // increases inventory and decreases money
        Integer water = waterStorage.getValue();
        Integer money = totalMoney.getValue();
        if (money >= 10) {
            waterStorage.setValue(water + 1);
            totalMoney.setValue(money - 10);
        }
    }
    public void buddyWaterClick() { // decreases inventory
        Integer water = waterStorage.getValue();
        if (water != 0) {
            waterStorage.setValue(water-1);
        }
    }

    public boolean buyToyClick(int toyNum) {
        Integer money = totalMoney.getValue();
        int index = toyNum-1;
        boolean[] toys = toyStorage.getValue();
        if (money >= 50 && !toys[index]) {
            toys[index] = true;
            toyStorage.setValue(toys);
            totalMoney.setValue(money - 50);
            return true;
        }
        return false;
    }

    public LiveData<Integer> foodCount() {
        return foodStorage;
    }

    public LiveData<Integer> waterCount() {
        return waterStorage;
    }

    public LiveData<Integer> moneyAmount() {
        return totalMoney;
    }

    public LiveData<boolean[]> toys() {
        return toyStorage;
    }

}

