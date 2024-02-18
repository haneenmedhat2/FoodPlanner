package com.example.foodplanner.homeScreen.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.homeScreen.view.HomeView;
import com.example.foodplanner.model.CategoriesResponse;
import com.example.foodplanner.model.Ingredients;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.RepositoryInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter{

    HomeView view;
    RepositoryInterface repo;
    private static final String TAG = "HomePresenterImp";


    public HomePresenterImp(HomeView view, RepositoryInterface repo) {
        this.view = view;
        this.repo=repo;
    }

    @Override
    public void getRandomMeal() {
        Observable<MealResponse> observable= repo.getRandomMeal();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                       view.getRandomMeal(mealResponse.getMeal());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "onError: Random Meal");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCategories() {
        Observable<CategoriesResponse> observable= repo.getCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoriesResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CategoriesResponse categoriesResponse) {
                        view.getCategories(categoriesResponse.getCategories());
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
    public void getCountry() {
        Observable <MealResponse> observable= repo.getCountry();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        view.getCountry(mealResponse.getMeal());
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
    public void addMeals(Meals meals) {
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
