package search.allCountry.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import search.allCountry.model.Country;
import search.SpecificArea.view.SpecificAreaMeal;
import search.presenter.SearchPresenter;

public class AllCountry extends AppCompatActivity implements AllCountryInterface {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CountryAdapter countryAdapter;
    SearchView searchView;
    List<Country> countries =new ArrayList<>();
    List<Country> countryArrayList =new ArrayList<>();
    ImageView closeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_area);

        recyclerView=findViewById(R.id.rv_areas);
        searchView=findViewById(R.id.sv_searchByArea);
        closeScreen=findViewById(R.id.btn_close);

        closeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        countries.addAll(getCountries());
        countryArrayList.addAll(getCountries());

        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        countryAdapter = new CountryAdapter(this, countryArrayList);
        recyclerView.setAdapter(countryAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()){
                    countryArrayList.clear();
                    String search = newText.toLowerCase(Locale.ROOT);
                    for (Country country : countries) {
                        if (country.getCountryName().toLowerCase(Locale.ROOT).startsWith(search)) {
                            countryArrayList.add(country);
                        }
                    }

                    countryAdapter.notifyDataSetChanged();

                }else{
                    countryArrayList.clear();
                    countryArrayList.addAll(countries);
                    countryAdapter.notifyDataSetChanged();
                }

                return true;
            }
        });



    }

    @Override
    public List<Country> getCountries() {
        return new SearchPresenter().getAreas();
    }

    @Override
    public void navToSpecificCountryMeal(String areaName) {
        Intent intent = new Intent(this, SpecificAreaMeal.class);
        intent.putExtra("areaName",areaName);
        startActivity(intent);

    }
}