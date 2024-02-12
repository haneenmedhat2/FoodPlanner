package com.example.foodplanner.homeScreen.view;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meals;

import java.util.List;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.MealHolder>{

        private List<Meals> meals;
        private Context context;

        private  onMealClickListener listener;
    private static final String TAG = "RandomAdapter";

    public RandomAdapter(List<Meals> meals, Context context, onMealClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.listener=listener;
    }


    public void setList(List<Meals> updateMeals){
        this.meals=updateMeals;
    }
    @NonNull
    @Override
    public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.inspiration_card, parent, false);
        MealHolder mealHolder = new MealHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return mealHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealHolder holder, int position) {

        Meals meal = meals.get(position);
        holder.tvTitle.setText(meal.getStrMeal());

    /*    String url = meal.getStrMealThumb();
        Glide.with(context).load(url).error(R.drawable.loading).into(holder.ivPhoto);*/

        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.ivPhoto.getWidth(),holder.ivPhoto.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.loading)
                .into(holder.ivPhoto);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMealClick(meal);
            }
        });

        Log.i(TAG, "onBindViewHolder: ");


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }



    class MealHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView ivPhoto;
        Button add;
        public MealHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCatTitle);
            ivPhoto = itemView.findViewById(R.id.circularImageView);
            add = itemView.findViewById(R.id.btnAdd);
        }
    }
}
