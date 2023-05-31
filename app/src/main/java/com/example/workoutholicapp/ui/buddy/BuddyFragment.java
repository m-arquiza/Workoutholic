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

/*
    Class to represent functions within the buddy page of the app.
 */
public class BuddyFragment extends Fragment {

    private FragmentBuddyBinding binding;
    private MainViewModel mainViewModel;

    private MainActivity activity;
    private int hungerLevel; // displayed in vitals
    private int thirstLevel; // displayed in vitals
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();
        hungerLevel = 0;
        thirstLevel = 0;
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
                // changes thirst level based on how much food dog is fed
                View hunger = getView().findViewById(R.id.hunger_bar);
                View hunger2 = getView().findViewById(R.id.hunger_bar2);
                View hunger3 = getView().findViewById(R.id.hunger_bar3);
                View hunger4 = getView().findViewById(R.id.hunger_bar4);
                if (hungerLevel < 4 && mainViewModel.foodCount().getValue() > 0) {
                    if (hungerLevel == 0) {
                        hunger4.setAlpha(1.0f);
                    } else if (hungerLevel == 1) {
                        hunger4.setAlpha(0.0f);
                        hunger3.setAlpha(1.0f);
                    } else if (hungerLevel == 2) {
                        hunger3.setAlpha(0.0f);
                        hunger2.setAlpha(1.0f);
                    } else if (hungerLevel == 3) {
                        hunger2.setAlpha(0.0f);
                        hunger.setAlpha(1.0f);
                    }
                    hungerLevel++;
                }

                mainViewModel.buddyFoodClick();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // changes thirst level based on how much water dog is fed
                View thirst = getView().findViewById(R.id.thirst_bar);
                View thirst2 = getView().findViewById(R.id.thirst_bar2);
                View thirst3 = getView().findViewById(R.id.thirst_bar3);
                View thirst4 = getView().findViewById(R.id.thirst_bar4);
                if (thirstLevel < 4 && mainViewModel.waterCount().getValue() > 0) {
                    if (thirstLevel == 0) {
                        thirst4.setAlpha(1.0f);
                    } else if (thirstLevel == 1) {
                        thirst4.setAlpha(0.0f);
                        thirst3.setAlpha(1.0f);
                    } else if (thirstLevel == 2) {
                        thirst3.setAlpha(0.0f);
                        thirst2.setAlpha(1.0f);
                    } else if (thirstLevel == 3) {
                        thirst2.setAlpha(0.0f);
                        thirst.setAlpha(1.0f);
                    }
                    thirstLevel++;
                }
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


        /*
            Toy display client interactions
         */

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


        // dog "plays with" ball if ball is available
        ImageButton ball = root.findViewById(R.id.dog_toy1);
        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView toy = getView().findViewById(R.id.toy_ball);
                boolean isEnabled = mainViewModel.toys().getValue()[0];
                if(isEnabled){
                    if (toy.getAlpha() == 0.0f) {
                        toy.setAlpha(1.0f);
                    } else {
                        toy.setAlpha(0.0f);
                    }
                }
            }
        });

        // dog "plays with" bone if bone is available
        ImageButton bone = root.findViewById(R.id.dog_toy2);
        bone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView toy = getView().findViewById(R.id.toy_bone);
                boolean isEnabled = mainViewModel.toys().getValue()[1];
                if(isEnabled){
                    if (toy.getAlpha() == 0.0f) {
                        toy.setAlpha(1.0f);
                    } else {
                        toy.setAlpha(0.0f);
                    }
                }
            }
        });

        // dog "plays with" stick if stick is available
        ImageButton stick = root.findViewById(R.id.dog_toy3);
        stick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView toy = getView().findViewById(R.id.toy_stick);
                boolean isEnabled = mainViewModel.toys().getValue()[2];
                if(isEnabled){
                    if (toy.getAlpha() == 0.0f) {
                        toy.setAlpha(1.0f);
                    } else {
                        toy.setAlpha(0.0f);
                    }
                }
            }
        });


        /*
            Hat display functions
         */

        // displays selected hat
        mainViewModel.hats().observe(getViewLifecycleOwner(), value -> {
            int[] images = {R.drawable.shiba,
                            R.drawable.shiba_prop,
                            R.drawable.shiba_frog,
                            R.drawable.shiba_astro,
                            R.drawable.shiba_beanie,
                            R.drawable.shiba_tiara,
                            R.drawable.shiba_rice,
                            R.drawable.shiba_daisy,
                            R.drawable.shiba_top};
            for(int i = 0; i < value.length; i++) {
                if(value[i][1]) {
                    ((ImageView) root.findViewById(R.id.shiba)).setImageResource(images[i]);
                    return;
                }
            }
        });

        return root;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // changes hunger level bar based on hunger value
        super.onViewCreated(view, savedInstanceState);
        View hunger = getView().findViewById(R.id.hunger_bar);
        View hunger2 = getView().findViewById(R.id.hunger_bar2);
        View hunger3 = getView().findViewById(R.id.hunger_bar3);
        View hunger4 = getView().findViewById(R.id.hunger_bar4);
        if (hungerLevel == 1) {
            hunger4.setAlpha(1.0f);
        } else if (hungerLevel == 2) {
            hunger3.setAlpha(1.0f);
        } else if (hungerLevel == 3) {
            hunger2.setAlpha(1.0f);
        } else if (hungerLevel == 4) {
            hunger.setAlpha(1.0f);
        }

        // changes thirst level bar based on hunger value
        View thirst = getView().findViewById(R.id.thirst_bar);
        View thirst2 = getView().findViewById(R.id.thirst_bar2);
        View thirst3 = getView().findViewById(R.id.thirst_bar3);
        View thirst4 = getView().findViewById(R.id.thirst_bar4);
        if (thirstLevel == 1) {
            thirst4.setAlpha(1.0f);
        } else if (thirstLevel == 2) {
            thirst3.setAlpha(1.0f);
        } else if (thirstLevel == 3) {
            thirst2.setAlpha(1.0f);
        } else if (thirstLevel == 4) {
            thirst.setAlpha(1.0f);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

