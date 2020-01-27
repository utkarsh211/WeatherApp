package com.utkarsh.flobizassignment.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cities {
    @SerializedName("cities")
    @Expose
    private List<City> citiesArrayList;

    public List<City> getCitiesArrayList() {
        return citiesArrayList;
    }


}
