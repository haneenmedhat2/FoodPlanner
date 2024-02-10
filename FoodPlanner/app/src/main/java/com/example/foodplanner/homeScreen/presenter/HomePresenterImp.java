package com.example.foodplanner.homeScreen.presenter;

import android.util.Log;

import com.example.foodplanner.homeScreen.view.RandomView;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class HomePresenterImp implements HomePresenter, NetworkCallback {

    RandomView view;
    RepositoryInterface repo;
    private static final String TAG = "HomePresenterImp";


    public HomePresenterImp(RandomView view, RepositoryInterface repo) {
        this.view = view;
        this.repo=repo;
    }

    @Override
    public void getRandomMeal() {
        repo.getRandomMeal(this);
    }

    @Override
    public void onSuccessMeal(List<Meals> mealModel) {
        view.getRandomMeal(mealModel);

    }

    @Override
    public void onFaild(String msg) {
        Log.i(TAG, "onFaild:Error when get data in home :"+msg );
    }
}
