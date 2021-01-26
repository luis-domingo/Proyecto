package com.example.loginregister.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForumTopicsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForumTopicsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is topics fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}