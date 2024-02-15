package com.example.foodplanner.Categories.presenter;

import com.example.foodplanner.model.Meals;

public interface AllCategoryInterface {
    public void getMealByCategory(String categoryName);
    public void addToFav(Meals meals);
}
