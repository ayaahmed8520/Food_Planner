package search.SpecificArea.view;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;

import meal.model.Meal;

public class SpecificAreaAdapter extends RecyclerView.Adapter<SpecificAreaAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";

    ArrayList<Meal> meals;

    public void setList(ArrayList<Meal> updatedMeals){this.meals=updatedMeals;}
    SpecificAreaMealInterface specificAreaMealInterface;

    public SpecificAreaAdapter(ArrayList<Meal> meals, SpecificAreaMealInterface specificAreaMealInterface) {
        this.meals = meals;
        this.specificAreaMealInterface = specificAreaMealInterface;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_with_favorite_btn, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        Meal current = meals.get(position);
        holder.meal_name_tv.setText(current.getStrMeal());
        holder.meal_id_tv.setText(Long.toString(current.getIdMeal()));

        Glide.with(holder.meal_photo.getContext())
                .load(current.getStrMealThumb())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(holder.meal_photo);

        holder.meal_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                specificAreaMealInterface.navigateToViewDetails(Long.toString(current.getIdMeal()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView meal_photo;
        TextView meal_name_tv, meal_id_tv;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_photo = itemView.findViewById(R.id.img_dish);
            meal_name_tv = itemView.findViewById(R.id.tv_dishName);
            meal_id_tv = itemView.findViewById(R.id.dish_id);


        }
    }
}
