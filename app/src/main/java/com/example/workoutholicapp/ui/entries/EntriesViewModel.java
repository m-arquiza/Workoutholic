package com.example.workoutholicapp.ui.entries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntriesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EntriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Create New Entry:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}