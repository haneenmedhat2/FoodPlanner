package com.example.foodplanner.favorite.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Item.view.ItemActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meals;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.AllFavHolder>{
      Context context;
      OnFavClickListener listener;
      List<Meals> meals;

    public FavoriteAdapter(Context context, OnFavClickListener listener, List<Meals> meals) {
        this.context = context;
        this.listener = listener;
        this.meals = meals;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public AllFavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view= inflater.inflate(R.layout.favourite_card,parent,false);
        AllFavHolder holder= new AllFavHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllFavHolder holder, int position) {
        Meals meal = meals.get(position);
        holder.textView.setText(meal.getStrMeal());


        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.circleImageView.getWidth(),holder.circleImageView.getHeight()))
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.circleImageView);

    /*    holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMealClick(meal);
                Toast.makeText(v.getContext(),"Data Removed successfully",Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AllFavHolder extends RecyclerView.ViewHolder{
         ImageButton button;
         TextView textView;
         CircleImageView circleImageView;

        public AllFavHolder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.removefav);
            textView= itemView.findViewById(R.id.tvFavType);
            circleImageView= itemView.findViewById(R.id.imageAllFav);
        }
    }
}
