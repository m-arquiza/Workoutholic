package com.example.workoutholicapp.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;

/*
    Class to hold shared variables across fragments and functions that interact with them.
 */
public class MainViewModel extends ViewModel {
    // Storage variables: hold signifiers for "obtaining" certain pieces of data
    private MutableLiveData<Integer> foodStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterStorage = new MutableLiveData<>(0);
    private MutableLiveData<boolean[]> toyStorage = new MutableLiveData<>(new boolean[3]);
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>(150);
    private MutableLiveData<boolean[]> autos = new MutableLiveData<>(new boolean[3]);
    private MutableLiveData<boolean[][]> hats = new MutableLiveData<>(new boolean[9][2]);

    public MainViewModel() {
        boolean[][] h = hats.getValue();
        h[0][1] = true;
        hats.setValue(h);
    }

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


    /*
        onClick function to "buy" toy item and "place" into user's storage.
        @param toyNum index number of specified toy
        @modifies toyStorage
        @effects sets toyStorage at index to true if purchase successful
        @returns true if unpurchased and purchasable, false otherwise
     */
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

    /*
        onClick function to "buy" auto-health items.
        @param autoNum index number of specified auto
        @modifies autos
        @effects sets autos at index to true if purchase successful
        @returns true if unpurchased and purchasable, false otherwise
     */
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

    /*
        onClick function to either "buy" hat item and "place" into user's storage or
        place hat onto dog.
        @param hatNum index number of specified hat
        @modifies hats
        @effects if hat is already purchased, sets "hat on" value to true at specified index
                 otherwise, sets "purchased" and "hat on" value to true at specified index
        @returns true if purchased or worn, false otherwise
     */
    public boolean buyHat(int hatNum) {
        Integer money = totalMoney.getValue();
        int index = hatNum;
        boolean[][] hat = hats.getValue();

        // if hat already bought
        if(hat[index][0] || index == 0) {
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

    /*
        Private helper function to "switch out" current hat on dog for another hat
        @param hatNum index number of specified hat
        @modifies hats
        @effects sets "hatOn" value of currently worn hat to false, sets hat at
                 specified index to true
     */
    private void switchHat(int hatNum) {
        boolean[][] hat = hats.getValue();
        for(int i = 0; i < hat.length; i++) {
            if(hat[i][1]) {
                hat[i][1] = false;
            }
        }
        hat[hatNum][1] = true;
        hats.setValue(hat);
    }

    /*
        Getter function for food count.
        @returns foodStorage
     */
    public LiveData<Integer> foodCount() {
        return foodStorage;
    }

    /*
        Getter function for water count.
        @returns waterStorage
     */
    public LiveData<Integer> waterCount() {
        return waterStorage;
    }

    /*
        Getter function for money amount.
        @returns totalMoney
     */
    public LiveData<Integer> moneyAmount() {
        return totalMoney;
    }

    /*
    Getter function for toys.
    @returns toyStorage
 */
    public LiveData<boolean[]> toys() {
        return toyStorage;
    }

    /*
        Getter function for hats.
        @returns hats
     */
    public LiveData<boolean[][]> hats() {
        return hats;
    }
}

