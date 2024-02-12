package com.example.foodplanner.homeScreen.presenter;

import android.util.Log;

import com.example.foodplanner.homeScreen.view.HomeView;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Country;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class HomePresenterImp implements HomePresenter, NetworkCallback {

    HomeView view;
    RepositoryInterface repo;
    private static final String TAG = "HomePresenterImp";


    public HomePresenterImp(HomeView view, RepositoryInterface repo) {
        this.view = view;
        this.repo=repo;
    }

    @Override
    public void getRandomMeal() {
        repo.getRandomMeal(this);
    }

    @Override
    public void getCategories() {
        repo.getCategories(this);
    }

    @Override
    public void getCountry() {
        repo.getCountry(this);
    }

    @Override
    public void onSuccessMeal(List<Meals> mealModel) {
        view.getRandomMeal(mealModel);

    }

    @Override
    public void onSuccessCategories(List<Categories> categories) {
        view.getCategories(categories);
    }

    @Override
    public void onSuccessCountry(List<Country> countries) {
        view.getCountry(countries);
    }

    @Override
    public void onFaild(String msg) {
        Log.i(TAG, "onFaild:Error when get data in home :"+msg );
    }
}
