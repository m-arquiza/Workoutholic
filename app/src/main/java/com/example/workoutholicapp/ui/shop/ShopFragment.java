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

    private FragmentShopBinding binding;
    private ShopViewModel shopViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding = FragmentShopBinding.bind(root);

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