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
        int money = vm.moneyAmount().getValue();
        assertEquals(3, food);
        assertEquals(120, money);
    }

    @Test
    public void testWaterClickEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        for (int i = 0; i < 7; i++) {
            vm.shopWaterClick();
        }
        int water = vm.waterCount().getValue();
        int money = vm.moneyAmount().getValue();
        assertEquals(7, water);
        assertEquals(115, money);
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

    @Test
    public void testAutoEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        vm.buyAuto(1);
        int money = vm.moneyAmount().getValue();
        assertEquals(50, money);
    }

    @Test
    public void testToyEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        vm.buyToyClick(1);
        int money = vm.moneyAmount().getValue();
        assertEquals(130, money);
    }

    @Test
    public void testToyJustEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(20);
        vm.buyToyClick(1);
        int money = vm.moneyAmount().getValue();
        assertEquals(0, money);
    }

    @Test
    public void testAutoNotEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(80);
        vm.buyAuto(3);
        int money = vm.moneyAmount().getValue();
        assertEquals(80, money);
    }

    @Test
    public void testToyNotEnoughMoney() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(30);
        vm.buyToyClick(3);
        int money = vm.moneyAmount().getValue();
        assertEquals(30, money);
    }

    @Test
    public void testHatBuy() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        vm.buyHat(3);
        int money = vm.moneyAmount().getValue();
        assertEquals(100, money);
        boolean[] hat = (vm.hats().getValue())[3];
        assertEquals(true, hat[0]);
        assertEquals(true, hat[1]);
    }

    @Test
    public void testHatSwitch() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        vm.buyHat(3);
        boolean[] hat1 = (vm.hats().getValue())[3];
        assertEquals(true, hat1[0]);
        assertEquals(true, hat1[1]);
        vm.buyHat(7);
        hat1 = (vm.hats().getValue())[3];
        assertEquals(true, hat1[0]);
        assertEquals(false, hat1[1]);

    }

    @Test
    public void testRemoveHat() {
        MainViewModel vm = new MainViewModel();
        vm.setMoney(150);
        vm.buyHat(3);
        boolean[] hat1 = (vm.hats().getValue())[3];
        assertEquals(true, hat1[0]);
        assertEquals(true, hat1[1]);
        vm.buyHat(0);
        int money = vm.moneyAmount().getValue();
        assertEquals(100, money);
    }
}