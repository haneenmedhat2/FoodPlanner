package com.example.foodplanner.homeScreen.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Item.view.ItemActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.country.view.AllCountryFragment;
import com.example.foodplanner.model.Country;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.view.WelcomeActivity;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder>{
      private List<Meals> countryList;
    private  Context context;
    private static final String TAG = "CountryAdapter";

    String [] arrayImage={
          "https://upload.wikimedia.org/wikipedia/commons/b/bc/Flag_of_the_United_States_%281795-1818%29.jpg",
        "https://www.worldometers.info/img/flags/uk-flag.gif",
        "https://www.worldometers.info/img/flags/ca-flag.gif",
        "https://www.worldometers.info/img/flags/ch-flag.gif",
        "https://www.worldometers.info/img/flags/hr-flag.gif",
        "https://www.worldometers.info/img/flags/nl-flag.gif",
        "https://www.worldometers.info//img/flags/small/tn_eg-flag.gif",
            "https://www.mappng.com/png-flags/2021-07-12542Philippines-flag.png",
        "https://www.worldometers.info//img/flags/small/tn_fr-flag.gif",
        "https://www.worldometers.info/img/flags/gr-flag.gif",
        "https://www.worldometers.info/img/flags/in-flag.gif",
        "https://www.worldometers.info/img/flags/ei-flag.gif",
        "https://www.worldometers.info/img/flags/it-flag.gif",
        "https://www.worldometers.info/img/flags/jm-flag.gif",
        "https://www.worldometers.info/img/flags/ja-flag.gif",
        "https://www.worldometers.info/img/flags/ke-flag.gif",
        "https://www.worldometers.info/img/flags/my-flag.gif",
        "https://www.worldometers.info/img/flags/mx-flag.gif",
        "https://www.worldometers.info/img/flags/mo-flag.gif",
        "https://www.worldometers.info/img/flags/pl-flag.gif",
       "https://www.worldometers.info/img/flags/po-flag.gif",
        "https://www.worldometers.info/img/flags/rs-flag.gif",
        "https://www.worldometers.info/img/flags/sp-flag.gif",
        "https://www.worldometers.info/img/flags/th-flag.gif",
        "https://www.worldometers.info/img/flags/ts-flag.gif",
        "https://www.worldometers.info/img/flags/tu-flag.gif",
        "https://www.freepnglogos.com/uploads/globe-png/globe-icon-aquanox-icons-softiconsm-34.png",
        "https://www.worldometers.info/img/flags/vm-flag.gif"
    };


    public CountryAdapter(List<Meals> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    public void setList(List<Meals> countryUpdate){
        this.countryList= countryUpdate;
    }


    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.country_card, parent, false);
        CountryHolder countryHolder= new CountryHolder(view);
        Log.i(TAG, "onCreateViewHolder: country");
        return countryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        Meals country = countryList.get(position);
        holder.tvTitle.setText(country.getStrArea());
        Glide.with(context).load(arrayImage[position]).error(R.drawable.loading)
                .into(holder.ivPhoto);
        Log.i(TAG, "onBindViewHolder:country ");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WelcomeActivity.guest==false){
                    Toast.makeText(v.getContext(),"Please Signup First" ,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putString("MEAL_AREA", country.getStrArea());
                    Navigation.findNavController(v).navigate(R.id.action_homeFragment2_to_allCountryFragment, bundle);
                }



            }
        });
    }

    @Override
    public int getItemCount() {
            return countryList.size();
    }

    class CountryHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView ivPhoto;
        CardView cardView;
        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCountryName);
            ivPhoto = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.countryCard);
        }
    }
}
