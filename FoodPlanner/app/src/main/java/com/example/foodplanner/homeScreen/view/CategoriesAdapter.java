package com.example.foodplanner.homeScreen.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Categories.OnCategoryclickListener;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Categories;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>{

        private List<Categories> meals;
        private Context context;

        private OnCategoryclickListener listener;
        private static final String TAG = "RandomAdapter";

        public CategoriesAdapter(List<Categories> meals, Context context) {
            this.meals = meals;
            this.context = context;
        }


        public void setList(List<Categories> updateMeals){
            this.meals=updateMeals;
        }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_card, parent, false);
        CategoryHolder categoryHolder= new CategoryHolder(view);
        Log.i(TAG, "onCreateViewHolder: Category");
        return categoryHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Categories categories = meals.get(position);
        holder.tvTitle.setText(categories.getStrCategory());

        String url = categories.getStrCategoryThumb();

        Glide.with(context).load(url).into(holder.ivPhoto);

        Log.i(TAG, "onBindViewHolder:Category ");

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    class CategoryHolder extends RecyclerView.ViewHolder{

            TextView tvTitle;
            ImageView ivPhoto;
            public CategoryHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvCatTitle);
                ivPhoto = itemView.findViewById(R.id.imCategory);
            }
        }
}

