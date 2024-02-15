package com.example.foodplanner.db;

import com.example.foodplanner.model.Meals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {
    Completable insertMeal(Meals meals);
    Completable deleteMeal(Meals meals);
    Flowable<List<Meals>> getAllStoredMeals();

}
