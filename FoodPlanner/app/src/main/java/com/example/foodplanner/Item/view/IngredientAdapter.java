package com.example.foodplanner.Item.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.homeScreen.view.onMealClickListener;
import com.example.foodplanner.model.IngredientItem;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientlHolder>{

    private List<IngredientItem> meals;
    private Context context;

    private OnIngredientClickListener listener;
    private static final String TAG = "RandomAdapter";

    public IngredientAdapter(List<IngredientItem> meals, Context context,OnIngredientClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.listener=listener;
    }

    public void setIngredient(List<IngredientItem> meals){ this.meals=meals;}

    @NonNull
    @Override
    public IngredientlHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient_card, parent, false);
       IngredientlHolder holder= new IngredientlHolder(view);
        Log.i(TAG, "onCreateViewHolder: Ingredient ");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientlHolder holder, int position) {
        IngredientItem meal = meals.get(position);
        holder.tvTitle.setText(meal.getIngredientName());

        Glide.with(context).load(meals.get(position).getIngredientImage())
                .apply(new RequestOptions().override(holder.ivPhoto.getWidth(),holder.ivPhoto.getHeight()))
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.ivPhoto);


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class IngredientlHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivPhoto;


        public IngredientlHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvIngredientType);
            ivPhoto = itemView.findViewById(R.id.imageIngredient);


        }
    }
}
