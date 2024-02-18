package com.example.foodplanner.homeScreen.presenter;

import com.example.foodplanner.model.Meals;

import io.reactivex.rxjava3.core.Completable;

public interface HomePresenter {
    public void getRandomMeal();

    public void getCategories();

    public void getCountry();

    public void addMeals(Meals meals);

}
