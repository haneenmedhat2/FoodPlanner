package com.example.foodplanner.favorite.view;

import com.example.foodplanner.model.Meals;

import java.util.List;

public interface FavoriteView {

    public void ShowAll(List<Meals> meals);
    public void removeMeal(Meals meals);
}
