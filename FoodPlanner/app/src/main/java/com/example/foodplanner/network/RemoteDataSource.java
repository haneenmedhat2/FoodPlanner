package com.example.foodplanner.network;

public interface RemoteDataSource {
    public void networkRandomMeals(NetworkCallback networkCallback);

    public void networkCategories(NetworkCallback networkCallback);

    public void networkCountry(NetworkCallback networkCallback);
}
