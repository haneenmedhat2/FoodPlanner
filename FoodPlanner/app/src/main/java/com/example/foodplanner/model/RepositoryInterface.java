package com.example.foodplanner.model;

import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    public Observable getRandomMeal();

    public Observable getCategories();

    public Observable getCountry();

    public Observable getIngredients();

    public Observable getByMealName(String mealName);

}
