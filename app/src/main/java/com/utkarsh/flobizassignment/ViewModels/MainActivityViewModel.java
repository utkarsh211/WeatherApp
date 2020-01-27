package com.utkarsh.flobizassignment.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.utkarsh.flobizassignment.Models.Cities;
import com.utkarsh.flobizassignment.Repositories.MainActivityRepository;

public class MainActivityViewModel extends AndroidViewModel {
    public LiveData<Cities> citiesLiveData;
    private MainActivityRepository mainActivityRepository;

    public MainActivityViewModel(Application application) {
        super(application);
        mainActivityRepository = MainActivityRepository.getInstance();
    }
    public void getCities()
    {
        citiesLiveData = mainActivityRepository.getCities();
    }
}
