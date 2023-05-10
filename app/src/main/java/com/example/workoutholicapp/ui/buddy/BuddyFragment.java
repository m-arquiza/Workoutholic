package com.example.workoutholicapp.ui.buddy;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.R;
import com.example.workoutholicapp.databinding.FragmentBuddyBinding;
import com.example.workoutholicapp.ui.shop.ShopFragment;
import com.example.workoutholicapp.ui.shop.ShopViewModel;

import org.w3c.dom.Text;

public class BuddyFragment extends Fragment {

    private FragmentBuddyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuddyViewModel buddyViewModel =
                new ViewModelProvider(this).get(BuddyViewModel.class);
        ShopViewModel shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentBuddyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton foodButton = root.findViewById(R.id.dogfood_button);
        ImageButton waterButton = root.findViewById(R.id.dogwater_button);
        ImageButton foodButton2 = root.findViewById(R.id.food_button);


        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddyViewModel.onFoodClick();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddyViewModel.onWaterClick();
            }
        });

        shopViewModel.moneyCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer amount) {
                TextView money = getView().findViewById(R.id.coin_text);
                money.setText(String.format("%d coins", amount));
            }
        });


        buddyViewModel.foodCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer foodStorage) {
                        TextView food = getView().findViewById(R.id.dogfood);
                        Log.d("food on change", "method reached");
                        food.setText("x" + foodStorage);
                    }
                }
        );

        buddyViewModel.waterCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer waterStorage) {
                        TextView water = getView().findViewById(R.id.dogwater);
                        water.setText("x" + waterStorage);
                    }
                }
        );


        ImageButton ball = root.findViewById(R.id.dog_toy1);
        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView ball = getView().findViewById(R.id.toy_ball);
                if (ball.getAlpha() == 0.0f) {
                    ball.setAlpha(1.0f);
                } else {
                    ball.setAlpha(0.0f);
                }
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