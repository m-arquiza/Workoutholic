package com.example.workoutholicapp.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> foodStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>(0);


    private MutableLiveData<boolean[]> toyStorage = new MutableLiveData<>(new boolean[3]);

    private MutableLiveData<boolean[]> autos = new MutableLiveData<>(new boolean[3]);

    private int foodPrice = 10;
    private int waterPrice = 5;

    public void workoutCoin() { // increases money when you log workout
        Integer money = totalMoney.getValue();
        totalMoney.setValue(money + 10);
    }

    public void shopFoodClick() { // increases inventory and decreases money
        Integer food = foodStorage.getValue();
        Integer money = totalMoney.getValue();
        if (money >= 20) {
            foodStorage.setValue(food + 1);
            totalMoney.setValue(money - foodPrice);
        }
    }
    public void buddyFoodClick() { // decreases inventory
        Integer food = foodStorage.getValue();
        if (food != 0) {
            foodStorage.setValue(food - 1);
        }
    }

    public void shopWaterClick() { // increases inventory and decreases money
        Integer water = waterStorage.getValue();
        Integer money = totalMoney.getValue();
        if (money >= 10) {
            waterStorage.setValue(water + 1);
            totalMoney.setValue(money - waterPrice);
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

    public boolean buyAuto(int autoNum) {
        Integer money = totalMoney.getValue();
        int index = autoNum-1;
        boolean[] auto = autos.getValue();
        if (money >= 100 && !auto[index]) {
            auto[index] = true;
            autos.setValue(auto);
            totalMoney.setValue(money - 100);
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

    public void moneyUpdate(int num) {
        totalMoney.setValue((totalMoney.getValue())+num);
    }

    public void setMoney(int num) { totalMoney.setValue(num); } // for testing purposes

    public LiveData<boolean[]> toys() {
        return toyStorage;
    }

}

