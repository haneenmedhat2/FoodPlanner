package com.example.foodplanner.country.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.country.presenter.AllCountryImp;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.homeScreen.presenter.HomePresenterImp;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.ArrayList;
import java.util.List;

public class AllCountryFragment extends Fragment implements AllCountryView,OnCountryClickListener {
    private static final String TAG = "AllCountryFragment";

    AllCountryImp countryImp;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    AllCountryAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.hide();
        String value=getArguments().getString("MEAL_AREA");
        Log.i(TAG, "onViewCreated: "+value);
        countryImp= new AllCountryImp(Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()), MealLocalDataSourceImp.getInstance(getContext()) ,view.getContext()),this);
        countryImp.getCountryName(value);


        recyclerView= view.findViewById(R.id.rvAllCountry);
        gridLayoutManager=new GridLayoutManager(getContext(),2,RecyclerView.HORIZONTAL,false);////
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new AllCountryAdapter(getContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);






    }

    @Override
    public void getCountryName(List<Meals> list) {
        adapter.setMeals(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void addData(Meals meals) {
        countryImp.addToFav(meals);
        Toast.makeText(getContext(),"Data added successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(Meals meal) {
      addData(meal);
    }
}