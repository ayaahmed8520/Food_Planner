package favorite.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import java.util.List;

import meal.view.OnMealClick;
import dp.MealDetails;

public class FavoriteMealsAdapter  extends RecyclerView.Adapter<FavoriteMealsAdapter.ViewHolder>{
    private Context context;
    private List<MealDetails> meals;
    private FavoriteFragmentIn favoriteFragmentIn;
    public static final String TAG = "FavoriteAdapter";
    OnMealClick listOnClickItem;
    public FavoriteMealsAdapter(Context context, List<MealDetails> meals,
                                FavoriteFragmentIn favoriteFragmentIn, OnMealClick listOnClickItem){
        this.context = context;
        this.meals = meals;
        this.favoriteFragmentIn = favoriteFragmentIn;
        this.listOnClickItem= listOnClickItem;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_checked_favorite_btn, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.i(TAG, "FavOnCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDetails currentMeal = meals.get(position);
        holder.tv_mealName.setText(currentMeal.getStrMeal());
        holder.tv_mealID.setText(String.valueOf(currentMeal.getIdMeal()));

        Glide.with(holder.img_meal.getContext())
                .load(currentMeal.getStrMealThumb())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(holder.img_meal);
        holder.btnRemoveFromFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                favoriteFragmentIn.removeMeal(currentMeal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setList(List<MealDetails> updatedMeals){
        this.meals = updatedMeals;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_meal;
        TextView tv_mealName;
        TextView tv_mealID;
        Button btnRemoveFromFavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_meal = itemView.findViewById(R.id.img_dish);
            tv_mealName = itemView.findViewById(R.id.tv_dishName);
            tv_mealID = itemView.findViewById(R.id.dish_id);
            btnRemoveFromFavorite = itemView.findViewById(R.id.btn_addFromFavorite);

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

