package com.example.foodplanner.search.view;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meals;

import java.util.List;

public interface Searchview {
    public void getAllMeals(List<Meals> list);
    public void getByLetter(List<Meals> list);
    public void getCategories(List<Categories> list);
    public void getByCategoryName(List<Meals> meals);

    public void addData(Meals meals);
    void getCountry(List<Meals> countryList);
    public void getCountryName(List<Meals> list);
}
