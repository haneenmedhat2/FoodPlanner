package com.example.foodplanner.favorite.presenter;

import com.example.foodplanner.favorite.view.FavoriteView;
import com.example.foodplanner.model.RepositoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImp implements FavoritePresenterInterface {

    FavoriteView view;
    RepositoryInterface repo;

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
}
