package com.example.foodplanner.network;

import com.example.foodplanner.model.Meals;

import java.util.List;

public interface NetworkCallback {

    public void onSuccessMeal(List<Meals> mealModel);

    public void onFaild(String msg);
}
