package com.example.workoutholicapp.ui.entries;

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
import com.example.workoutholicapp.databinding.FragmentEntriesBinding;
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

public class EntriesFragment extends Fragment {
    private FragmentEntriesBinding binding;
    private View root;
    private EntriesViewModel entriesViewModel;
    private MainViewModel mainViewModel;

    private LinkedList<Log> logList;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entriesViewModel =
                new ViewModelProvider(this).get(EntriesViewModel.class);

        binding = FragmentEntriesBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        ConstraintLayout parent = root.findViewById(R.id.entriesParent_layout);

        MainActivity activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();

        mainViewModel.moneyAmount().observe(getViewLifecycleOwner(), value -> {
            TextView money = root.findViewById(R.id.coin_text);
            money.setText(value + " coins");
        });

        List<TextView> entries = new ArrayList<>();
        List<TextView> dates = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();
        logList = entriesViewModel.getList();

        Button entryButton = root.findViewById(R.id.entry_button);
        entryButton.setOnClickListener(v -> {
            final boolean[] muscleLogged = {false};
            final boolean[] workoutLogged = {false};
            final boolean[] repLogged = {false};
            final List<Exercise>[] exercise = new List[]{new ArrayList<>()};

            Exercise ex = new Exercise();

            View popupView = inflater.inflate(R.layout.popup_layout, null);
            // Specify the length and width through constants
            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;

            // Make Inactive Items Outside Of PopupWindow
            boolean focusable = true;

            // Create a window with our parameters
            PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            // Set the location of the window on the screen
            popupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);

            // Display current date
            TextView date = popupView.findViewById(R.id.date);
            String dateString = "" + new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
            date.setText("Date: " + dateString);

            // Close button on the top right
            Button entryCloseButton = popupView.findViewById(R.id.entryClose_button);
            entryCloseButton.setOnClickListener(v2 -> popupWindow.dismiss());

            // Create the ArrayLists for muscle, exercise, and rep.
            List<String> muscleList = new ArrayList<>();
            muscleList.add("Select...");

            List<String> exerciseList = new ArrayList<>();
            exerciseList.add("Select...");

            List<String> repList = new ArrayList<>();
            repList.add("Select...");

            // Create a Weight Drop Down Spinner
            Spinner repSpinner = popupView.findViewById(R.id.rep_spinner);

            // Create a Muscle Group Drop Down Spinner
            Spinner muscleSpinner = popupView.findViewById(R.id.muscle_spinner);

            // Create a Workout Group Drop Down Spinner
            Spinner workoutSpinner = popupView.findViewById(R.id.workout_spinner);

            muscleList.addAll(ViewWorkout.muscleList());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item, muscleList);

            muscleSpinner.setAdapter(adapter);

            muscleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0) {
                        muscleLogged[0] = false;

                        exerciseList.clear();
                        exerciseList.add("Select...");

                        ArrayAdapter<String> workoutAdapter = new ArrayAdapter<>(requireContext(),
                                android.R.layout.simple_spinner_dropdown_item, exerciseList);

                        workoutSpinner.setAdapter(workoutAdapter);
                    } else {
                        ex.setMuscle((String) parent.getItemAtPosition(position));
                        muscleLogged[0] = true;

                        Future<Response> futureResponse = ViewWorkout.getRequest(ex.getMuscle());
                        try {
                            Response response = futureResponse.get();
                            assert response.body() != null;
                            exercise[0] = ViewWorkout.JSONmapper(response.body().string());

                            exerciseList.clear();
                            exerciseList.add("Select...");

                            for(int i=0; i < exercise[0].size(); i++) {
                                exerciseList.add(exercise[0].get(i).getName());
                            }

                            ArrayAdapter<String> workoutAdapter = new ArrayAdapter<>(requireContext(),
                                    android.R.layout.simple_spinner_dropdown_item, exerciseList);

                            workoutSpinner.setAdapter(workoutAdapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            ArrayAdapter<String> workoutAdapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item, exerciseList);

            workoutSpinner.setAdapter(workoutAdapter);

            workoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0) {
                        workoutLogged[0] = false;
                    } else {
                        ex.setName((String) parent.getItemAtPosition(position));
                        workoutLogged[0] = true;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            for(int i=1; i<=20; i++) {
                repList.add(String.valueOf((i)));
            }

            ArrayAdapter<String> repAdapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item, repList);

            repSpinner.setAdapter(repAdapter);

            repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0) {
                        repLogged[0] = false;
                    } else {
                        ex.setNumberOfReps((String) parent.getItemAtPosition(position));
                        repLogged[0] = true;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            // Confirm Button
            Button entryDoneButton = popupView.findViewById(R.id.entryDone_button);
            entryDoneButton.setOnClickListener(v2 -> {
                if(muscleLogged[0] && workoutLogged[0] & repLogged[0]) {
                    popupWindow.dismiss();

                    if(ShopFragment.mainViewModel != null) {
                        ShopFragment.mainViewModel.moneyUpdate(10);
                    }

                    logList.add(new Log(dateString, ex));

                    if(logList.size() == 1) {
                        TextView entryDate = root.findViewById(R.id.entryDate);
                        entryDate.setVisibility(View.VISIBLE);
                        entryDate.setText(logList.get(0).getDate());

                        TextView entry = root.findViewById(R.id.entry);
                        entry.setVisibility(View.VISIBLE);
                        entry.setText("Group: " + logList.get(0).getExercise().getMuscle() +
                                " \n Workout: " + logList.get(0).getExercise().getName() +
                                " \n Repetitions: " + logList.get(0).getExercise().getNumberOfReps());
                        entry.setLines(3);

                        Button deleteEntry_button = root.findViewById(R.id.deleteEntry_button);
                        deleteEntry_button.setVisibility(View.VISIBLE);

                        entries.add(entry);
                        dates.add(entryDate);
                        buttons.add(deleteEntry_button);

                        deleteEntry_button.setOnClickListener(v1 -> {
                            logList.removeFirst();

                            if(logList.isEmpty()) {
                                root.findViewById(R.id.entryDate).setVisibility(View.GONE);
                                root.findViewById(R.id.deleteEntry_button).setVisibility(View.GONE);
                                root.findViewById(R.id.entry).setVisibility(View.GONE);
                            } else {
                                for(int i=0; i<logList.size(); i++) {
                                    entries.get(i).setText("Group: " + logList.get(i).getExercise().getMuscle() +
                                            " \n Workout: " + logList.get(i).getExercise().getName() +
                                            " \n Repetitions: " + logList.get(i).getExercise().getNumberOfReps());
                                    dates.get(i).setText(logList.get(i).getDate());
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
                        int index = logList.size()-1;

                        TextView newEntry = setTextView(entries.get(0));
                        newEntry.setId(View.generateViewId());
                        newEntry.setTextSize(20);
                        newEntry.setText("Group: " + logList.get(index).getExercise().getMuscle() +
                                " \n Workout: " + logList.get(index).getExercise().getName() +
                                " \n Repetitions: " + logList.get(index).getExercise().getNumberOfReps());
                        newEntry.setLineSpacing(entries.get(0).getLineSpacingExtra(), entries.get(0).getLineSpacingMultiplier());

                        ConstraintLayout.LayoutParams entryLayoutParams = new ConstraintLayout.LayoutParams(
                                dpToPixel(350),
                                dpToPixel(100)
                        );

                        entryLayoutParams.startToStart = parent.getId();
                        entryLayoutParams.endToEnd = parent.getId();
                        entryLayoutParams.topToBottom = entries.get(index - 1).getId();
                        entryLayoutParams.topMargin = dpToPixel(100);
                        newEntry.setLayoutParams(entryLayoutParams);
                        parent.addView(newEntry);

                        TextView newEntryDate = setTextView(dates.get(0));
                        newEntryDate.setTextSize(20);
                        newEntryDate.setText(logList.get(index).getDate());

                        ConstraintLayout.LayoutParams dateLayoutParams = new ConstraintLayout.LayoutParams(
                                dpToPixel(150),
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
                            for(int j=index; j<logList.size(); j++) {
                                entries.get(j).setText("Group: " + logList.get(j).getExercise().getMuscle() +
                                        " \n Workout: " + logList.get(j).getExercise().getName() +
                                        " \n Repetitions: " + logList.get(j).getExercise().getNumberOfReps());
                                dates.get(j).setText(logList.get(j).getDate());
                            }

                            parent.removeView(entries.get(entries.size()-1));
                            parent.removeView(dates.get(dates.size()-1));
                            parent.removeView(buttons.get(buttons.size()-1));

                            entries.remove(entries.size()-1);
                            dates.remove(dates.size()-1);
                            buttons.remove(buttons.size()-1);
                        });
                    }
                } else {
                    TextView warning = popupView.findViewById(R.id.warning);
                    warning.setText("You must choose all the options!");
                }
            });
        });

        if(logList.isEmpty()) {
            root.findViewById(R.id.entryDate).setVisibility(View.GONE);
            root.findViewById(R.id.deleteEntry_button).setVisibility(View.GONE);
            root.findViewById(R.id.entry).setVisibility(View.GONE);
        } else {
            TextView entryDate = root.findViewById(R.id.entryDate);
            entryDate.setVisibility(View.VISIBLE);
            entryDate.setText(logList.get(0).getDate());

            TextView entry = root.findViewById(R.id.entry);
            entry.setVisibility(View.VISIBLE);
            entry.setText("Group: " + logList.get(0).getExercise().getMuscle() +
                    " \n Workout: " + logList.get(0).getExercise().getName() +
                    " \n Repetitions: " + logList.get(0).getExercise().getNumberOfReps());
            entry.setLines(3);

            Button deleteEntry_button = root.findViewById(R.id.deleteEntry_button);
            deleteEntry_button.setVisibility(View.VISIBLE);

            entries.add(entry);
            dates.add(entryDate);
            buttons.add(deleteEntry_button);

            deleteEntry_button.setOnClickListener(v1 -> {
                logList.removeFirst();

                if (logList.isEmpty()) {
                    root.findViewById(R.id.entryDate).setVisibility(View.GONE);
                    root.findViewById(R.id.deleteEntry_button).setVisibility(View.GONE);
                    root.findViewById(R.id.entry).setVisibility(View.GONE);
                } else {
                    for (int i = 0; i < logList.size(); i++) {
                        entries.get(i).setText("Group: " + logList.get(i).getExercise().getMuscle() +
                                " \n Workout: " + logList.get(i).getExercise().getName() +
                                " \n Repetitions: " + logList.get(i).getExercise().getNumberOfReps());
                        dates.get(i).setText(logList.get(i).getDate());
                    }

                    parent.removeView(entries.get(entries.size() - 1));
                    parent.removeView(dates.get(dates.size() - 1));
                    parent.removeView(buttons.get(buttons.size() - 1));

                    entries.remove(entries.size() - 1);
                    dates.remove(dates.size() - 1);
                    buttons.remove(buttons.size() - 1);
                }
            });

            for (int i = 1; i < logList.size(); i++) {
                TextView newEntry = setTextView(entries.get(0));
                newEntry.setId(View.generateViewId());
                newEntry.setTextSize(20);
                newEntry.setText("Group: " + logList.get(i).getExercise().getMuscle() +
                        " \n Workout: " + logList.get(i).getExercise().getName() +
                        " \n Repetitions: " + logList.get(i).getExercise().getNumberOfReps());
                newEntry.setLineSpacing(entries.get(0).getLineSpacingExtra(), entries.get(0).getLineSpacingMultiplier());

                ConstraintLayout.LayoutParams entryLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(350),
                        dpToPixel(100)
                );

                entryLayoutParams.startToStart = parent.getId();
                entryLayoutParams.endToEnd = parent.getId();
                entryLayoutParams.topToBottom = entries.get(i - 1).getId();
                entryLayoutParams.topMargin = dpToPixel(100);
                newEntry.setLayoutParams(entryLayoutParams);
                parent.addView(newEntry);

                TextView newEntryDate = setTextView(dates.get(0));
                newEntryDate.setTextSize(20);
                newEntryDate.setText(logList.get(i).getDate());

                ConstraintLayout.LayoutParams dateLayoutParams = new ConstraintLayout.LayoutParams(
                        dpToPixel(150),
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

                int finalI = i;
                newDeleteEntry_button.setOnClickListener(v3 -> {
                    logList.remove(finalI);
                    android.util.Log.d("Tag123", "index is:" + finalI);
                    for (int j = finalI; j < logList.size(); j++) {
                        entries.get(j).setText("Group: " + logList.get(j).getExercise().getMuscle() +
                                " \n Workout: " + logList.get(j).getExercise().getName() +
                                " \n Repetitions: " + logList.get(j).getExercise().getNumberOfReps());
                        dates.get(j).setText(logList.get(j).getDate());
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

        entriesViewModel.setList(logList);
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