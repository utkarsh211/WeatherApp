package com.utkarsh.flobizassignment.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
}
