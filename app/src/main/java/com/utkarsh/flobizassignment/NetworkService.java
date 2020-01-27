package com.utkarsh.flobizassignment;

import com.utkarsh.flobizassignment.Models.Cities;
import com.utkarsh.flobizassignment.Models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {

    @GET("bins/lw9qf")
    Call<Cities> getCities();

    @GET("weather")
    Call<Weather> getWeather(@Query("q") String q, @Query("appid") String appId);
}
