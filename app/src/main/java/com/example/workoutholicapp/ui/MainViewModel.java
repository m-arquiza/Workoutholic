package com.example.workoutholicapp.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> foodStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>(150);

    public void shopFoodClick() {
        Integer food = foodStorage.getValue();
        Integer money = totalMoney.getValue();
        if (money >= 20) {
            foodStorage.setValue(food + 1);
            totalMoney.setValue(money - 20);
        }
    }
    public void buddyFoodClick() {
        Integer food = foodStorage.getValue();
        if (food != 0) {
            foodStorage.setValue(food-1);
        }
    }

    public void shopWaterClick() {
        Integer water = waterStorage.getValue();
        Integer money = totalMoney.getValue();
        if (money >= 10) {
            waterStorage.setValue(water + 1);
            totalMoney.setValue(money - 10);
        }
    }
    public void buddyWaterClick() {
        Integer water = waterStorage.getValue();
        if (water != 0) {
            waterStorage.setValue(water-1);
        }
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
}

