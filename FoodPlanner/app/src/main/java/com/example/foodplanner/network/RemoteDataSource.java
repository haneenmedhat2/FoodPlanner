package com.example.foodplanner.network;

import io.reactivex.rxjava3.core.Observable;

public interface RemoteDataSource {
    public Observable networkRandomMeals();

    public Observable networkCategories();

    public Observable networkCountry();

    public Observable networkIngredient();

    public Observable networkGetMealByName(String mealName);
    public Observable networkGetMealByCategory(String categoryName);
    public Observable networkGetMealByCountry(String countryName);
}
