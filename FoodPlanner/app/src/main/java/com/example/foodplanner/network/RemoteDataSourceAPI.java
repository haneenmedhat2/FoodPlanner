package com.example.foodplanner.network;

import android.content.Context;
import android.icu.text.UFormat;
import android.util.Log;

import com.example.foodplanner.model.CategoriesResponse;
import com.example.foodplanner.model.CountryResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Meals;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceAPI implements RemoteDataSource{


    private static final String url = "https://www.themealdb.com/api/json/v1/1/";
    private  static RemoteDataSourceAPI client= null;
    private static final String TAG = "MealClient";

     MealService mealService;


    private RemoteDataSourceAPI(Context context){

        File cacheDirectory = new File(context.getCacheDir(), "offlineData");
        Cache cache = new Cache(cacheDirectory,50 *1024 * 1024); //50MB
       /* The cache is used to store responses for offline use,
        reducing the need for network requests when the data is already available locally.*/


        OkHttpClient okHttpClient = new OkHttpClient
                .Builder().cache(cache).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(url).build();

        mealService = retrofit.create(MealService.class);

    }
    public static RemoteDataSourceAPI getInstance(Context context){
        if(client== null){
            client= new RemoteDataSourceAPI(context);
        }

        return client;
    }



    @Override
    public Observable networkRandomMeals() {
        Observable <MealResponse> mealResponseObservable=mealService.getRandomMeals().subscribeOn(Schedulers.io());
        return mealResponseObservable;
    }

    @Override
    public Observable networkCategories() {
        Observable<CategoriesResponse> categoriesResponseObservable = mealService.getCategories().subscribeOn(Schedulers.io());
        return categoriesResponseObservable;
    }

    @Override
    public Observable networkCountry() {
        Observable<MealResponse> mealResponseObservable = mealService.getCountry("list").subscribeOn(Schedulers.io());
        return mealResponseObservable;
    }

    @Override
    public Observable networkIngredient() {
        Observable<IngredientResponse> ingredientResponseObservable= mealService.getIngredients("list").subscribeOn(Schedulers.io());
        return ingredientResponseObservable;
    }

    @Override
    public Observable networkGetMealByName(String mealName) {
        Observable<MealResponse> mealResponseObservable =mealService.getByMealName(mealName).subscribeOn(Schedulers.io());
        return mealResponseObservable;
    }

    @Override
    public Observable networkGetMealByCategory(String categoryName) {
        Observable<MealResponse> mealResponseObservable =mealService.getByCategoryName(categoryName).subscribeOn(Schedulers.io());
        return mealResponseObservable;
    }

    @Override
    public Observable networkGetMealByCountry(String countryName) {
        Observable<MealResponse> mealResponseObservable =mealService.getByCountryName(countryName).subscribeOn(Schedulers.io());
        return mealResponseObservable;
    }

    @Override
    public Observable NetworkGetByFirstLetter(String letter) {
        Observable<MealResponse> mealResponseObservable =mealService.getByFirstLetter(letter).subscribeOn(Schedulers.io());
        return mealResponseObservable;      }
}
