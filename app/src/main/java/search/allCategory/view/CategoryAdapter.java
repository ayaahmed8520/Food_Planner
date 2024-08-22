package search.allCategory.view;

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

import java.util.ArrayList;

import search.allCategory.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private static final String TAG = "MyAdapter";
    AllCategoryInterface allCategoryInterface;
    ArrayList<Category> categories;
    Context context;

    public void setList(ArrayList<Category> updatedCategories){this.categories=updatedCategories;}


    public CategoryAdapter(Context context, AllCategoryInterface allCategoryInterface, ArrayList<Category> categories) {
        this.allCategoryInterface = allCategoryInterface;
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_area,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        Category current =categories.get(position);
        holder.name.setText(current.getStrCategory());
        Glide.with(context)
                .load(current.getStrCategoryThumb())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(holder.photo);

        holder.wholeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allCategoryInterface.navToSpecificCategoryMeals(current.getStrCategory());

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout wholeItem;
        TextView name;
        ImageView photo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wholeItem=itemView.findViewById(R.id.layout_whole_area);
            name=itemView.findViewById(R.id.tv_countryName);
            photo=itemView.findViewById(R.id.img_flag);


        }

    }

}