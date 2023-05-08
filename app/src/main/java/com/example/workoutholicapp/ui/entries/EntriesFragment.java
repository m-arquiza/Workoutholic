package com.example.workoutholicapp.ui.entries;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.R;
import com.example.workoutholicapp.databinding.FragmentEntriesBinding;

public class EntriesFragment extends Fragment {

    private FragmentEntriesBinding binding;
    private PopupWindow popupWindow;
    private Button entryButton;
    private boolean hasEntry;
    private View root;
    private EntriesViewModel entriesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entriesViewModel =
                new ViewModelProvider(this).get(EntriesViewModel.class);

        binding = FragmentEntriesBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        entryButton = root.findViewById(R.id.entry_button);

        this.hasEntry = false;

        entriesViewModel.setMuscle("Chest");
        System.out.println(entriesViewModel.getMuscle());

        if(!this.hasEntry) {
            entryButton.setVisibility(View.VISIBLE);
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

    public void showEntry() {
        TextView entry = root.findViewById(R.id.entry);
        entry.setText(entriesViewModel.getMuscle());
        entry.setBackgroundResource(R.drawable.border);
    }
}