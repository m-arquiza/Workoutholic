package com.example.workoutholicapp.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> foodStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>(200);


    private MutableLiveData<boolean[]> toyStorage = new MutableLiveData<>(new boolean[3]);

    private MutableLiveData<boolean[]> autos = new MutableLiveData<>(new boolean[3]);

    private MutableLiveData<boolean[][]> hats = new MutableLiveData<>(new boolean[9][2]);

    public MainViewModel() {
        boolean[][] h = hats.getValue();
        h[0][1] = true;
        hats.setValue(h);
    }
    private int foodPrice = 10;
    private int waterPrice = 5;

    public void shopFoodClick() { // increases inventory and decreases money
        Integer food = foodStorage.getValue();
        Integer money = totalMoney.getValue();
        if (money >= foodPrice) {
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
        if (money >= waterPrice) {
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
            if (toyNum == 1) {
                totalMoney.setValue(money - 20);
            } else {
                totalMoney.setValue(money - 50);
            }
            return true;
        }
        return false;
    }


    public boolean buyAuto(int autoNum) {
        Integer money = totalMoney.getValue();
        int index = autoNum - 1;
        boolean[] auto = autos.getValue();
        if (money >= 100 && !auto[index]) {
            auto[index] = true;
            autos.setValue(auto);
            totalMoney.setValue(money - 100);
            return true;
        }
        return false;
    }


    public boolean buyHat(int hatNum) {
        Integer money = totalMoney.getValue();
        int index = hatNum;
        boolean[][] hat = hats.getValue();

        // if hat already bought
        if(hat[index][0] || index == 0) {
            Log.d("buyHat","hat alr bought:" + Arrays.deepToString(hats().getValue()));
            switchHat(index);
            return true;
        }

        if (money >= 50) {
            hat[index][0] = true;
            hats.setValue(hat);
            totalMoney.setValue(money - 50);
            switchHat(index);
            return true;
        }
        return false;
    }

    private void switchHat(int hatNum) {
        boolean[][] hat = hats.getValue();
        for(int i = 0; i < hat.length; i++) {
            if(hat[i][1]) {
                hat[i][1] = false;
            }
        }
        hat[hatNum][1] = true;
        hats.setValue(hat);
        Log.d("switchHat","switching hat" + Arrays.deepToString(hats().getValue()));
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

    public LiveData<boolean[]> autoList() {
        return autos;
    }

    public void setMoney(int num) { // mostly for testing purposes
        totalMoney.setValue(num);
    }

    public LiveData<boolean[]> toys() {
        return toyStorage;
    }


    public LiveData<boolean[][]> hats() {
        return hats;
    }
}
