package search.presenter;


import java.util.List;

import search.allCountry.model.CountryLIst;
import search.allCountry.model.Country;

public class SearchPresenter {
    public List<Country> getAreas(){

        return CountryLIst.getInstance().getAllAreas();
    }
}

