package com.avansprojects.antl.ui.start;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StartViewModel extends ViewModel {
    private MutableLiveData _data = new MutableLiveData<String>();

    public StartViewModel(){
        _data.setValue("Hello Jetpack");
    }

    public LiveData<String> get_data() {
        return _data;
    }
}
