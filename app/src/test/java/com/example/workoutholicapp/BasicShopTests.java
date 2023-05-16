package com.example.workoutholicapp;

import static org.junit.Assert.assertEquals;

import com.example.workoutholicapp.ui.MainViewModel;

import org.junit.Test;


public class BasicShopTests {

    @Test
    public void testFoodClick() {
        MainViewModel vm = new MainViewModel();
        vm.onFoodClick(true);
        vm.onFoodClick(true);
        vm.onFoodClick(true);
        assertEquals(3, vm.foodCount());
    }

    @Test
    public void testWaterClick() {
        MainViewModel vm = new MainViewModel();
        vm.onFoodClick(true);
        vm.onFoodClick(true);
        vm.onFoodClick(true);
        assertEquals(3, vm.waterCount());
    }
}