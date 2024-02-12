package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoriesResponse;
import com.example.foodplanner.model.CountryResponse;
import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


// Http Request Needed//
public interface MealService {
    @GET("random.php")
    Call<MealResponse> getRandomMeals();

    @GET("categories.php")
    Call<CategoriesResponse> getCategories();

    @GET("list.php")
    Call<CountryResponse> getCountry(@Query("a") String country);


}
