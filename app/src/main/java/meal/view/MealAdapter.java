package meal.view;

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


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.Holder> {
    private final ArrayList<Meal> mealArrayList;
    private final OnMealClick listOnClickItem;

    public MealAdapter(ArrayList<Meal> mealArrayList, OnMealClick listOnClickItem) {
        this.mealArrayList = mealArrayList;
        this.listOnClickItem = listOnClickItem;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_favorite_btn, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Meal simpleMeal = mealArrayList.get(position);
        holder.tv_mealName.setText(simpleMeal.getStrMeal());
        holder.tv_mealID.setText(String.valueOf(simpleMeal.getIdMeal()));
        Glide.with(holder.img_meal.getContext()).load(simpleMeal.getStrMealThumb()).into(holder.img_meal);
    }

    @Override
    public int getItemCount() {
        Log.d("MealAdapter", "Size of meals list: " + (mealArrayList != null ? mealArrayList.size() : "null"));
        return mealArrayList != null ? mealArrayList.size() : 0;
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView img_meal;
        public TextView tv_mealName, tv_mealID;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img_meal = itemView.findViewById(R.id.img_dish);
            tv_mealName = itemView.findViewById(R.id.tv_dishName);
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

        @Override
        public void onClick(View view) {
        }
    }
}

