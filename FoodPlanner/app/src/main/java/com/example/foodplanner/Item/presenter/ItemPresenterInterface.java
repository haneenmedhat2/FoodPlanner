package com.example.foodplanner.Item.presenter;

import com.example.foodplanner.model.Meals;

public interface ItemPresenterInterface {

    public void getByMealName( String mealName);
    public void addToFav(Meals meals);
}
