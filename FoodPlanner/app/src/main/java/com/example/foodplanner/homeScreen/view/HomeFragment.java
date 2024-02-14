package com.example.foodplanner.homeScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.homeScreen.presenter.HomePresenterImp;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Country;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeView,onMealClickListener{

    RecyclerView recyclerView;
    RandomAdapter randomAdapter;
    HomePresenterImp presenterImp;
    LinearLayoutManager mealLayoutManager;


    RecyclerView recyclerViewCategoy;
    CategoriesAdapter categoriesAdapter;
    LinearLayoutManager categoryLayoutManager;


    RecyclerView recyclerViewCountry;
    CountryAdapter countryAdapter;
    LinearLayoutManager countryLayoutManager;
    String list;


    private static final String TAG = "AllProduct";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenterImp=new HomePresenterImp(this, Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()),
                view.getContext()));

        recyclerView = view.findViewById(R.id.rvDaily);
        mealLayoutManager = new LinearLayoutManager(view.getContext());
        randomAdapter = new RandomAdapter(new ArrayList<>(),view.getContext(),this);
        recyclerView.setLayoutManager(mealLayoutManager);
        recyclerView.setAdapter(randomAdapter);
        recyclerView.setHasFixedSize(true);
        mealLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        presenterImp.getRandomMeal();


        recyclerViewCategoy = view.findViewById(R.id.rvCategory);
        categoryLayoutManager = new LinearLayoutManager(view.getContext());
        categoriesAdapter = new CategoriesAdapter(new ArrayList<>(),view.getContext());
        recyclerViewCategoy.setLayoutManager(categoryLayoutManager);
        recyclerViewCategoy.setAdapter(categoriesAdapter);
        recyclerViewCategoy.setHasFixedSize(true);
        categoryLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        presenterImp.getCategories();


        recyclerViewCountry = view.findViewById(R.id.rvCountry);
        countryLayoutManager = new LinearLayoutManager(view.getContext());
        countryAdapter = new CountryAdapter(new ArrayList<>(),view.getContext());
        recyclerViewCountry.setLayoutManager(countryLayoutManager);
        recyclerViewCountry.setAdapter(countryAdapter);
        recyclerViewCountry.setHasFixedSize(true);
        countryLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        presenterImp.getCountry();


    }

    @Override
    public void getRandomMeal(List<Meals> mealsList) {
        randomAdapter.setList(mealsList);
        randomAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategories(List<Categories> list) {
        categoriesAdapter.setList(list);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCountry(List<Meals> countryList) {
        countryAdapter.setList(countryList);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(Meals meal) {

    }
}