package meal.view;

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

import meal.model.SingleMeal;


public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    private final ArrayList<SingleMeal> singleMealList;
    private final OnMealClick clickItemListener;

    public MealAdapter(ArrayList<SingleMeal> singleMealList, OnMealClick clickItemListener) {
        this.singleMealList = singleMealList;
        this.clickItemListener = clickItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_favorite_btn, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SingleMeal singleMeal = singleMealList.get(position);
        viewHolder.meal_name_tv.setText(singleMeal.getStrMeal());
        viewHolder.meal_id_tv.setText(String.valueOf(singleMeal.getIdMeal()));
        Glide.with(viewHolder.meal_photo.getContext()).load(singleMeal.getStrMealThumb()).into(viewHolder.meal_photo);
    }

    @Override
    public int getItemCount() {
        return singleMealList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView meal_photo;
        public TextView meal_name_tv, meal_id_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_photo = itemView.findViewById(R.id.dish_image);
            meal_name_tv = itemView.findViewById(R.id.dish_name);
            meal_id_tv = itemView.findViewById(R.id.dish_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemListener != null)
                    {
                        clickItemListener.OnMealClicked(meal_id_tv.getText().toString());
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
        }
    }
}

