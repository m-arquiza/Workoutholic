package com.example.workoutholicapp.ui.entries;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.R;
import com.example.workoutholicapp.backend.ViewWorkout.Exercise;
import com.example.workoutholicapp.backend.ViewWorkout.ViewWorkout;
import com.example.workoutholicapp.databinding.FragmentEntriesBinding;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

import okhttp3.Response;

public class EntriesFragment extends Fragment {

    private FragmentEntriesBinding binding;
    private PopupWindow popupWindow;
    private boolean hasEntry;
    private View root;
    private EntriesViewModel entriesViewModel;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entriesViewModel =
                new ViewModelProvider(this).get(EntriesViewModel.class);

        binding = FragmentEntriesBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        this.hasEntry = false;

        Button entryButton = root.findViewById(R.id.entry_button);
        entryButton.setOnClickListener(v -> {
            final boolean[] muscleLogged = {false};
            final boolean[] workoutLogged = {false};
            final boolean[] repLogged = {false};
            final List<Exercise>[] exercise = new List[]{new ArrayList<>()};

            View popupView = inflater.inflate(R.layout.popup_layout, null);
            // Specify the length and width through constants
            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;

            // Make Inactive Items Outside Of PopupWindow
            boolean focusable = true;

            // Create a window with our parameters
            popupWindow = new PopupWindow(popupView, width, height, focusable);

            // Set the location of the window on the screen
            popupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);

            // Display current date
            TextView date = popupView.findViewById(R.id.date);
            date.setText(new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date()));

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
                        entriesViewModel.setMuscle((String) parent.getItemAtPosition(position));
                        muscleLogged[0] = true;

                        Future<Response> futureResponse = ViewWorkout.getRequest(entriesViewModel.getMuscle());
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
                        entriesViewModel.setWorkout((String) parent.getItemAtPosition(position));
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
                        entriesViewModel.setRep((String) parent.getItemAtPosition(position));
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
                    this.hasEntry = true;
                    updateViews();
                } else {
                    TextView warning = popupView.findViewById(R.id.warning);
                    warning.setText("You must choose all the options!");
                }
            });
        });

        updateViews();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    private void updateViews() {
        if (this.hasEntry) {
            TextView entryDate = root.findViewById(R.id.entryDate);
            entryDate.setVisibility(View.VISIBLE);
            entryDate.setText(entriesViewModel.getDate());

            TextView entry = root.findViewById(R.id.entry);
            entry.setVisibility(View.VISIBLE);
            entry.setText("Group: " + entriesViewModel.getMuscle() +
                          " \n Workout: " + entriesViewModel.getWorkout() +
                          " \n Repetitions: " + entriesViewModel.getRep());
            entry.setLines(3);

            Button deleteEntry_button = root.findViewById(R.id.deleteEntry_button);
            deleteEntry_button.setVisibility(View.VISIBLE);

            deleteEntry_button.setOnClickListener(v -> {
                hasEntry = false;
                updateViews();
            });
        } else {
            root.findViewById(R.id.entryDate).setVisibility(View.GONE);
            root.findViewById(R.id.deleteEntry_button).setVisibility(View.GONE);
            root.findViewById(R.id.entry).setVisibility(View.GONE);
        }
    }
}