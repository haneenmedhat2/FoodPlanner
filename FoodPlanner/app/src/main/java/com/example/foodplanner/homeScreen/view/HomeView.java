package com.example.foodplanner.homeScreen.view;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Country;
import com.example.foodplanner.model.Meals;

import java.util.List;

public interface HomeView {
    void getRandomMeal(List<Meals> mealsList);

    void getCategories(List<Categories> list);

    void getCountry(List<Meals> countryList);

}
