package com.example.workoutholicapp.ui.shop;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workoutholicapp.ui.buddy.BuddyViewModel;


public class ShopViewModel extends ViewModel {
        /* hi ashley! some to dos that I can think of rn:
            - currently, food and water bought is stored as a variable in the shopviewmodel class.
              i'd like to be able to use it in the buddy class as well, but i'm a) not able to use
              shop vars in buddy and b) not able to call buddy component ids in shop. if you could
              fix that that'd be perf :)
            - similarly (but also not rly) when i have a toy pressed, nothing happens... i hardcoded
              a single toy in, but since there's no way for me to indicate that the user alr bought
              an item in the shop to use in the buddy fragment i didn't create any variables so same idea,
              if you can give me a way to access that information that helps
            - vitals do not move in any way. atm i have vital bars set to 80dp height, if you can
              provide me numbers such that i can change the height of the vitals according to those
              80 degrees of measurement i would be very grateful
            - so,,, shop system not implemented. not sure who's doing that, but i can't have items
              cost things if i have no money to spend LOL so money doesn't go up or down, legit
              just hardcoded as like 104 or smth
           and that's all i can think of rn
        * */

    //private MutableLiveData<Integer> foodInStorage = new MutableLiveData<>(0);
    private MutableLiveData<Integer> waterInStorage = new MutableLiveData<>(0);
    private static MutableLiveData<Integer> totalMoney = new MutableLiveData<>(150);


//    public void onFoodClick() {
//        Integer food = foodInStorage.getValue();
//        Integer money = totalMoney.getValue();
//        if (food != null && money > 10) {
//            foodInStorage.setValue(food+1);
//            totalMoney.setValue(money - 10);
//        }
//    }

    public void onWaterClick() {
        Integer water = waterInStorage.getValue();
        Integer money = totalMoney.getValue();
        if (water != null) {
            waterInStorage.setValue(water+1);
            totalMoney.setValue(money - 20);
        }
    }


//    public LiveData<Integer> foodCount() {
//        return foodInStorage;
//    }

    public LiveData<Integer> waterCount() {
        return waterInStorage;
    }

    public LiveData<Integer> moneyCount() { return totalMoney; }

}