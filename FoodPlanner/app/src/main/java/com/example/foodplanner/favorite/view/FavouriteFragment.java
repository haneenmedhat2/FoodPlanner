package com.example.foodplanner.favorite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.country.presenter.AllCountryImp;
import com.example.foodplanner.country.view.AllCountryAdapter;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.favorite.presenter.FavoritePresenterImp;
import com.example.foodplanner.homeScreen.view.CountryAdapter;
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
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.hide();
        presenterImp= new FavoritePresenterImp(this, Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()),
                MealLocalDataSourceImp.getInstance(getContext()),view.getContext()));

        recyclerView= view.findViewById(R.id.rvAllFav);
        gridLayoutManager=new GridLayoutManager(getContext(),1,RecyclerView.VERTICAL,false);
        adapter=new FavoriteAdapter(getContext(),this,new ArrayList<>());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        presenterImp.getAllFav();





    }

    @Override
    public void ShowAll(List<Meals> meals) {
        adapter.setMeals(meals);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void removeMeal(Meals meals) {
        presenterImp.deleteFav(meals);
    }

    @Override
    public void onMealClickRemove(Meals meal) {
        removeMeal(meal);
    }
}