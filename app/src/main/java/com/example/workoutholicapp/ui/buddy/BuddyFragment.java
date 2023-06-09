package com.example.workoutholicapp.ui.buddy;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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

import java.util.Timer;
import java.util.TimerTask;


/*
    Class to represent functions within the buddy page of the app.
 */
public class BuddyFragment extends Fragment {

    private FragmentBuddyBinding binding;
    private MainViewModel mainViewModel;

    private MainActivity activity;
    private int hungerLevel; // displayed in vitals
    private int thirstLevel; // displayed in vitals

    private int happinessLevel; // displayed in vitals

    private Timer timer;
    private Handler handler;
    private final int intervalInMillis = 24 * 60 * 60 * 1000; // 1 day in milliseconds
    //private final int intervalInMillis = 5000; // for testing/demo purposes
    private boolean[] isTime;
    private long[] timeLeftInMillis = {0, 0, 0};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();
        hungerLevel = 0;
        thirstLevel = 0;
        happinessLevel = 0;
        isTime = new boolean[]{true, true, true};
    }

    private void scheduleHungerLevelUpdate() {
        // Create a new timer task
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateHungerLevel();
                    }
                });
            }
        };

        // Schedule the timer task to run at the specified interval
        timer.scheduleAtFixedRate(timerTask, intervalInMillis, intervalInMillis);
    }

    private void scheduleThirstLevelUpdate() {
        // Create a new timer task
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateThirstLevel();
                    }
                });
            }
        };

        // Schedule the timer task to run at the specified interval
        timer.scheduleAtFixedRate(timerTask, intervalInMillis, intervalInMillis);
    }

    private void scheduleHappinessLevelUpdate() {
        // Create a new timer task
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateHappinessLevel();
                    }
                });
            }
        };

        // Schedule the timer task to run at the specified interval
        timer.scheduleAtFixedRate(timerTask, intervalInMillis, intervalInMillis);
    }


    @Override
    public void onResume() {
        super.onResume();
        scheduleHungerLevelUpdate();
        scheduleThirstLevelUpdate();
        scheduleHappinessLevelUpdate();
    }

    private void updateHungerLevel() {
        // decreases hunger level every time interval
        View hunger = getView().findViewById(R.id.hunger_bar);
        View hunger2 = getView().findViewById(R.id.hunger_bar2);
        View hunger3 = getView().findViewById(R.id.hunger_bar3);
        View hunger4 = getView().findViewById(R.id.hunger_bar4);
        if (hungerLevel >= 0) {
            if (hungerLevel == 3) {
                hunger.setAlpha(0.0f);
                hunger2.setAlpha(1.0f);
            } else if (hungerLevel == 2) {
                hunger2.setAlpha(0.0f);
                hunger3.setAlpha(1.0f);
            } else if (hungerLevel == 1) {
                hunger3.setAlpha(0.0f);
                hunger4.setAlpha(1.0f);
            } else if (hungerLevel == 0){
                hunger4.setAlpha(0.0f);
            }
            if (hungerLevel > 0) hungerLevel--;
        }
    }

    private void updateThirstLevel() {
        // // decreases thirst level every time interval
        View thirst = getView().findViewById(R.id.thirst_bar);
        View thirst2 = getView().findViewById(R.id.thirst_bar2);
        View thirst3 = getView().findViewById(R.id.thirst_bar3);
        View thirst4 = getView().findViewById(R.id.thirst_bar4);
        if (thirstLevel >= 0) {
            if (thirstLevel == 3) {
                thirst.setAlpha(0.0f);
                thirst2.setAlpha(1.0f);
            } else if (thirstLevel == 2) {
                thirst2.setAlpha(0.0f);
                thirst3.setAlpha(1.0f);
            } else if (thirstLevel == 1) {
                thirst3.setAlpha(0.0f);
                thirst4.setAlpha(1.0f);
            } else if (thirstLevel == 0){
                thirst4.setAlpha(0.0f);
            }
            if (thirstLevel > 0) thirstLevel--;
        }
    }

    private void updateHappinessLevel() {
        // decreases happiness level every time interval
        View happy = getView().findViewById(R.id.happiness_bar);
        View happy2 = getView().findViewById(R.id.happiness_bar2);
        View happy3 = getView().findViewById(R.id.happiness_bar3);
        if (happinessLevel >= 0) {
            if (happinessLevel == 3) {
                happy.setAlpha(0.0f);
                happy2.setAlpha(1.0f);
            } else if (happinessLevel == 2) {
                happy2.setAlpha(0.0f);
                happy3.setAlpha(1.0f);
            } else if (happinessLevel == 1) {
                happy3.setAlpha(0.0f);
            }
            if (happinessLevel > 0) happinessLevel--;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        timer = new Timer();
        handler = new Handler();
        binding = FragmentBuddyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton foodButton = root.findViewById(R.id.dogfood_button);
        ImageButton waterButton = root.findViewById(R.id.dogwater_button);

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // changes hunger level based on how much food dog is fed
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
                    if (hungerLevel < 4) hungerLevel++;
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
                    if (thirstLevel < 4) thirstLevel++;
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

        mainViewModel.autoList().observe(getViewLifecycleOwner(), value -> {
            View hunger = getView().findViewById(R.id.hunger_bar);
            if (value[0]) {
                hunger.setAlpha(1.0f);
                hungerLevel = 3;
            }
        });

        mainViewModel.autoList().observe(getViewLifecycleOwner(), value -> {
            View thirst = getView().findViewById(R.id.thirst_bar);
            if (value[1]) {
                thirst.setAlpha(1.0f);
                thirstLevel = 3;
            }
        });

        mainViewModel.autoList().observe(getViewLifecycleOwner(), value -> {
            View happiness = getView().findViewById(R.id.happiness_bar);
            if (value[2]) {
                happiness.setAlpha(1.0f);
                happinessLevel = 2;
            }
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
        TextView warning = root.findViewById(R.id.dialog_title);
        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView toy = getView().findViewById(R.id.toy_ball);
                ImageView bone = getView().findViewById(R.id.toy_bone);
                ImageView stick = getView().findViewById(R.id.toy_stick);
                bone.setAlpha(0.0f);
                stick.setAlpha(0.0f);
                boolean isEnabled = mainViewModel.toys().getValue()[0];
                if(isEnabled){
                    if (toy.getAlpha() == 0.0f && isTime[0]) {
                        toy.setAlpha(1.0f);
                        changeHappiness();
                        warning.setAlpha(0.0f);
                        isTime[0] = false;
                        CountDownTimer timer = new CountDownTimer(2 * 60 * 60 * 1000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                timeLeftInMillis[0] = millisUntilFinished;
                            }

                            @Override
                            public void onFinish() {
                                // Timer finished, handle as needed
                                isTime[0] = true;
                            }
                        };
                        timer.start();
                    } else if (toy.getAlpha() == 0.0f) {
                        if (warning.getAlpha() == 1.0f) {
                            warning.setAlpha(0.0f);
                        } else {
                            int seconds = (int) ((timeLeftInMillis[0] / 1000));
                            String message = "";
                            if (seconds > 3600) {
                                message = "Wait " + seconds / 3600 + "h " + (seconds % 3600) / 60 + "m to play with this toy again!";
                            } else if (seconds > 60) {
                                message = "Wait " + seconds / 60 + "m " + seconds % 60 + "s to play with this toy again!";
                            } else {
                                message = "Wait " + seconds + "s to play with this toy again!";
                            }
                            warning.setText(message);
                            warning.setAlpha(1.0f);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    warning.setAlpha(0.0f);
                                }
                            }, 5 * 1000); // 5 seconds in milliseconds
                        }
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
                ImageView ball = getView().findViewById(R.id.toy_ball);
                ImageView stick = getView().findViewById(R.id.toy_stick);
                ball.setAlpha(0.0f);
                stick.setAlpha(0.0f);
                boolean isEnabled = mainViewModel.toys().getValue()[1];
                if(isEnabled){
                    if (toy.getAlpha() == 0.0f && isTime[1]) {
                        toy.setAlpha(1.0f);
                        changeHappiness();
                        warning.setAlpha(0.0f);
                        isTime[1] = false;
                        CountDownTimer timer2 = new CountDownTimer(2 * 60 * 60 * 1000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                timeLeftInMillis[1] = millisUntilFinished;
                            }

                            @Override
                            public void onFinish() {
                                // Timer finished, handle as needed
                                isTime[1] = true;
                            }
                        };
                        timer2.start();
                    } else if (toy.getAlpha() == 0.0f) {
                        if (warning.getAlpha() == 1.0f) {
                            warning.setAlpha(0.0f);
                        } else {
                            // shows error message if 2 hour limit not up
                            int seconds = (int) ((timeLeftInMillis[1] / 1000));
                            String message = "";
                            if (seconds > 3600) {
                                message = "Wait " + seconds / 3600 + "h " + (seconds % 3600) / 60 + "m to play with this toy again!";
                            } else if (seconds > 60) {
                                message = "Wait " + seconds / 60 + "m " + seconds % 60 + "s to play with this toy again!";
                            } else {
                                message = "Wait " + seconds + "s to play with this toy again!";
                            }
                            warning.setText(message);
                            warning.setAlpha(1.0f);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    warning.setAlpha(0.0f);
                                }
                            }, 5 * 1000); // 5 seconds in milliseconds
                        }
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
                ImageView ball = getView().findViewById(R.id.toy_ball);
                ImageView bone = getView().findViewById(R.id.toy_bone);
                ball.setAlpha(0.0f);
                bone.setAlpha(0.0f);
                boolean isEnabled = mainViewModel.toys().getValue()[2];
                if (isEnabled) {
                    if (toy.getAlpha() == 0.0f && isTime[2]) {
                        toy.setAlpha(1.0f);
                        changeHappiness();
                        warning.setAlpha(0.0f);
                        isTime[2] = false;
                        CountDownTimer timer3 = new CountDownTimer(2 * 60 * 60 * 1000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                timeLeftInMillis[2] = millisUntilFinished;
                            }

                            @Override
                            public void onFinish() {
                                // Timer finished, handle as needed
                                isTime[2] = true;
                            }
                        };
                        timer3.start();
                    } else if (toy.getAlpha() == 0.0f) {
                        if (warning.getAlpha() == 1.0f) {
                            warning.setAlpha(0.0f);
                        } else {
                            int seconds = (int) ((timeLeftInMillis[2] / 1000));
                            String message = "";
                            if (seconds > 3600) {
                                message = "Wait " + seconds / 3600 + "h " + (seconds % 3600) / 60 + "m to play with this toy again!";
                            } else if (seconds > 60) {
                                message = "Wait " + seconds / 60 + "m " + seconds % 60 + "s to play with this toy again!";
                            } else {
                                message = "Wait " + seconds + "s to play with this toy again!";
                            }
                            warning.setText(message);
                            warning.setAlpha(1.0f);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    warning.setAlpha(0.0f);
                                }
                            }, 5 * 1000); // 5 seconds in milliseconds
                        }
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


    public void changeHappiness() {
        View happy = getView().findViewById(R.id.happiness_bar);
        View happy2 = getView().findViewById(R.id.happiness_bar2);
        View happy3 = getView().findViewById(R.id.happiness_bar3);
        if (happinessLevel < 3) {
            if (happinessLevel == 0) {
                happy3.setAlpha(1.0f);
            } else if (happinessLevel == 1) {
                happy3.setAlpha(0.0f);
                happy2.setAlpha(1.0f);
            } else if (happinessLevel == 2) {
                happy2.setAlpha(0.0f);
                happy.setAlpha(1.0f);
            }
            if (happinessLevel < 3) happinessLevel++;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // sets hunger level bar based on previous hunger value
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

        // sets thirst level bar based on previous thirst value
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

        // sets happiness level bar based on previous happiness value
        View happy = getView().findViewById(R.id.happiness_bar);
        View happy2 = getView().findViewById(R.id.happiness_bar2);
        View happy3 = getView().findViewById(R.id.happiness_bar3);
        if (happinessLevel == 1) {
            happy3.setAlpha(1.0f);
        } else if (happinessLevel == 2) {
            happy2.setAlpha(1.0f);
        } else if (happinessLevel == 3) {
            happy.setAlpha(1.0f);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
        binding = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // saves the amount of time until toy limits are up
        super.onSaveInstanceState(outState);
        outState.putLongArray("times", timeLeftInMillis);
    }

    public int getHungerLevel() { return hungerLevel; }

    public int getThirstLevel() { return thirstLevel; }

    public int getHappinessLevel() { return happinessLevel; }
}