package com.example.foodplanner.model;

import android.content.Context;

import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class Repository implements RepositoryInterface{
    //singleton//
    RemoteDataSourceAPI remoteDataSourceAPI;
    MealLocalDataSourceImp mealLocalDataSourceImp;
    Context context;

    private static Repository repo=null;

    private Repository( RemoteDataSourceAPI remoteDataSourceAPI, MealLocalDataSourceImp mealLocalDataSourceImp,Context context){
        this.remoteDataSourceAPI=remoteDataSourceAPI;
        this.mealLocalDataSourceImp=mealLocalDataSourceImp;
        this.context=context;
    }

    public static Repository getInstance( RemoteDataSourceAPI remoteDataSourceAPI, MealLocalDataSourceImp mealLocalDataSourceImp,Context context){
        if(repo== null){
            repo= new Repository(remoteDataSourceAPI,mealLocalDataSourceImp,context);
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

    @Override
    public Observable getByCountryName(String countryName) {
        return  remoteDataSourceAPI.networkGetMealByCountry(countryName);
    }

    @Override
    public Observable getByFirstLetter(String letter) {
        return remoteDataSourceAPI.NetworkGetByFirstLetter(letter);
    }

    @Override
    public Observable getByIngredientName(String name) {
        return remoteDataSourceAPI.NetworkGetByIngredientName(name);
    }

    @Override
    public Completable addMeal(Meals meals) {
        return mealLocalDataSourceImp.insertMeal(meals);
    }

    @Override
    public Completable deleteMeal(Meals meals) {
        return mealLocalDataSourceImp.deleteMeal(meals);
    }

    @Override
    public Flowable<List<Meals>> getAllMeals() {
        return mealLocalDataSourceImp.getAllStoredMeals();
    }

    @Override
    public Completable inserPlan(Plan plan) {
        return  mealLocalDataSourceImp.inserPlans(plan);
    }

    @Override
    public Completable deletePlan(Plan plan) {
        return mealLocalDataSourceImp.deletePlans(plan);
    }

    @Override
    public Flowable<List<Plan>> getPlan() {
        return  mealLocalDataSourceImp.getPlanByName();
    }
}
