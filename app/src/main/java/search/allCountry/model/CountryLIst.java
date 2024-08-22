package search.allCountry.model;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class CountryLIst {

    private static CountryLIst countryLIst =null;
    private  List<Country> countries = new  ArrayList<Country>();

    private CountryLIst() {

        countries.add(new Country("American", R.drawable.america));
        countries.add(new Country("British", R.drawable.british));
        countries.add(new Country("Canadian", R.drawable.canada));
        countries.add(new Country("Chinese", R.drawable.china));
        countries.add(new Country("Croatian", R.drawable.croatian));
        countries.add(new Country("Dutch", R.drawable.dutch));
        countries.add(new Country("Egyptian", R.drawable.egypt));
        countries.add(new Country("French", R.drawable.french));
        countries.add(new Country("Greek", R.drawable.greek));
        countries.add(new Country("Indian", R.drawable.indian));
        countries.add(new Country("Irish", R.drawable.ireland));
        countries.add(new Country("Italian", R.drawable.italian));
        countries.add(new Country("Jamaican", R.drawable.jamaican));
        countries.add(new Country("Japanese", R.drawable.japan));
        countries.add(new Country("Kenyan", R.drawable.kenya));
        countries.add(new Country("Malaysian", R.drawable.malaysian));
        countries.add(new Country("Mexican", R.drawable.mexico));
        countries.add(new Country("Moroccan", R.drawable.moroco));
        countries.add(new Country("Polish", R.drawable.poland));
        countries.add(new Country("Portuguese", R.drawable.portug));
        countries.add(new Country("Russian", R.drawable.russian));
        countries.add(new Country("Spanish", R.drawable.spani));
        countries.add(new Country("Thai", R.drawable.thia));
        countries.add(new Country("Tunisian", R.drawable.tunisian));
        countries.add(new Country("Turkish", R.drawable.turcia));
        countries.add(new Country("Unknown", R.drawable.unknown));
        countries.add(new Country("Vietnamese", R.drawable.vietname));



    }

    public static CountryLIst getInstance() {
        if (countryLIst == null) {
            countryLIst = new CountryLIst();
        }

        return countryLIst;
    }
    public List<Country> getAllAreas() {
        return countries;
    }
}
