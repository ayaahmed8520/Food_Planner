package com.example.foodplanner.view.allCountry;


import java.util.List;

import com.example.foodplanner.model.allCountry.Country;

public interface AllCountryInterface {
    public List<Country> getCountries();
    public void navToSpecificCountryMeal(String areaName);
}