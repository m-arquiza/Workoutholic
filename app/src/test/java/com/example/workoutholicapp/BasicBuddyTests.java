package com.example.workoutholicapp;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.workoutholicapp.ui.MainViewModel;

import org.junit.Rule;
import org.junit.Test;

public class BasicBuddyTests {

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
    public void testFoodEatAfterBuy() {
        MainViewModel vm = new MainViewModel();
        for (int i = 0; i < 3; i++) {
            vm.shopFoodClick();
        }
        for (int i = 0; i < 2; i++) {
            vm.buddyFoodClick();
        }
        int food = vm.foodCount().getValue();
        assertEquals(1, food);
    }

    @Test
    public void testWaterDrinkAfterBuy() {
        MainViewModel vm = new MainViewModel();
        for (int i = 0; i < 3; i++) {
            vm.shopWaterClick();
        }
        for (int i = 0; i < 2; i++) {
            vm.buddyWaterClick();
        }
        int water = vm.waterCount().getValue();
        assertEquals(1, water);
    }

    @Test
    public void testFoodEatNoBuy() {
        MainViewModel vm = new MainViewModel();
        vm.buddyFoodClick();
        int food = vm.foodCount().getValue();
        assertEquals(0, food);
    }

    @Test
    public void testWaterDrinkNoBuy() {
        MainViewModel vm = new MainViewModel();
        vm.buddyWaterClick();
        int water = vm.waterCount().getValue();
        assertEquals(0, water);
    }
}