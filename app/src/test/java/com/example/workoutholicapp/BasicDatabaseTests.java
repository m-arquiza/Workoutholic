package com.example.workoutholicapp;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


public class BasicDatabaseTests {

    @Test
    public void testInventory() {
        ArrayList<String> sampleInventory = new ArrayList<>();
        sampleInventory.add("Food");
        assertTrue(sampleInventory.contains("Food"));
        assertEquals(1, sampleInventory.size());
    }

    @Test
    public void testShopInventory() {
        ArrayList<String> sampleShop = new ArrayList<>();
        sampleShop.add("Food");
        sampleShop.add("Water");
        sampleShop.add("Toy");
        sampleShop.add("Clothes");
        sampleShop.add("Furniture");
        assertTrue(sampleShop.contains("Food"));
        assertEquals(5, sampleShop.size());
    }
}