package com.example.foodplanner.view.weekPlan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;

import java.util.List;

import com.example.foodplanner.view.meal.OnMealClick;
import com.example.foodplanner.model.dp.weekPlanDB.WeeklyPlanMeal;

public class WeekPlanMealAdapter extends RecyclerView.Adapter<WeekPlanMealAdapter.ViewHolder>{

    private Context context;
    private List<WeeklyPlanMeal> planMeals;
    private WeekPlanInterface weekPlanInterface;
    public static final String TAG = "WeekPlanAdapter";
    OnMealClick listOnClickItem;

    public WeekPlanMealAdapter(Context context, List<WeeklyPlanMeal> meals,
                                WeekPlanInterface weekPlanInterface1, OnMealClick listOnClickItem){
        this.context = context;
        this.planMeals = meals;
        this.weekPlanInterface = weekPlanInterface1;
        this.listOnClickItem= listOnClickItem;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_week_plan_meal, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "WeekPlanOnCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeeklyPlanMeal currentPlanMeal = planMeals.get(position);
        holder.tv_planMealName.setText(currentPlanMeal.getMealName());
        holder.tv_planMealDate.setText(currentPlanMeal.getDate());
        holder.tv_planMealType.setText(currentPlanMeal.getMealType());

        holder.tv_mealID.setText(String.valueOf(currentPlanMeal.getIdMeal()));

        Glide.with(holder.img_planMeal.getContext())
                .load(currentPlanMeal.getMealThump())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(holder.img_planMeal);
        holder.btnRemoveFromPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weekPlanInterface.removePlannedMeal(currentPlanMeal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return planMeals.size();
    }

    public void setList(List<WeeklyPlanMeal> updatedMeals){
        this.planMeals = updatedMeals;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_planMeal;
        TextView tv_planMealName;
        TextView tv_planMealDate;
        TextView tv_planMealType;
        TextView tv_mealID;
        ImageView btnRemoveFromPlan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_planMeal = itemView.findViewById(R.id.img_weekPlannerMeal);
            tv_planMealName = itemView.findViewById(R.id.tv_weekPlannerMealName);
            tv_planMealDate = itemView.findViewById(R.id.tv_weekPlannerMealDate);
            tv_planMealType = itemView.findViewById(R.id.tv_weekPlannerMealType);
            btnRemoveFromPlan = itemView.findViewById(R.id.btn_removeWeekPlannerItem);

            tv_mealID = itemView.findViewById(R.id.dish_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listOnClickItem != null)
                    {
                        listOnClickItem.OnMealClicked(tv_mealID.getText().toString());
                    }
                }
            });
        }
    }
}

