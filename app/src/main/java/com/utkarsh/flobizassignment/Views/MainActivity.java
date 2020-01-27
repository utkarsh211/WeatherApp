package com.utkarsh.flobizassignment.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.utkarsh.flobizassignment.Adapters.RecyclerAdapter;
import com.utkarsh.flobizassignment.Models.Cities;
import com.utkarsh.flobizassignment.Models.City;
import com.utkarsh.flobizassignment.R;
import com.utkarsh.flobizassignment.ViewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnCardClickListener{
private RecyclerView recyclerView;
private List<City> citiesList;
private List<String> citiesNames;
private RecyclerAdapter recyclerAdapter;
private ProgressBar progressBar;
private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        showProgressBar();
        citiesList = new ArrayList<>();
        citiesNames = new ArrayList<>();
        initAuthViewModel();
        mainActivityViewModel.getCities();
        mainActivityViewModel.citiesLiveData.observe(this, new Observer<Cities>() {
            @Override
            public void onChanged(Cities cities) {
                citiesList = cities.getCitiesArrayList();
                for(int i = 0; i < citiesList.size(); i++)
                {
                    String name = citiesList.get(i).getName();
                    citiesNames.add(name);
                    recyclerAdapter.notifyDataSetChanged();
                }
            }
        });
        initRecyclerView();
        hideProgressBar();
    }
    private void initRecyclerView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter(this, citiesNames, MainActivity.this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initAuthViewModel()
    {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    private void findViews(){
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.circular_progress);
    }
    private void showProgressBar(){
        findViewById(R.id.constraint_layout).setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.constraint_layout).setVisibility(View.VISIBLE);
    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(MainActivity.this,WeatherDetailsActivity.class);
        String countryName = "";
        switch (citiesNames.get(position))
        {
            case "London":
                countryName = "uk";
                break;
            case "Delhi":
                countryName = "india";
                break;

            case "Mumbai":
                countryName = "india";
                break;
            case "Pune":
                countryName = "india";
                break;
            case "Rajkot":

                countryName = "india";
                break;
            case "Jaipur":
                countryName = "india";
                break;
            case "Paris":
                countryName = "france";
                break;
            case "Singapore":
                countryName = "singapore";
                break;
            case "Sydney":
                countryName = "australia";
                break;
            case "Tokyo":
                countryName = "japan";
                break;
            case "Zurich":
                countryName = "switzerland";
                break;
        }
        intent.putExtra("countryName",countryName);
        intent.putExtra("cityName",citiesNames.get(position));
        startActivity(intent);
    }
}
