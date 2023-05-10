package com.example.workoutholicapp.ui.buddy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.workoutholicapp.MainActivity;
import com.example.workoutholicapp.R;
import com.example.workoutholicapp.databinding.FragmentBuddyBinding;
import com.example.workoutholicapp.ui.MainViewModel;


public class BuddyFragment extends Fragment {

    private FragmentBuddyBinding binding;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) requireActivity();
        mainViewModel = activity.getFoodViewModel();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBuddyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton foodButton = root.findViewById(R.id.dogfood_button);
        ImageButton waterButton = root.findViewById(R.id.dogwater_button);
        ImageButton foodButton2 = root.findViewById(R.id.food_button);


        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.onFoodClick(false);
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.onWaterClick(false);
            }
        });

        mainViewModel.foodCount().observe(getViewLifecycleOwner(), value -> {
            TextView food = getView().findViewById(R.id.dogfood);
            food.setText("x" + value);
        });

        mainViewModel.waterCount().observe(getViewLifecycleOwner(), value -> {
            TextView water = getView().findViewById(R.id.dogwater);
            water.setText("x" + value);
        });

        mainViewModel.moneyAmount().observe(getViewLifecycleOwner(), value -> {
            TextView money = getView().findViewById(R.id.coin_text);
            money.setText(value + " coins");
        });


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