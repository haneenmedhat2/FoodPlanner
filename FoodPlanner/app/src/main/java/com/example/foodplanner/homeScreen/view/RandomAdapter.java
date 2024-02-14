package com.example.foodplanner.homeScreen.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Item.view.ItemActivity;
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


        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.ivPhoto.getWidth(),holder.ivPhoto.getHeight()))
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.ivPhoto);

        holder.cardView.setOnClickListener(v -> {
            Intent myIntent =new Intent(context, ItemActivity.class);
            myIntent.putExtra("NAME_OF_MEAL",meals.get(position).getStrMeal());
            context.startActivity(myIntent);
        });
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

        CardView cardView;
        TextView tvTitle;
        ImageView ivPhoto;
        Button add;
        public MealHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCatTitle);
            ivPhoto = itemView.findViewById(R.id.circularImageView);
            add = itemView.findViewById(R.id.btnAdd);
            cardView=itemView.findViewById(R.id.card_inspire);
        }
    }
}
