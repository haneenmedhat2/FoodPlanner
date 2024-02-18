package com.example.foodplanner.search.view;

import com.example.foodplanner.model.Meals;

public interface OnSearchClickListener {
    public void onMealClick(Meals meal);
    public void categoryOnClick(String category);
    public void countryOnClick(String country);
    public void IngredientyOnClick(String ingredient);

}
