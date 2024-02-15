package com.example.foodplanner.favorite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.country.presenter.AllCountryImp;
import com.example.foodplanner.country.view.AllCountryAdapter;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.favorite.presenter.FavoritePresenterImp;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavoriteView,OnFavClickListener{
    FavoritePresenterImp presenterImp;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    FavoriteAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterImp= new FavoritePresenterImp(this, Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()),
                MealLocalDataSourceImp.getInstance(getContext()),view.getContext()));
        presenterImp.getAllFav();

        recyclerView= view.findViewById(R.id.rvAllCountry);
        gridLayoutManager=new GridLayoutManager(getContext(),2,RecyclerView.HORIZONTAL,false);////
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new FavoriteAdapter(getContext(),this,new ArrayList<>());
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void ShowAll(List<Meals> meals) {
        adapter.setMeals(meals);
        adapter.notifyDataSetChanged();

    }
}