package com.example.foodplanner.network;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Country;
import com.example.foodplanner.model.CountryResponse;
import com.example.foodplanner.model.Meals;

import java.util.List;

public interface NetworkCallback {

    public void onSuccessMeal(List<Meals> mealModel);

    public void onSuccessCategories(List<Categories> categories);

    public void onSuccessCountry(List<Country> countries);

    public void onFaild(String msg);
}
