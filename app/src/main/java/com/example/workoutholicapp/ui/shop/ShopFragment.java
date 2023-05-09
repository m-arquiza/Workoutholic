package com.example.workoutholicapp.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.R;
import com.example.workoutholicapp.databinding.FragmentShopBinding;

public class ShopFragment extends Fragment {

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

    private FragmentShopBinding binding;
    private ShopViewModel shopViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /* Gets and stores views - binds viewmodel to xml fragment */
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding = FragmentShopBinding.bind(root);

        /* On click, updates food and water variable inside ShopViewModel respectively */
        ImageButton foodButton = root.findViewById(R.id.food_button);
        ImageButton  waterButton = root.findViewById(R.id.water_button);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.onFoodClick();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.onWaterClick();
            }
        });
        shopViewModel.moneyCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer amount) {
                TextView money = getView().findViewById(R.id.coin_text4);
                money.setText(String.format("%d coins", amount));
            }
        });
        // Updates text displayed to user about food/water bought
        shopViewModel.foodCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer foodBought) {
                TextView food = getView().findViewById(R.id.food_storage);
                food.setText("food in storage: " + foodBought);
            }
        });

        shopViewModel.waterCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer waterBought) {
                TextView food = getView().findViewById(R.id.water_storage);
                food.setText("water in storage: " + waterBought);
            }
        });

        /* On click, "sets" auto food/water/happy on activate (just grays out/disables button
        *  and pops up a text to user */

        ImageButton autoFood = root.findViewById(R.id.auto_food);
        ImageButton autoWater = root.findViewById(R.id.auto_water);
        ImageButton autoHappy = root.findViewById(R.id.auto_happy);

        autoFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoFood.setEnabled(false);
                autoFood.setAlpha(0.5f);
                TextView food = getView().findViewById(R.id.af_activate);
                food.setText("auto feed on!");
            }
        });
        autoWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoWater.setEnabled(false);
                autoWater.setAlpha(0.5f);
                TextView water = getView().findViewById(R.id.aw_activate);
                water.setText("auto hydrate on!");
            }
        });
        autoHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoHappy.setEnabled(false);
                autoHappy.setAlpha(0.5f);
                TextView happy = getView().findViewById(R.id.ah_activate);
                happy.setText("auto play on!");
            }
        });

        /* On click, "buys" toy (grays it out. that's all for rn) */

        ImageButton toy1 = root.findViewById(R.id.toy1);
        ImageButton toy2 = root.findViewById(R.id.toy2);
        ImageButton toy3 = root.findViewById(R.id.toy3);

        toy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toy1.setEnabled(false);
                toy1.setAlpha(0.5f);
            }
        });
        toy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toy2.setEnabled(false);
                toy2.setAlpha(0.5f);
            }
        });
        toy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toy3.setEnabled(false);
                toy3.setAlpha(0.5f);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}