package com.example.foodplanner.Categories.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.foodplanner.Item.view.ItemActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.homeScreen.view.CategoriesAdapter;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meals;

import java.util.List;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.AllCategoryHolder>{
    Context context;
    List<Meals> meals;
    private static final String TAG = "AllCategoriesAdapter";

    public AllCategoriesAdapter(Context context, List<Meals> meals) {
        this.context = context;
        this.meals = meals;
    }

    public void setAllCategory(List<Meals> meals){
        this.meals= meals;
    }

    @NonNull
    @Override
    public AllCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.allcategories_card, parent, false);
        AllCategoryHolder categoryHolder= new AllCategoryHolder(view);
        Log.i(TAG, "onCreateViewHolder: AllCategory");
        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryHolder holder, int position) {
        Meals meals1 = meals.get(position);
        holder.tvTitle.setText(meals1.getStrMeal());
        String url = meals1.getStrMealThumb();
        Glide.with(context).load(url).into(holder.ivPhoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ItemActivity.class);
                intent.putExtra("NAME_OF_MEAL",meals1.getStrMeal());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class AllCategoryHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView ivPhoto;
        CardView cardView;
        public AllCategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCatType);
            ivPhoto = itemView.findViewById(R.id.imageAllCat);
            cardView= itemView.findViewById(R.id.allCatCard);

        }
    }
}
