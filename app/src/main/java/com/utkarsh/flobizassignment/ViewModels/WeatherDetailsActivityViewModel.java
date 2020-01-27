package com.utkarsh.flobizassignment.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.utkarsh.flobizassignment.Models.Weather;
import com.utkarsh.flobizassignment.Repositories.WeatherDetailsRepository;

public class WeatherDetailsActivityViewModel extends AndroidViewModel {
private WeatherDetailsRepository weatherDetailsRepository;
public  LiveData<Weather> weatherLiveData;
private Context context;


    public WeatherDetailsActivityViewModel(@NonNull Application application) {
        super(application);
        weatherDetailsRepository = WeatherDetailsRepository.getInstance();
    }

    public void getWeather(Context context)
    {
        this.context = context;
        weatherLiveData = weatherDetailsRepository.getWeather(context);
    }
    public void updateWeatherCity(String city, String country)
    {
        weatherDetailsRepository.updateWeatherCityAndCountry(city,country);
    }
}
