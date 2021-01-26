package com.example.loginregister.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForumPublicationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForumPublicationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is publications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
