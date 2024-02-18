package com.example.foodplanner.country.presenter;

import android.util.Log;

import com.example.foodplanner.country.view.AllCountryView;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.RepositoryInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllCountryImp implements AllCountryInterface{
    RepositoryInterface repo;
    AllCountryView view;
    private static final String TAG = "AllCountryImp";

    public AllCountryImp(RepositoryInterface repo, AllCountryView view) {
        this.repo = repo;
        this.view = view;
    }


    @Override
    public void getCountryName(String countryName) {
        Observable<MealResponse> observable= repo.getByCountryName(countryName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        view.getCountryName(mealResponse.getMeal());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addToFav(Meals meals) {
        repo.addMeal(meals)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.i(TAG, "Meal added to favorites successfully");

                }, error -> {
                    Log.i(TAG, "Error adding Meal to favorites");
                });
    }
}
