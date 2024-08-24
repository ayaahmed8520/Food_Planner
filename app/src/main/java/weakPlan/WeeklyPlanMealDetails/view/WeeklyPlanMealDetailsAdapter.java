package weakPlan.WeeklyPlanMealDetails.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import calendar.CalendarActivity;
import dp.MealDetails;
import favorite.view.MyAddAndRemoveFavIn;
import firebase.FirebaseRepoImp;
import meal.view.OnMealClick;
import mealDetails.model.MealIngredientMeasure;
import mealDetails.view.MealDetailsAdapter;
import mealDetails.view.MealDetailsIn;
import mealDetails.view.MealDetailsIngredientsAdapter;
import weakPlan.WeeklyPlanMealDetails.model.WeeklyPlanDetailsIngredientMeasure;
import weakPlan.dp.WeeklyPlanMealDetails;
import weakPlan.view.AddAndRemoveFromPlan;

public class WeeklyPlanMealDetailsAdapter extends RecyclerView.Adapter<WeeklyPlanMealDetailsAdapter.Holder> {

    AddAndRemoveFromPlan addAndRemoveFromPlan;
    private final ArrayList<WeeklyPlanMealDetails> weeklyPlanMealDetailsArrayList;
    WeeklyPlanMealMealDetailsIn weeklyPlanMealMealDetailsIn;
    static OnMealClick listOnClickItem;




    public WeeklyPlanMealDetailsAdapter(ArrayList<WeeklyPlanMealDetails> mealDetailsArrayList) {
        this.weeklyPlanMealDetailsArrayList = mealDetailsArrayList;

    }

    @NonNull
    @Override
    public WeeklyPlanMealDetailsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_plan_item_details, parent, false);
        return new WeeklyPlanMealDetailsAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyPlanMealDetailsAdapter.Holder holder, int position) {

        WeeklyPlanMealDetails mealDetails = weeklyPlanMealDetailsArrayList.get(position);
        holder.tv_mealName.setText(mealDetails.getStrMeal());
        holder.tv_mealCountry.setText(mealDetails.getStrArea());
        holder.tv_mealInstructions.setText(mealDetails.getStrInstructions());
        Glide.with(holder.img_meal.getContext()).load(mealDetails.getStrMealThumb()).into(holder.img_meal);
        String clientID = FirebaseRepoImp.getInstance(holder.black_background.getContext()).getSharedPreferences().getString("clientID", null);




        ArrayList<WeeklyPlanDetailsIngredientMeasure> ingredients=new ArrayList<WeeklyPlanDetailsIngredientMeasure>();
        if (mealDetails.strIngredient1!=null&&!mealDetails.strIngredient1.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient1,mealDetails.strMeasure1.toString()));
        } if (mealDetails.strIngredient2!=null&&!mealDetails.strIngredient2.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient2,mealDetails.strMeasure2.toString()));
        } if (mealDetails.strIngredient3!=null&&!mealDetails.strIngredient3.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient3,mealDetails.strMeasure3.toString()));
        } if (mealDetails.strIngredient4!=null&&!mealDetails.strIngredient4.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient4,mealDetails.strMeasure4.toString()));
        } if (mealDetails.strIngredient5!=null&&!mealDetails.strIngredient5.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient5,mealDetails.strMeasure5.toString()));
        } if (mealDetails.strIngredient6!=null&&!mealDetails.strIngredient6.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient6,mealDetails.strMeasure6.toString()));
        } if (mealDetails.strIngredient7!=null&&!mealDetails.strIngredient7.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient7,mealDetails.strMeasure7.toString()));
        } if (mealDetails.strIngredient8!=null&&!mealDetails.strIngredient8.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient8,mealDetails.strMeasure8.toString()));
        } if (mealDetails.strIngredient9!=null&&!mealDetails.strIngredient9.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient9,mealDetails.strMeasure9.toString()));
        } if (mealDetails.strIngredient10!=null&&!mealDetails.strIngredient10.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient10,mealDetails.strMeasure10.toString()));

        } if (mealDetails.strIngredient11!=null&&!mealDetails.strIngredient11.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient11,mealDetails.strMeasure11.toString()));
        } if (mealDetails.strIngredient12!=null&&!mealDetails.strIngredient12.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient12,mealDetails.strMeasure12.toString()));
        } if (mealDetails.strIngredient13!=null&&!mealDetails.strIngredient13.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient13,mealDetails.strMeasure13.toString()));
        } if (mealDetails.strIngredient14!=null&&!mealDetails.strIngredient14.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient14,mealDetails.strMeasure14.toString()));
        } if (mealDetails.strIngredient15!=null&&!mealDetails.strIngredient15.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient15,mealDetails.strMeasure15.toString()));
        } if (mealDetails.strIngredient16!=null&&!mealDetails.strIngredient16.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient16,mealDetails.strMeasure16.toString()));
        } if (mealDetails.strIngredient17!=null&&!mealDetails.strIngredient17.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient17,mealDetails.strMeasure17.toString()));
        } if (mealDetails.strIngredient18!=null&&!mealDetails.strIngredient18.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient18,mealDetails.strMeasure18.toString()));
        } if (mealDetails.strIngredient19!=null&&!mealDetails.strIngredient19.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient19,mealDetails.strMeasure19.toString()));
        } if (mealDetails.strIngredient20!=null&&!mealDetails.strIngredient20.isEmpty()){
            ingredients.add(new WeeklyPlanDetailsIngredientMeasure(mealDetails.strIngredient20,mealDetails.strMeasure20.toString()));
        }


        holder.mealDetailsIngredientsAdapter= new WeeklyPlanMealDetailsIngredientAdapter(ingredients);
        holder.recyclerViewIngredients.setAdapter( holder.mealDetailsIngredientsAdapter);


        if (!mealDetails.getStrYoutube().isEmpty() && !mealDetails.getStrYoutube().equals("")&& mealDetails.getStrYoutube() != null)  {
            String[] split = mealDetails.getStrYoutube().split("=");
            holder.mealVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = split[1];
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return weeklyPlanMealDetailsArrayList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public ImageView img_meal;
        public TextView tv_mealName, tv_mealCountry, tv_mealInstructions;
        private final YouTubePlayerView mealVideo;
        RecyclerView recyclerViewIngredients;
        View black_background;
        WeeklyPlanMealDetailsIngredientAdapter mealDetailsIngredientsAdapter;
        LinearLayoutManager layoutManager;

        TextView tv_mealID;


        public Holder(@NonNull View itemView) {
            super(itemView);
            img_meal = itemView.findViewById(R.id.img_foodDetails);
            tv_mealName = itemView.findViewById(R.id.tv_detailsFoodName);
            tv_mealCountry = itemView.findViewById(R.id.tv_detailsFoodCountry);
            tv_mealInstructions = itemView.findViewById(R.id.tv_instructionDetails);
            mealVideo = itemView.findViewById(R.id.IngredientVideo);
            recyclerViewIngredients = itemView.findViewById(R.id.rv_ingredients);
            black_background = itemView.findViewById(R.id.black_background);
            layoutManager=new LinearLayoutManager(recyclerViewIngredients.getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerViewIngredients.setLayoutManager(layoutManager);

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

