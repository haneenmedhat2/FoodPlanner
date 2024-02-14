package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoriesResponse;
import com.example.foodplanner.model.CountryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


// Http Request Needed//
public interface MealService {
    @GET("random.php")
    Observable<MealResponse> getRandomMeals();

    @GET("categories.php")
    Observable<CategoriesResponse> getCategories();

    @GET("list.php")
    Observable<MealResponse> getCountry(@Query("a") String country);

    @GET("list.php")
    Observable<IngredientResponse> getIngredients(@Query("i") String ingredient);

    @GET("search.php")
    Observable<MealResponse> getByMealName(@Query("s") String mealName);

    @GET("filter.php")
    Observable<MealResponse> getByCategoryName(@Query("c") String mealName);



}
