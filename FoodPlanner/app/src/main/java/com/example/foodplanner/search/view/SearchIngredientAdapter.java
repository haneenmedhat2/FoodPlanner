package com.example.foodplanner.search.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Item.view.IngredientAdapter;
import com.example.foodplanner.Item.view.OnIngredientClickListener;
import com.example.foodplanner.R;
import com.example.foodplanner.model.AllMeals;
import com.example.foodplanner.model.IngredientItem;
import com.example.foodplanner.model.Meals;

import java.util.ArrayList;
import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.IngredientlHolder> {
     List<IngredientItem> ingredientItems;

    private List<AllMeals> meals;
    private Context context;

    private OnSearchClickListener listener;

    private static final String TAG = "IngredientAdapter";

    public SearchIngredientAdapter(List<AllMeals> meals, Context context,OnSearchClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.listener=listener;

    }

    public void setIngredient(List<AllMeals> meals){ this.meals=meals;}


    @NonNull
    @Override
    public IngredientlHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient_card, parent, false);
        IngredientlHolder holder= new IngredientlHolder(view);
        Log.i(TAG, "onCreateViewHolder: Ingredient ");
        return holder;    }

    @Override
    public void onBindViewHolder(@NonNull IngredientlHolder holder,  @SuppressLint("RecyclerView")int position) {
        AllMeals meal1 = meals.get(position);
        holder.tvTitle.setText(meal1.getStrIngredient());

        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+meal1.getStrIngredient()+ ".png")
                .apply(new RequestOptions().override(holder.ivPhoto.getWidth(),holder.ivPhoto.getHeight()))
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.ivPhoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.IngredientyOnClick(meals.get(position).getStrIngredient());
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class IngredientlHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivPhoto;
        CardView cardView;

        public IngredientlHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvIngredientType);
            ivPhoto = itemView.findViewById(R.id.imageIngredient);
            cardView = itemView.findViewById(R.id.in_card);

        }
    }
}
