package com.example.foodplanner.Item.presenter;

import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Plan;

import io.reactivex.rxjava3.core.Completable;

public interface ItemPresenterInterface {

    public void getByMealName( String mealName);
    public void addToFav(Meals meals);

    public void inserPlan(Plan plan);
}
