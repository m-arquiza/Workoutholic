package com.example.workoutholicapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workoutholicapp.backend.Logger.Log;
import com.example.workoutholicapp.backend.ViewWorkout.Exercise;
import com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan;

import java.util.LinkedList;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> foodStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>(200);


    private MutableLiveData<boolean[]> toyStorage = new MutableLiveData<>(new boolean[3]);

    private MutableLiveData<boolean[]> autos = new MutableLiveData<>(new boolean[3]);

    private LinkedList<com.example.workoutholicapp.backend.Logger.Log> logList = new LinkedList<>();

    private LinkedList<com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan> plansList = new LinkedList<>();

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



    public void setList(LinkedList<com.example.workoutholicapp.backend.Logger.Log> list) {
        this.logList = list;
    }

    public LinkedList<com.example.workoutholicapp.backend.Logger.Log> getList() {
        return (LinkedList<Log>)this.logList.clone();
    }

    public void setPlans(LinkedList<com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan> list) {
        this.plansList = list;
    }

    public LinkedList<com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan> getPlans() {
        return (LinkedList<WorkoutPlan>)this.plansList.clone();
    }
}