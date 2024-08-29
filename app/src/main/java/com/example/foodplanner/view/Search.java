package com.example.foodplanner.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplanner.R;

import com.example.foodplanner.view.allCountry.AllCountry;
import com.example.foodplanner.view.AllCategory.AllCategory;
import com.example.foodplanner.view.allIngredient.AllIngredients;

public class Search extends Fragment {
    Button btnSearchByArea , btnSearchByCategory,btnSearchByIngredient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSearchByArea= view.findViewById(R.id.btn_area);
        btnSearchByCategory= view.findViewById(R.id.btn_category);
        btnSearchByIngredient= view.findViewById(R.id.btn_ingredient);

        btnSearchByArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AllCountry.class);
                startActivity(intent);
            }
        });

        btnSearchByCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AllCategory.class);
                startActivity(intent);
            }
        });

        btnSearchByIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AllIngredients.class);
                startActivity(intent);
            }
        });



    }
}