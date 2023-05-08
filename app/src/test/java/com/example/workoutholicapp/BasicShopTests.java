package com.example.workoutholicapp;

import static org.junit.Assert.assertEquals;

import com.example.workoutholicapp.ui.shop.ShopViewModel;

import org.junit.Test;


public class BasicShopTests {

    @Test
    public void testFoodClick() {
        ShopViewModel vm = new ShopViewModel();
        vm.onFoodClick();
        vm.onFoodClick();
        vm.onFoodClick();
        assertEquals(3, vm.foodCount());
    }

    @Test
    public void testWaterClick() {
        ShopViewModel vm = new ShopViewModel();
        vm.onFoodClick();
        vm.onFoodClick();
        vm.onFoodClick();
        assertEquals(3, vm.waterCount());
    }
}