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
import com.example.foodplanner.R;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder>{
     List<Country> countryList;
    Context context;
    private static final String TAG = "CountryAdapter";

    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    public void setList(List<Country> countryUpdate){
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
        Country country = countryList.get(position);
        holder.tvTitle.setText(country.getStrArea());

        String url = "https://www.worldometers.info/img/flags/us-flag.gif";

        Glide.with(context).load(url).into(holder.ivPhoto);

        Log.i(TAG, "onBindViewHolder:country ");
    }

    @Override
    public int getItemCount() {
            return countryList.size();
    }

    class CountryHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView ivPhoto;
        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCountryTitle);
            ivPhoto = itemView.findViewById(R.id.imCountry);
        }
    }
}
