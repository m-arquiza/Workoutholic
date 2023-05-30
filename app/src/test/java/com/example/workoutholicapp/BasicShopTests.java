package com.example.workoutholicapp;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.workoutholicapp.ui.MainViewModel;

import org.junit.Rule;
import org.junit.Test;

public class BasicShopTests {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Test

    public void testNoFoodWater() {
        MainViewModel vm = new MainViewModel();
        int food = vm.foodCount().getValue();
        int water = vm.waterCount().getValue();
        assertEquals(0, food);
        assertEquals(0, water);
    }

    @Test
    public void testFoodClickEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        for (int i = 0; i < 3; i++) {
            vm.shopFoodClick();
        }
        int food = vm.foodCount().getValue();
        assertEquals(3, food);
    }

    @Test
    public void testWaterClickEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        for (int i = 0; i < 7; i++) {
            vm.shopWaterClick();
        }
        int water = vm.waterCount().getValue();
        assertEquals(7, water);
    }

    @Test
    public void testWaterClickNotEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        for (int i = 0; i < 31; i++) {
            vm.shopWaterClick();
        }
        int water = vm.waterCount().getValue();
        assertEquals(30, water);
    }

    @Test
    public void testFoodClickNotEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        for (int i = 0; i < 16; i++) {
            vm.shopFoodClick();
        }
        int food = vm.foodCount().getValue();
        assertEquals(15, food);
    }

}