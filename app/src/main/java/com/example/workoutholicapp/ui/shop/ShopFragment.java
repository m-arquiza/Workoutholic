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

    private FragmentShopBinding binding;

    public static MainViewModel mainViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();
    }

    private boolean autoFoodOn = false;
    private boolean autoWaterOn = false;
    private boolean autoHappyOn = false;
    private boolean[] toyOn = new boolean[3];


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /* Gets and stores views - binds viewmodel to xml fragment */
        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding = FragmentShopBinding.bind(root);


        /* On click, updates food and water respectively */
        ImageButton foodButton = root.findViewById(R.id.food_button);
        ImageButton waterButton = root.findViewById(R.id.water_button);

        // updates stored food and water
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

        // updates text display in shop
        mainViewModel.foodCount().observe(getViewLifecycleOwner(), value -> {
            TextView food = getView().findViewById(R.id.food_storage);
            food.setText("food in storage: " + value);
        });

        mainViewModel.waterCount().observe(getViewLifecycleOwner(), value -> {
            TextView water = getView().findViewById(R.id.water_storage);
            water.setText("water in storage: " + value);
        });

        // updates money display
        mainViewModel.moneyAmount().observe(getViewLifecycleOwner(), value -> {
            TextView money = getView().findViewById(R.id.coin_text);
            money.setText(value + " coins");
        });


        /* On click, indicates auto-vital on/off */
        ImageButton autoFood = root.findViewById(R.id.auto_food);
        ImageButton autoWater = root.findViewById(R.id.auto_water);
        ImageButton autoHappy = root.findViewById(R.id.auto_happy);

        // displays indicator
        autoFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainViewModel.buyAuto(1)) {
                    autoFoodOn = true;
                    autoFood.setEnabled(false);
                    autoFood.setAlpha(0.5f);
                    TextView food = getView().findViewById(R.id.af_activate);
                    food.setText("auto feed on!");
                }
            }
        });

        autoWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainViewModel.buyAuto(2)) {
                    autoWaterOn = true;
                    autoWater.setEnabled(false);
                    autoWater.setAlpha(0.5f);
                    TextView food = getView().findViewById(R.id.aw_activate);
                    food.setText("auto hydrate on!");
                }
            }
        });

        autoHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainViewModel.buyAuto(3)) {
                    autoHappyOn = true;
                    autoHappy.setEnabled(false);
                    autoHappy.setAlpha(0.5f);
                    TextView food = getView().findViewById(R.id.ah_activate);
                    food.setText("auto play on!");
                }
            }
        });


        /* On click, adds toy to inventory if nonbought and enough money */
        ImageButton toy1 = root.findViewById(R.id.toy1);
        ImageButton toy2 = root.findViewById(R.id.toy2);
        ImageButton toy3 = root.findViewById(R.id.toy3);

        // adds toy to inventory
        toy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainViewModel.buyToyClick(1)) {
                    toyOn[0] = true;
                    toy1.setEnabled(false);
                    toy1.setAlpha(0.5f);
                }
            }
        });
        toy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainViewModel.buyToyClick(2)) {
                    toyOn[1] = true;
                    toy2.setEnabled(false);
                    toy2.setAlpha(0.5f);
                }
            }
        });
        toy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainViewModel.buyToyClick(3)) {
                    toyOn[2] = true;
                    toy3.setEnabled(false);
                    toy3.setAlpha(0.5f);
                }
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

            toyOn = savedInstanceState.getBooleanArray("toyOn");
            for(int i = 0; i < toyOn.length; i++) {
                if(toyOn[i]) {
                    if(i == 0){
                        toy1.setEnabled(false);
                        toy1.setAlpha(0.5f);
                    }
                    if(i == 1){
                        toy2.setEnabled(false);
                        toy2.setAlpha(0.5f);
                    }
                    if(i == 2){
                        toy3.setEnabled(false);
                        toy3.setAlpha(0.5f);
                    }

                }
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
        outState.putBooleanArray("toyOn", toyOn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
