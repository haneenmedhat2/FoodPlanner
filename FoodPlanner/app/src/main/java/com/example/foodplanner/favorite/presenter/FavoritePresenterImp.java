package com.example.foodplanner.favorite.presenter;

import android.util.Log;

import com.example.foodplanner.favorite.view.FavoriteView;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.RepositoryInterface;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImp implements FavoritePresenterInterface {

    FavoriteView view;
    RepositoryInterface repo;
    private static final String TAG = "FavoritePresenterImp";

    public FavoritePresenterImp(FavoriteView view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void getAllFav() {
        repo.getAllMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(meals -> {
                    view.ShowAll(meals);
                });
    }

    @Override
    public void deleteFav(Meals meals) {
        repo.deleteMeal(meals)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.removeMeal(meals),
                        error -> Log.i(TAG, "removeFromFav: Error")
                );
    }
}
