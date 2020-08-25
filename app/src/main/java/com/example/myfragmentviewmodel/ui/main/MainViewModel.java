package com.example.myfragmentviewmodel.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> mCounter;
    private final String TAG = getClass().toString();

    public void init() {
        if (mCounter == null) {
            mCounter = new MutableLiveData<Integer>();
            mCounter.setValue(0);
        }
    }

    public MutableLiveData<Integer> getCounter() {
        Log.d(TAG, "getCounter()");
        return mCounter;
    }

    public void setCounter(int count) {
        Log.d(TAG, "setCounter()");
        mCounter.setValue(count);
    }

    public void addCounter(int addVal) {
        Log.d(TAG, "addCounter()");
        int val = mCounter.getValue().intValue();
        val += addVal;
        mCounter.setValue(val);
        ;
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared()");
        super.onCleared();
    }
}
