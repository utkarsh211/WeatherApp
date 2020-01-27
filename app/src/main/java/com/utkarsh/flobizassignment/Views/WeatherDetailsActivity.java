package com.utkarsh.flobizassignment.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.utkarsh.flobizassignment.Models.Main;
import com.utkarsh.flobizassignment.Models.Weather;
import com.utkarsh.flobizassignment.Models.Wind;
import com.utkarsh.flobizassignment.R;
import com.utkarsh.flobizassignment.ViewModels.WeatherDetailsActivityViewModel;

import java.util.Objects;

public class WeatherDetailsActivity extends AppCompatActivity {
private WeatherDetailsActivityViewModel weatherDetailsActivityViewModel;
private TextView locationText;
private TextView temperatureText;
private TextView humidityText;
private TextView pressureText;
private TextView windSpeedText;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        findViews();
        showProgressBar();
        initViewModel();
        String countryName = getIntent().getStringExtra("countryName");
        String cityName = getIntent().getStringExtra("cityName");
        weatherDetailsActivityViewModel.updateWeatherCity(cityName,countryName);
        weatherDetailsActivityViewModel.getWeather(WeatherDetailsActivity.this);
        weatherDetailsActivityViewModel.weatherLiveData.observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
             Main main = weather.getMain();
             Wind wind = weather.getWind();
             String locationString = cityName + "," + " " + countryName.substring(0,1).toUpperCase() + countryName.substring(1);
             locationText.setText(locationString);
             temperatureText.setText(main.getTemp());
             humidityText.setText(main.getHumidity());
             pressureText.setText(main.getPressure());
             windSpeedText.setText(wind.getSpeed());
             hideProgressBar();

            }
        });
    }
    private void initViewModel()
    {
        weatherDetailsActivityViewModel = ViewModelProviders.of(this).get(WeatherDetailsActivityViewModel.class);

    }
    private void findViews()
    {
        locationText = findViewById(R.id.location_text);
        temperatureText = findViewById(R.id.temp_text);
        humidityText = findViewById(R.id.humidity_text);
        pressureText = findViewById(R.id.pressure_text);
        windSpeedText = findViewById(R.id.wind_speed_text);
        progressBar = findViewById(R.id.circular_progress);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showProgressBar()
    {
        progressBar.setVisibility(View.VISIBLE);
        findViewById(R.id.constraint_layout).setVisibility(View.INVISIBLE);
    }
    private void hideProgressBar()
    {
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.constraint_layout).setVisibility(View.VISIBLE);
    }

}
