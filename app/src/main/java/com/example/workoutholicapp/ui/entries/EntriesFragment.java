package com.example.workoutholicapp.ui.entries;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.R;
import com.example.workoutholicapp.databinding.FragmentEntriesBinding;

public class EntriesFragment extends Fragment {

    private FragmentEntriesBinding binding;
    private PopupWindow popupWindow;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EntriesViewModel entriesViewModel =
                new ViewModelProvider(this).get(EntriesViewModel.class);

        binding = FragmentEntriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(true) {
            newWorkoutEntry(entriesViewModel, inflater);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void newWorkoutEntry(EntriesViewModel entriesViewModel, LayoutInflater inflater) {
        // Create our Popup Window if a user wants to create a new workout entry.
        final TextView textView = binding.textEntries;
        entriesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        textView.setOnClickListener(view -> {
            View popupView = inflater.inflate(R.layout.popup_layout, null);
            popupWindow = new PopupWindow(popupView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

            // Initialize the closeButton
            Button closeButton = popupView.findViewById(R.id.close_button);
            closeButton.setOnClickListener(view123 -> popupWindow.dismiss());
            popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
        });
    }
}