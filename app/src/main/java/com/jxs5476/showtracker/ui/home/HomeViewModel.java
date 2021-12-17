package com.jxs5476.showtracker.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jxs5476.showtracker.ShowDatabase;

public class HomeViewModel extends ViewModel {

    private ShowDatabase db;
    private MutableLiveData<String> mText;

    public HomeViewModel() {
       // db = ShowDatabase.getInstance(getApplicationContext());
        mText = new MutableLiveData<>();
        mText.setValue("test");
    }

    public LiveData<String> getText() {
        return mText;
    }
}