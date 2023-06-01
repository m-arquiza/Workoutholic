package com.example.workoutholicapp.ui.plans;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.MainActivity;
import com.example.workoutholicapp.R;
import com.example.workoutholicapp.backend.Logger.Log;
import com.example.workoutholicapp.backend.Logger.LogPlan;
import com.example.workoutholicapp.backend.ViewWorkout.Exercise;
import com.example.workoutholicapp.backend.ViewWorkout.ViewWorkout;
import com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlan;
import com.example.workoutholicapp.backend.WorkoutPlans.WorkoutPlans;
import com.example.workoutholicapp.databinding.FragmentPlansBinding;
import com.example.workoutholicapp.ui.MainViewModel;
import com.example.workoutholicapp.ui.shop.ShopFragment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

import okhttp3.Response;


public class PlansFragment extends Fragment {
    private FragmentPlansBinding binding;
    private View root;
    private MainViewModel mainViewModel;

    private LinkedList<Log> logList;

    private LinkedList<WorkoutPlan> plansList;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPlansBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        ConstraintLayout parent = root.findViewById(R.id.plansParent_layout);

        MainActivity activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();

        mainViewModel.moneyAmount().observe(getViewLifecycleOwner(), value -> {
            TextView money = root.findViewById(R.id.coin_text);
            money.setText(value + " coins");
        });

        List<TextView> entries = new ArrayList<>();
        List<TextView> dates = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();
        logList = mainViewModel.getList();
        plansList = mainViewModel.getPlans();
        WorkoutPlan currentPlan = new WorkoutPlan();

        Button entryButton = root.findViewById(R.id.plan_button);
        entryButton.setOnClickListener(v -> {
            currentPlan.setList(logList);
            currentPlan.setName("Workout Plan!");
            plansList.add(currentPlan);

            if(plansList.size() == 1) {
                TextView entryDate = root.findViewById(R.id.entryDate);
                entryDate.setVisibility(View.VISIBLE);
                entryDate.setText(plansList.get(0).getName());

                TextView entry = root.findViewById(R.id.plan);
                entry.setVisibility(View.VISIBLE);
                entry.setText(plansList.get(0).getNameList());
                Button deletePlan_button = root.findViewById(R.id.deletePlan_button);
                deletePlan_button.setVisibility(View.VISIBLE);

                entries.add(entry);
                dates.add(entryDate);
                buttons.add(deletePlan_button);

                deletePlan_button.setOnClickListener(v1 -> {
                    plansList.removeFirst();

                    if(plansList.isEmpty()) {
                        root.findViewById(R.id.entryDate).setVisibility(View.GONE);
                        root.findViewById(R.id.deletePlan_button).setVisibility(View.GONE);
                        root.findViewById(R.id.plan).setVisibility(View.GONE);
                    } else {
                        for(int i=0; i<plansList.size(); i++) {
                            entries.get(i).setText(plansList.get(i).getNameList());
                            dates.get(i).setText(plansList.get(i).getName());
                        }

                        parent.removeView(entries.get(entries.size()-1));
                        parent.removeView(dates.get(dates.size()-1));
                        parent.removeView(buttons.get(buttons.size()-1));

                        entries.remove(entries.size()-1);
                        dates.remove(dates.size()-1);
                        buttons.remove(buttons.size()-1);
                    }
                });
            } else {
                int index = plansList.size()-1;

                TextView newEntry = setTextView(entries.get(0));
                newEntry.setId(View.generateViewId());
                newEntry.setTextSize(20);
                newEntry.setText(plansList.get(index).getNameList());
                newEntry.setLineSpacing(entries.get(0).getLineSpacingExtra(), entries.get(0).getLineSpacingMultiplier());

                ConstraintLayout.LayoutParams entryLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(350),
                        dpToPixel(125)
                );

                entryLayoutParams.startToStart = parent.getId();
                entryLayoutParams.endToEnd = parent.getId();
                entryLayoutParams.topToBottom = entries.get(index - 1).getId();
                entryLayoutParams.topMargin = dpToPixel(100);
                newEntry.setLayoutParams(entryLayoutParams);
                parent.addView(newEntry);

                TextView newEntryDate = setTextView(dates.get(0));
                newEntryDate.setTextSize(20);
                newEntryDate.setText(plansList.get(index).getName());

                ConstraintLayout.LayoutParams dateLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(175),
                        dpToPixel(50)
                );

                dateLayoutParams.bottomToTop = newEntry.getId();
                dateLayoutParams.startToStart = newEntry.getId();
                dateLayoutParams.bottomMargin = 25;
                newEntryDate.setLayoutParams(dateLayoutParams);
                parent.addView(newEntryDate);

                Button newDeleteEntry_button = setButton(buttons.get(0));
                newDeleteEntry_button.setTextSize(25);
                newDeleteEntry_button.setTextColor(Color.WHITE);

                ConstraintLayout.LayoutParams buttonLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(50),
                        dpToPixel(50)
                );

                buttonLayoutParams.bottomToTop = newEntry.getId();
                buttonLayoutParams.endToEnd = newEntry.getId();
                buttonLayoutParams.bottomMargin = 25;
                newDeleteEntry_button.setLayoutParams(buttonLayoutParams);
                parent.addView(newDeleteEntry_button);

                entries.add(newEntry);
                dates.add(newEntryDate);
                buttons.add(newDeleteEntry_button);

