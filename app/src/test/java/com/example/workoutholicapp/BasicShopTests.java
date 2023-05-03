package com.example.workoutholicapp;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.workoutholicapp.ui.shop.ShopViewModel;


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