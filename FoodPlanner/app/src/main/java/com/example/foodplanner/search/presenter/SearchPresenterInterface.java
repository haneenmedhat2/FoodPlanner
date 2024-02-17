package com.example.foodplanner.search.presenter;

import com.example.foodplanner.model.Meals;

public interface SearchPresenterInterface {

    public void getAllMeals();
    public void getByLetter(String letter);
    public void getCategories();
    public void getMealByCategory(String categoryName);

    public void addMeals(Meals meals);

    public void getCountry();
    public void getCountryName(String countryName);

    public void getIngredient();









}
