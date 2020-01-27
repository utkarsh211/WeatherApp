package com.utkarsh.flobizassignment.Repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.utkarsh.flobizassignment.Models.Weather;
import com.utkarsh.flobizassignment.NetworkService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDetailsRepository {
private static WeatherDetailsRepository instance;
private Context context;
private String city;
private String country;
private static final String appId = "b6907d289e10d714a6e88b30761fae22";
private static final String weather_url = "https://samples.openweathermap.org/data/2.5/";

    public static WeatherDetailsRepository getInstance()
    {
        if(instance == null)
        {
            instance = new WeatherDetailsRepository();
        }
        return instance;
    }

    public MutableLiveData<Weather> getWeather(Context context)
    {
        this.context = context;
        MutableLiveData<Weather> weatherMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .build();
                return chain.proceed(newRequest);
            }
        }).addInterceptor(interceptor)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(client)
                .baseUrl(weather_url)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        NetworkService networkService = retrofit.create(NetworkService.class);
        Call<Weather> call = networkService.getWeather(city + "," + country,appId);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                weatherMutableLiveData.setValue(weather);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
        return weatherMutableLiveData;
    }

    public void updateWeatherCityAndCountry(String city, String country)
    {
        this.city = city;
        this.country = country;
    }
}
