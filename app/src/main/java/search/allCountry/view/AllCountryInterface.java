package search.allCountry.view;


import java.util.List;

import search.allCountry.model.Country;

public interface AllCountryInterface {
    public List<Country> getCountries();
    public void navToSpecificCountryMeal(String areaName);
}