package com.example.foodplanner.model;


import java.util.List;

import com.example.foodplanner.model.allCountry.CountryLIst;
import com.example.foodplanner.model.allCountry.Country;

public class SearchResponse {
    public List<Country> getAreas(){

        return CountryLIst.getInstance().getAllAreas();
    }
}

