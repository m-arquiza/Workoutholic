package com.example.workoutholicapp.ui.shop;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.view.View;


public class ShopViewModel extends ViewModel {

    private MutableLiveData<Integer> foodInStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterInStorage = new MutableLiveData<>(0);


    public void onFoodClick() {
        Integer food = foodInStorage.getValue();
        if (food != null) {
            foodInStorage.setValue(food+1);
        }
    }

    public void onWaterClick() {
        Integer water = waterInStorage.getValue();
        if (water != null) {
            waterInStorage.setValue(water+1);
        }
    }

    public LiveData<Integer> foodCount() {
        return foodInStorage;
    }

    public LiveData<Integer> waterCount() {
        return waterInStorage;
    }

}