package com.utkarsh.flobizassignment.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.utkarsh.flobizassignment.Models.Cities;
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

public class MainActivityRepository {
    private static MainActivityRepository instance;
    private static final String cities_url = "https://api.myjson.com/";
    private Cities cities = new Cities();

    public static MainActivityRepository getInstance()
    {
        if(instance == null)
        {
            instance = new MainActivityRepository();
        }
        return instance;
    }

    public MutableLiveData<Cities> getCities()
    {
        MutableLiveData<Cities> citiesMutableLiveData = new MutableLiveData<>();
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
                .baseUrl(cities_url)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        NetworkService networkService = retrofit.create(NetworkService.class);
        Call<Cities> call = networkService.getCities();
        call.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                assert response.body() != null;
                cities = response.body();
                citiesMutableLiveData.setValue(cities);
            }
            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });
        return citiesMutableLiveData;
    }


}
