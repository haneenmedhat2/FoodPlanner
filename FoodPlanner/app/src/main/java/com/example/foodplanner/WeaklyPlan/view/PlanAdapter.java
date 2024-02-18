package com.example.foodplanner.WeaklyPlan.view;

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
import com.example.foodplanner.model.Plan;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanHolder>{

    Context context;
    List<Plan> plans;
    OnPlanClickListener listener;

    public PlanAdapter(Context context, List<Plan> plans, OnPlanClickListener listener) {
        this.context = context;
        this.plans = plans;
        this.listener = listener;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.inspiration_card,parent,false);
        PlanHolder holder= new PlanHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
        Plan plan= plans.get(position);
        holder.textView.setText(plan.getStrMeal());

        Glide.with(context).load(plan.getStrMealThumb())
                .error(R.drawable.loading).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ItemActivity.class);
                intent.putExtra("NAME_OF_MEAL",plan.getStrMeal());
                context.startActivity(intent);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlanClickRemove(plan);
                Toast.makeText(v.getContext(),"Plan deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    class PlanHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;
        ImageView button;

        public PlanHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tvCatTitle);
            imageView=itemView.findViewById(R.id.circularImageView);
            cardView=itemView.findViewById(R.id.card_inspire);
            button=itemView.findViewById(R.id.btnAddToFav);

        }
    }
}
