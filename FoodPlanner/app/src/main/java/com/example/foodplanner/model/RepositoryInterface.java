package com.example.foodplanner.model;

import com.example.foodplanner.network.NetworkCallback;

public interface RepositoryInterface {
    public void getRandomMeal(NetworkCallback networkCallback);

    public void getCategories(NetworkCallback networkCallback);

    public void getCountry(NetworkCallback networkCallback);
}
