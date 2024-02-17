package com.example.foodplanner.model;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    public Observable getRandomMeal();

    public Observable getCategories();

    public Observable getCountry();

    public Observable getIngredients();

    public Observable getByMealName(String mealName);

    public Observable getByCategoryName(String categoryName);

    public Observable getByCountryName(String countryName);

    public Observable getByFirstLetter(String letter);


    public Completable addMeal(Meals meals);
    public Completable deleteMeal(Meals meals);

     public Flowable<List<Meals>> getAllMeals();


     //plan
     public Completable inserPlan(Plan plan);
    public Completable deletePlan(Plan plan);

    public Flowable<List<Plan>> getPlan();




}
