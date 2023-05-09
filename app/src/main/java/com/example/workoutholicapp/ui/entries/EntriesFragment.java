package com.example.workoutholicapp.ui.entries;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
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
import com.example.workoutholicapp.databinding.FragmentEntriesBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        Button entryButton = root.findViewById(R.id.entry_button);

        this.hasEntry = false;

        if(!this.hasEntry) {
            entryButton.setOnClickListener(v -> {
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

                // Create a Drop Down Spinner
                Spinner workoutSpinner = popupView.findViewById(R.id.workout_spinner);
                String[] items = new String[]{"Select...", "Chest", "Back", "Legs"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_dropdown_item, items);

                workoutSpinner.setAdapter(adapter);
                workoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        entriesViewModel.setMuscle((String)parent.getItemAtPosition(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                // Confirm Button
                Button entryDoneButton = popupView.findViewById(R.id.entryDone_button);
                entryDoneButton.setOnClickListener(v2 -> popupWindow.dismiss());

                this.hasEntry = true;
                showEntry();
            });
        } else {
            showEntry();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("ResourceAsColor")
    public void showEntry() {
        TextView entry = root.findViewById(R.id.entry);
        entry.setText(entriesViewModel.getMuscle());
        entry.setBackgroundResource(R.drawable.border);

        TextView date = root.findViewById((R.id.entryDate));
        date.setText("123123123");
        date.setBackgroundResource(R.drawable.border);

        Button deleteEntryButton = root.findViewById(R.id.deleteEntry_button);
        deleteEntryButton.setBackgroundResource(R.drawable.border);
        deleteEntryButton.setEnabled(true);
        deleteEntryButton.setBackgroundColor(R.color.salmon_red);

        deleteEntryButton.setOnClickListener(v -> {
            entry.setVisibility(root.GONE);
            date.setVisibility(root.GONE);
            deleteEntryButton.setVisibility(root.GONE);
        });

    }
}