package com.example.foodplanner.view.allIngredient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;

import java.util.List;

import com.example.foodplanner.model.allIngredient.Ingredient;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";
    AllIngredientsInterface allIngredientsInterface;
    List<Ingredient> ingredients;
    Context context;

    public void setList(List<Ingredient> updatedIngredients){this.ingredients=updatedIngredients;}


    public IngredientAdapter(Context context, AllIngredientsInterface allIngredientsInterface, List<Ingredient> ingredients) {
        this.allIngredientsInterface = allIngredientsInterface;
        this.ingredients = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_area,parent,false);
        IngredientAdapter.MyViewHolder myViewHolder=new IngredientAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        Ingredient current =ingredients.get(position);
        holder.name.setText(current.getStrIngredient());
        Glide.with(context)
                .load(context.getResources().getString(R.string.ingredient_img, current.getStrIngredient()))
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(holder.photo);

        holder.wholeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allIngredientsInterface.navToSpecificIngredientMeals(current.getStrIngredient());

            }
        });

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout wholeItem;
        TextView name;
        ImageView photo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            wholeItem=itemView.findViewById(R.id.layout_whole_area);
            name=itemView.findViewById(R.id.tv_countryName);
            photo=itemView.findViewById(R.id.img_flag);


        }

    }

}
