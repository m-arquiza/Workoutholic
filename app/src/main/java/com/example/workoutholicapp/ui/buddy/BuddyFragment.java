package com.example.workoutholicapp.ui.buddy;

import android.media.Image;
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
    private static boolean[] toyOn = new boolean[6];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBuddyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton foodButton = root.findViewById(R.id.dogfood_button);
        ImageButton waterButton = root.findViewById(R.id.dogwater_button);

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.buddyFoodClick();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.buddyWaterClick();
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

        // updates display in dog inventory
        mainViewModel.toys().observe(getViewLifecycleOwner(), value -> {
            for(int i = 0; i < value.length; i++) {
                if(value[i]) {
                    if(i == 0){
                        ImageButton dogtoy = root.findViewById(R.id.dog_toy1);
                        dogtoy.setImageResource(R.drawable.toy_ball);
                        dogtoy.setEnabled(true);
                    }
                    if(i == 1){
                        ImageButton dogtoy = root.findViewById(R.id.dog_toy2);
                        dogtoy.setImageResource(R.drawable.toy_bone);
                        dogtoy.setEnabled(true);
                    }
                    if(i == 2){
                        ImageButton dogtoy = root.findViewById(R.id.dog_toy3);
                        dogtoy.setImageResource(R.drawable.toy_stick);
                        dogtoy.setEnabled(true);
                    }
                }
            }
        });


        ImageButton ball = root.findViewById(R.id.dog_toy1);
        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView toy = getView().findViewById(R.id.toy);
                if(checkAndChangeToy(0)){
                    toy.setImageResource(R.drawable.toy_ball);
                } else {
                    if (toy.getAlpha() == 0.0f) {
                        toyOn[0] = true;
                        toy.setImageResource(R.drawable.toy_ball);
                        toy.setAlpha(1.0f);
                    } else {
                        toyOn[0] = false;
                        toy.setAlpha(0.0f);
                    }
                }
            }
        });

        ImageButton bone = root.findViewById(R.id.dog_toy2);
        bone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView toy = getView().findViewById(R.id.toy);
                if(checkAndChangeToy(1)){
                    toy.setImageResource(R.drawable.toy_bone);
                } else {
                    if (toy.getAlpha() == 0.0f) {
                        toyOn[1] = true;
                        toy.setAlpha(1.0f);
                        toy.setImageResource(R.drawable.toy_bone);
                    } else {
                        toyOn[1] = false;
                        toy.setAlpha(0.0f);
                    }
                }
            }
        });

        ImageButton stick = root.findViewById(R.id.dog_toy3);
        stick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView toy = getView().findViewById(R.id.toy);
                if(checkAndChangeToy(2)){
                    toy.setImageResource(R.drawable.toy_stick);
                } else {
                    if (toy.getAlpha() == 0.0f) {
                        toyOn[2] = true;
                        toy.setAlpha(1.0f);
                        toy.setImageResource(R.drawable.toy_stick);
                    } else {
                        toyOn[2] = false;
                        toy.setAlpha(0.0f);
                    }
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

    public static boolean checkAndChangeToy(int index) {
        for (int i = 0; i < toyOn.length; i++) {
            if (i != index && toyOn[i]) {
                toyOn[i] = false;
                return true;
            }
        }
        return false;
    }
}

