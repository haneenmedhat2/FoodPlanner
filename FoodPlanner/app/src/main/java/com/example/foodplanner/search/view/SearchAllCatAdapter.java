package com.example.foodplanner.search.view;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Categories.view.AllCategoriesAdapter;
import com.example.foodplanner.Categories.view.OnCategoryclickListener;
import com.example.foodplanner.Item.view.ItemActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.view.WelcomeActivity;

import java.util.List;

public class SearchAllCatAdapter extends RecyclerView.Adapter<SearchAllCatAdapter.AllCategoryHolder>{

    Context context;
    List<Meals> meals;

    OnSearchClickListener listener;

    private static final String TAG = "AllCategoriesAdapter";

    public SearchAllCatAdapter(Context context, List<Meals> meals, OnSearchClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener=listener;
    }

    public void setAllCategory(List<Meals> meals){
        this.meals= meals;
    }

    @NonNull
    @Override
    public AllCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.inspiration_card, parent, false);
        AllCategoryHolder categoryHolder= new AllCategoryHolder(view);
        Log.i(TAG, "onCreateViewHolder: AllCategory");
        return categoryHolder;    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryHolder holder, int position) {
        Meals meals1 = meals.get(position);
        holder.tvTitle.setText(meals1.getStrMeal());
        String url = meals1.getStrMealThumb();
        Glide.with(context).load(url).into(holder.ivPhoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WelcomeActivity.guest==false){
                    Toast.makeText(v.getContext(),"Please Signup First" ,
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(context, ItemActivity.class);
                    intent.putExtra("NAME_OF_MEAL",meals1.getStrMeal());
                    context.startActivity(intent);
                }


            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMealClick(meals1);
                Toast.makeText(v.getContext(),"Data added successfully",Toast.LENGTH_SHORT).show();
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
        ImageView button;

        public AllCategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCatTitle);
            ivPhoto = itemView.findViewById(R.id.circularImageView);
            cardView= itemView.findViewById(R.id.card_inspire);
            button=itemView.findViewById(R.id.btnAddToFav);

        }
    }
}
