package com.example.foodplanner.network;

import android.content.Context;
import android.icu.text.UFormat;
import android.util.Log;

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

        File cacheDirectory = new File(context.getCacheDir(), "offline_cache_directory");
        Cache cache = new Cache(cacheDirectory,100 *1024 * 1024);

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

        List<Meals> list=new ArrayList<>();
        Flowable<MealResponse> mealModelResponseSingle= mealService.getRandomMeals();
        mealModelResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .repeat(10)
                .doOnNext(e-> list.addAll(e.getMeal()))
                .doOnComplete(()->networkCallback.onSuccessMeal(list))
                .subscribe();
        }
}
