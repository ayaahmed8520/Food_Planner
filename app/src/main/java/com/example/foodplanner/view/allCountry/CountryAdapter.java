package com.example.foodplanner.view.allCountry;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.R;
import java.util.List;

import com.example.foodplanner.model.allCountry.Country;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private static final String TAG = "MyAdapter";
    AllCountryInterface allCountryInterface;
    List areas;

    public CountryAdapter(AllCountryInterface allCountryInterface, List areas) {
        this.allCountryInterface = allCountryInterface;
        this.areas = areas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_area,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        Country current = (Country) areas.get(position);
        holder.name.setText(current.getCountryName());
        holder.photo.setImageBitmap(
                decodeSampledBitmapFromResource( holder.photo.getResources(), current.getImageID(), 100, 80));
        holder.wholeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allCountryInterface.navToSpecificCountryMeal(current.getCountryName());

            }
        });

    }

    @Override
    public int getItemCount() {
        return areas.size();
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




































    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;


            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


}
