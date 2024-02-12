package com.example.foodplanner.network;

import android.content.Context;
import android.icu.text.UFormat;
import android.util.Log;

import com.example.foodplanner.model.CategoriesResponse;
import com.example.foodplanner.model.CountryResponse;
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
    public void networkRandomMeals(NetworkCallback networkCallback) {

      /*
        Call<MealResponse> mealModelResponseSingle= mealService.getRandomMeals();
        mealModelResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .repeat(5)
                .doOnNext(e-> list.addAll(e.getMeal()))
                .doOnComplete(()->networkCallback.onSuccessMeal(list))
                .subscribe();*/
        Call<MealResponse> mealModelResponseSingle= mealService.getRandomMeals();
        mealModelResponseSingle.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    networkCallback.onSuccessMeal(response.body().getMeal());
                    Log.i(TAG, "onResponse:network Random Meal "+response.body());
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                networkCallback.onFaild(t.getMessage());
            }

        });
    }

    @Override
    public void networkCategories(NetworkCallback networkCallback) {

        Call<CategoriesResponse> call = mealService.getCategories();

        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful()) {
                    networkCallback.onSuccessCategories(response.body().getCategories());
                    Log.i(TAG, "onResponse:networkCategories "+response.body());
                }
            }
            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                networkCallback.onFaild(t.getMessage());
            }
        });

    }

    @Override
    public void networkCountry(NetworkCallback networkCallback) {

        Call<CountryResponse> call = mealService.getCountry("list");

        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful()&& response.body() != null) {
                    networkCallback.onSuccessCountry(response.body().getCountries());
                    Log.i(TAG, "onResponse: Country "+response.body());
                }
                else {
                    networkCallback.onFaild("Received null country list");
                }
            }
            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: Country" + t.getMessage());
                networkCallback.onFaild(t.getMessage());
            }
        });
    }
}
