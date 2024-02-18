package com.example.foodplanner.search.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.foodplanner.Item.view.ItemActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.view.WelcomeActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAllIngredient extends  RecyclerView.Adapter<SearchAllIngredient.AllIngredientHolder>{

    Context context;
    List<Meals> meals;
    OnSearchClickListener listener;

    public SearchAllIngredient(Context context, List<Meals> meals,  OnSearchClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener=listener;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public AllIngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.inspiration_card,parent,false);
        AllIngredientHolder ingredientHolder= new AllIngredientHolder(view);
        return ingredientHolder;    }

    @Override
    public void onBindViewHolder(@NonNull AllIngredientHolder holder, int position) {
        Meals meals1= meals.get(position);
        holder.textView.setText(meals1.getStrMeal());

        Glide.with(context).load(meals1.getStrMealThumb())
                .error(R.drawable.loading).into(holder.circleImageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WelcomeActivity.guest==false){
                    Toast.makeText(v.getContext(),"Please Signup First" ,
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent= new Intent(context, ItemActivity.class);
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

    class AllIngredientHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView circleImageView;
        CardView cardView;
        ImageView button;

        public AllIngredientHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tvCatTitle);
            circleImageView=itemView.findViewById(R.id.circularImageView);
            cardView=itemView.findViewById(R.id.card_inspire);
            button=itemView.findViewById(R.id.btnAddToFav);

        }
    }
}
