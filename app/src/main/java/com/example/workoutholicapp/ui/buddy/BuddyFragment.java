package com.example.workoutholicapp.ui.buddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.databinding.FragmentBuddyBinding;

public class BuddyFragment extends Fragment {

    private FragmentBuddyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuddyViewModel buddyViewModel =
                new ViewModelProvider(this).get(BuddyViewModel.class);

        binding = FragmentBuddyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBuddy;
        buddyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}