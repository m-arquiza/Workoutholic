package com.example.workoutholicapp.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.workoutholicapp.MainActivity;
import com.example.workoutholicapp.R;
import com.example.workoutholicapp.databinding.FragmentShopBinding;
import com.example.workoutholicapp.ui.MainViewModel;


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

    private MainViewModel mainViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();
    }

    private boolean autoFoodOn = false;
    private boolean autoWaterOn = false;
    private boolean autoHappyOn = false;
    private boolean toy1bought = false;
    private boolean toy2bought = false;
    private boolean toy3bought = false;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /* Gets and stores views - binds viewmodel to xml fragment */
        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding = FragmentShopBinding.bind(root);

        /* On click, updates food and water variable inside ShopViewModel respectively */
        ImageButton foodButton = root.findViewById(R.id.food_button);
        ImageButton waterButton = root.findViewById(R.id.water_button);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.shopFoodClick();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.shopWaterClick();
            }
        });



        mainViewModel.foodCount().observe(getViewLifecycleOwner(), value -> {
            TextView food = getView().findViewById(R.id.food_storage);
            food.setText("food in storage: " + value);
        });

        mainViewModel.waterCount().observe(getViewLifecycleOwner(), value -> {
            TextView water = getView().findViewById(R.id.water_storage);
            water.setText("water in storage: " + value);
        });

        mainViewModel.moneyAmount().observe(getViewLifecycleOwner(), value -> {
            TextView money = getView().findViewById(R.id.coin_text2);
            money.setText(value + " coins");
        });


        ImageButton autoFood = root.findViewById(R.id.auto_food);
        ImageButton autoWater = root.findViewById(R.id.auto_water);
        ImageButton autoHappy = root.findViewById(R.id.auto_happy);

        autoFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoFoodOn = true;
                autoFood.setEnabled(false);
                autoFood.setAlpha(0.5f);
                TextView food = getView().findViewById(R.id.af_activate);
                food.setText("auto feed on!");
            }
        });

        autoWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoWaterOn = true;
                autoWater.setEnabled(false);
                autoWater.setAlpha(0.5f);
                TextView food = getView().findViewById(R.id.aw_activate);
                food.setText("auto hydrate on!");
            }
        });

        autoHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoHappyOn = true;
                autoHappy.setEnabled(false);
                autoHappy.setAlpha(0.5f);
                TextView food = getView().findViewById(R.id.ah_activate);
                food.setText("auto play on!");
            }
        });

        /* On click, "buys" toy (grays it out. that's all for rn) */

        ImageButton toy1 = root.findViewById(R.id.toy1);
        ImageButton toy2 = root.findViewById(R.id.toy2);
        ImageButton toy3 = root.findViewById(R.id.toy3);

        toy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toy1bought = true;
                toy1.setEnabled(false);
                toy1.setAlpha(0.5f);
            }
        });
        toy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toy2bought = true;
                toy2.setEnabled(false);
                toy2.setAlpha(0.5f);
            }
        });
        toy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toy3bought = true;
                toy3.setEnabled(false);
                toy3.setAlpha(0.5f);
            }
        });

        if (savedInstanceState != null) {
            autoFoodOn = savedInstanceState.getBoolean("autoFoodOn");
            if (autoFoodOn) {
                autoFood.setEnabled(false);
                autoFood.setAlpha(0.5f);
                TextView food = root.findViewById(R.id.af_activate);
                food.setText("auto feed on!");
            }

            autoWaterOn = savedInstanceState.getBoolean("autoWaterOn");
            if (autoWaterOn) {
                autoWater.setEnabled(false);
                autoWater.setAlpha(0.5f);
                TextView food = root.findViewById(R.id.aw_activate);
                food.setText("auto hydrate on!");
            }

            autoHappyOn = savedInstanceState.getBoolean("autoHappyOn");
            if (autoHappyOn) {
                autoHappy.setEnabled(false);
                autoHappy.setAlpha(0.5f);
                TextView food = root.findViewById(R.id.ah_activate);
                food.setText("auto play on!");
            }

            toy1bought = savedInstanceState.getBoolean("toy1bought");
            if (toy1bought) {
                toy1bought = true;
                toy1.setEnabled(false);
                toy1.setAlpha(0.5f);
            }

            toy2bought = savedInstanceState.getBoolean("toy2bought");
            if (toy2bought) {
                toy2bought = true;
                toy2.setEnabled(false);
                toy2.setAlpha(0.5f);
            }

            toy3bought = savedInstanceState.getBoolean("toy3bought");
            if (toy3bought) {
                toy3bought = true;
                toy3.setEnabled(false);
                toy3.setAlpha(0.5f);
            }
        }
        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("autoFoodOn", autoFoodOn);
        outState.putBoolean("autoWaterOn", autoWaterOn);
        outState.putBoolean("autoHappyOn", autoHappyOn);
        outState.putBoolean("toy1bought", toy1bought);
        outState.putBoolean("toy2bought", toy2bought);
        outState.putBoolean("toy3bought", toy3bought);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

