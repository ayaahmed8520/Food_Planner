package weakPlan.WeeklyPlanMealDetails.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;

import java.util.ArrayList;

import mealDetails.model.MealIngredientMeasure;
import mealDetails.view.MealDetailsIngredientsAdapter;
import weakPlan.WeeklyPlanMealDetails.model.WeeklyPlanDetailsIngredientMeasure;

public class WeeklyPlanMealDetailsIngredientAdapter extends RecyclerView.Adapter<WeeklyPlanMealDetailsIngredientAdapter.ViewHolder> {
    ArrayList<WeeklyPlanDetailsIngredientMeasure> mealIngredientMeasures;


    public WeeklyPlanMealDetailsIngredientAdapter(ArrayList<WeeklyPlanDetailsIngredientMeasure> list) {
        mealIngredientMeasures = list;
    }

    @NonNull
    @Override
    public WeeklyPlanMealDetailsIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient_with_measure, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyPlanMealDetailsIngredientAdapter.ViewHolder holder, int position) {
        holder.ingredientMeasure.setText(mealIngredientMeasures.get(position).getWeeklyPlanIngredientMeasure());
        holder.ingredientName.setText(mealIngredientMeasures.get(position).getWeeklyPlanIngredientName());
        Glide.with(holder.ingredientImageView.getContext())
                .load(holder.ingredientImageView.getContext().getResources().getString(R.string.ingredient_img, mealIngredientMeasures.get(position).getWeeklyPlanIngredientName()))
                .placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image).into(holder.ingredientImageView);


    }

    @Override
    public int getItemCount() {
        return mealIngredientMeasures.size();
    }

    public void setList(ArrayList<WeeklyPlanDetailsIngredientMeasure> arrayList) {
        mealIngredientMeasures = arrayList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView ingredientMeasure;
        ImageView ingredientImageView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.tv_ingredientName);

            ingredientMeasure = itemView.findViewById(R.id.tv_ingredientMeasure);

            ingredientImageView = itemView.findViewById(R.id.img_ingredient);

        }


    }
}
