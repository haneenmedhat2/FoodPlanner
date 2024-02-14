package com.example.foodplanner.model;

import android.content.Context;

import com.example.foodplanner.network.RemoteDataSourceAPI;

import io.reactivex.rxjava3.core.Observable;

public class Repository implements RepositoryInterface{
    //singleton//
    RemoteDataSourceAPI remoteDataSourceAPI;
    Context context;

    private static Repository repo=null;

    private Repository( RemoteDataSourceAPI remoteDataSourceAPI, Context context){
        this.remoteDataSourceAPI=remoteDataSourceAPI;
        this.context=context;
    }

    public static Repository getInstance( RemoteDataSourceAPI remoteDataSourceAPI, Context context){
        if(repo== null){
            repo= new Repository(remoteDataSourceAPI,context);
        }
        return repo;
    }


    @Override
    public Observable getRandomMeal() {
        return  remoteDataSourceAPI.networkRandomMeals();
    }

    @Override
    public Observable getCategories() {
        return remoteDataSourceAPI.networkCategories();
    }

    @Override
    public Observable getCountry() {
        return  remoteDataSourceAPI.networkCountry();
    }

    @Override
    public Observable getIngredients() {
        return remoteDataSourceAPI.networkIngredient();
    }

    @Override
    public Observable getByMealName(String mealName) {
        return  remoteDataSourceAPI.networkGetMealByName(mealName);
    }

    @Override
    public Observable getByCategoryName(String categoryName) {
        return  remoteDataSourceAPI.networkGetMealByCategory(categoryName);
    }
}
