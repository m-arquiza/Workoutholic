package com.example.workoutholicapp.ui.statistics;

import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.workoutholicapp.MainActivity;
import com.example.workoutholicapp.R;
import com.example.workoutholicapp.backend.Logger.Log;
import com.example.workoutholicapp.databinding.FragmentEntriesBinding;
import com.example.workoutholicapp.databinding.FragmentStatisticsBinding;
import com.example.workoutholicapp.ui.MainViewModel;

import java.util.LinkedList;

public class StatisticsFragment extends Fragment {
    private FragmentStatisticsBinding binding;
    private View root;
    private LinkedList<Log> logList;
    private MainViewModel mainViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        MainActivity activity = (MainActivity) requireActivity();
        mainViewModel = activity.getMainViewModel();
        logList = mainViewModel.getList();

        if(logList.isEmpty()) {
            TextView noStatWarning = root.findViewById(R.id.no_stat_warning);
            noStatWarning.setVisibility(VISIBLE);
        } else {

        }





        return root;
    }
}