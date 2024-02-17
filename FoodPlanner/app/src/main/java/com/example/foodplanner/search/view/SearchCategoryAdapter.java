package com.example.foodplanner.search.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Categories.view.AllCategoriesAdapter;
import com.example.foodplanner.Categories.view.OnCategoryclickListener;
import com.example.foodplanner.Item.view.ItemActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meals;

import java.util.List;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.AllCategoryHolder>{
    Context context;
    private List<Categories> meals;

    OnSearchClickListener listener;
    private static final String TAG = "AllCategoriesAdapter";

    public SearchCategoryAdapter(Context context, List<Categories> meals, OnSearchClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener=listener;
    }

    public void setList(List<Categories> updateMeals){
        this.meals=updateMeals;
    }


    @NonNull
    @Override
    public AllCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_card, parent, false);
        AllCategoryHolder categoryHolder= new AllCategoryHolder(view);
        Log.i(TAG, "onCreateViewHolder: AllCategory");
        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryHolder holder, @SuppressLint("RecyclerView") int position) {
        Categories categories = meals.get(position);
        holder.tvTitle.setText(categories.getStrCategory());
        String url = categories.getStrCategoryThumb();
        Glide.with(context).load(url).into(holder.ivPhoto);
        Log.i(TAG, "onBindViewHolder:Category ");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.categoryOnClick(meals.get(position).getStrCategory());

            }
        });


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class AllCategoryHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView ivPhoto;
        CardView cardView;

        public AllCategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCatTitle);
            ivPhoto = itemView.findViewById(R.id.imCategory);
            cardView= itemView.findViewById(R.id.category_card);

        }
    }

}
