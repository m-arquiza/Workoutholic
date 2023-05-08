package com.example.workoutholicapp.ui.buddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutholicapp.R;
import com.example.workoutholicapp.databinding.FragmentBuddyBinding;

public class BuddyFragment extends Fragment {

    private FragmentBuddyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuddyViewModel buddyViewModel =
                new ViewModelProvider(this).get(BuddyViewModel.class);

        binding = FragmentBuddyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button foodButton = root.findViewById(R.id.dogfood_button);
        Button waterButton = root.findViewById(R.id.dogwater_button);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddyViewModel.onFoodClick();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buddyViewModel.onWaterClick();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}