                newDeleteEntry_button.setOnClickListener(v3 -> {
                    logList.remove(index);
                    android.util.Log.d("Tag123", "index is:" + index);
                    for(int j=index; j<plansList.size(); j++) {
                        entries.get(j).setText(plansList.get(j).getNameList());
                        dates.get(j).setText(plansList.get(j).getName());
                    }

                    parent.removeView(entries.get(entries.size()-1));
                    parent.removeView(dates.get(dates.size()-1));
                    parent.removeView(buttons.get(buttons.size()-1));

                    entries.remove(entries.size()-1);
                    dates.remove(dates.size()-1);
                    buttons.remove(buttons.size()-1);
                });
            }
        });

        if(plansList.isEmpty()) {
            root.findViewById(R.id.entryDate).setVisibility(View.GONE);
            root.findViewById(R.id.deletePlan_button).setVisibility(View.GONE);
            root.findViewById(R.id.plan).setVisibility(View.GONE);
        } else {

            TextView entryDate = root.findViewById(R.id.entryDate);
            entryDate.setVisibility(View.VISIBLE);
            entryDate.setText("Workout Plan!");

            TextView entry = root.findViewById(R.id.plan);
            entry.setVisibility(View.VISIBLE);
            entry.setText(plansList.get(0).getNameList());

            Button deletePlan_button = root.findViewById(R.id.deletePlan_button);
            deletePlan_button.setVisibility(View.VISIBLE);

            entries.add(entry);
            dates.add(entryDate);
            buttons.add(deletePlan_button);

            deletePlan_button.setOnClickListener(v1 -> {
                plansList.removeFirst();

                if (plansList.isEmpty()) {
                    root.findViewById(R.id.entryDate).setVisibility(View.GONE);
                    root.findViewById(R.id.deletePlan_button).setVisibility(View.GONE);
                    root.findViewById(R.id.plan).setVisibility(View.GONE);
                } else {
                    for (int i = 0; i < plansList.size(); i++) {
                        entries.get(i).setText(plansList.get(i).getNameList());
                        //dates.get(i).setText(logList.get(i).getDate());
                    }

                    parent.removeView(entries.get(entries.size() - 1));
                    parent.removeView(dates.get(dates.size() - 1));
                    parent.removeView(buttons.get(buttons.size() - 1));

                    entries.remove(entries.size() - 1);
                    dates.remove(dates.size() - 1);
                    buttons.remove(buttons.size() - 1);
                }
            });

            for (int i = 1; i < plansList.size(); i++) {
                TextView newEntry = setTextView(entries.get(0));
                newEntry.setId(View.generateViewId());
                newEntry.setTextSize(20);
                newEntry.setText(plansList.get(i).getNameList());

                newEntry.setLineSpacing(entries.get(0).getLineSpacingExtra(), entries.get(0).getLineSpacingMultiplier());

                ConstraintLayout.LayoutParams entryLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(350),
                        dpToPixel(125)
                );

                entryLayoutParams.startToStart = parent.getId();
                entryLayoutParams.endToEnd = parent.getId();
                entryLayoutParams.topToBottom = entries.get(i - 1).getId();
                entryLayoutParams.topMargin = dpToPixel(100);
                newEntry.setLayoutParams(entryLayoutParams);
                parent.addView(newEntry);

                TextView newEntryDate = setTextView(dates.get(0));
                newEntryDate.setTextSize(20);
                newEntryDate.setText("Workout Plan!");

                ConstraintLayout.LayoutParams dateLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(175),
                        dpToPixel(50)
                );

                dateLayoutParams.bottomToTop = newEntry.getId();
                dateLayoutParams.startToStart = newEntry.getId();
                dateLayoutParams.bottomMargin = 25;
                newEntryDate.setLayoutParams(dateLayoutParams);
                parent.addView(newEntryDate);

                Button newDeletePlan_button = setButton(buttons.get(0));
                newDeletePlan_button.setTextSize(25);
                newDeletePlan_button.setTextColor(Color.WHITE);

                ConstraintLayout.LayoutParams buttonLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(50),
                        dpToPixel(50)
                );

                buttonLayoutParams.bottomToTop = newEntry.getId();
                buttonLayoutParams.endToEnd = newEntry.getId();
                buttonLayoutParams.bottomMargin = 25;
                newDeletePlan_button.setLayoutParams(buttonLayoutParams);
                parent.addView(newDeletePlan_button);

                entries.add(newEntry);
                dates.add(newEntryDate);
                buttons.add(newDeletePlan_button);

                int finalI = i;
                newDeletePlan_button.setOnClickListener(v3 -> {
                    plansList.remove(finalI);
                    android.util.Log.d("Tag123", "index is:" + finalI);
                    for (int j = finalI; j < logList.size(); j++) {
                        entries.get(j).setText(plansList.get(j).getNameList());
                        //dates.get(j).setText(logList.get(j).getDate());
                    }

                    parent.removeView(entries.get(entries.size() - 1));
                    parent.removeView(dates.get(dates.size() - 1));
                    parent.removeView(buttons.get(buttons.size() - 1));

                    entries.remove(entries.size() - 1);
                    dates.remove(dates.size() - 1);
                    buttons.remove(buttons.size() - 1);
                });
            }
        }

        mainViewModel.setPlans(plansList);
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public TextView setTextView(TextView source) {
        TextView dest = new TextView(getContext());

        dest.setTextColor(source.getTextColors());
        dest.setBackground(source.getBackground());
        dest.setGravity(source.getGravity());

        return dest;
    }

    public Button setButton(Button source) {
        Button dest = new Button(getContext());

        dest.setBackground(source.getBackground());
        dest.setText("x");

        return dest;
    }

    public int dpToPixel(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}