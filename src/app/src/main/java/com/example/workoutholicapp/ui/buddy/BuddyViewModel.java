package com.example.workoutholicapp.ui.buddy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuddyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BuddyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the buddy fragment I made");
    }

    public LiveData<String> getText() {
        return mText;
    }
}