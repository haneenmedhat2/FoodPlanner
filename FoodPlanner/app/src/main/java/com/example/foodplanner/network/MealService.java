package com.example.foodplanner.network;

import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;


//Request Needed//
public interface MealService {
    @GET("random.php")
    Flowable<MealResponse> getRandomMeals();
}
