package com.example.foodplanner.view.AllCategory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.foodplanner.model.allCategory.Category;
import com.example.foodplanner.presenter.CategoryPresenter;
import com.example.foodplanner.view.SpecificCategory.SpecificCategoryMeals;

public class AllCategory extends AppCompatActivity implements AllCategoryInterface {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CategoryAdapter categoryAdapter;
    SearchView searchView;
    List<Category> categoryArrayList=new ArrayList<>();
    List<Category> displayList=new ArrayList<>();
    ImageView closeScreen;
    CategoryPresenter categoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        categoryPresenter = new CategoryPresenter(this);

        recyclerView=findViewById(R.id.rv_categories);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        categoryAdapter= new CategoryAdapter(this,this,new ArrayList<>());
        recyclerView.setAdapter(categoryAdapter);

        categoryPresenter.getAllCategory();

        closeScreen=findViewById(R.id.btn_close);
        closeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView=findViewById(R.id.sv_searchByCategory);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextChange(String newText) {

                if (!newText.isEmpty()){
                    displayList.clear();
                    categoryAdapter.setList(displayList);
                    categoryAdapter.notifyDataSetChanged();

                    String search = newText.toLowerCase(Locale.ROOT);
                    for (Category category :categoryArrayList) {
                        if (category.getStrCategory().toLowerCase(Locale.ROOT).startsWith(search)) {
                            displayList.add(category);
                        }
                    }
                    categoryAdapter.setList(displayList);
                    categoryAdapter.notifyDataSetChanged();

                }else{
                    displayList.clear();
                    displayList.addAll(categoryArrayList);
                    categoryAdapter.setList(displayList);
                    categoryAdapter.notifyDataSetChanged();


                }

                return true;
            }
        });


    }

    @Override
    public void getCategories() {
        categoryPresenter.getAllCategory();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void resultSuccess(List<Category> categories) {

        categoryArrayList.addAll(categories);
        displayList.addAll(categories);
        categoryAdapter.setList(displayList);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        Log.e("CategoryTAG", "onFailureResult: "+ error );

    }

    @Override
    public void navToSpecificCategoryMeals(String categoryName) {
        Intent intent = new Intent(this, SpecificCategoryMeals.class);
        intent.putExtra("categoryName",categoryName);
        startActivity(intent);

    }
}