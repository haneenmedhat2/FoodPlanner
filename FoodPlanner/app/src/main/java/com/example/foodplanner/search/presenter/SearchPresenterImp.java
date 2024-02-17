package com.example.foodplanner.search.presenter;

import android.util.Log;

import com.example.foodplanner.model.AllMealResponse;
import com.example.foodplanner.model.CategoriesResponse;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.search.view.Searchview;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenterInterface{

    Searchview view;
    RepositoryInterface repo;
    private static final String TAG = "SearchPresenterImp";

    public SearchPresenterImp(Searchview view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getAllMeals() {
        Observable<MealResponse> observable= repo.getRandomMeal();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        view.getAllMeals(mealResponse.getMeal());
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
    public void getByLetter(String letter) {
        Observable<MealResponse> observable= repo.getByFirstLetter(letter);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        view.getByLetter(mealResponse.getMeal());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "onError: couldnt get data by first letter");

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
    public void getMealByCategory(String categoryName) {
        Observable<MealResponse> observable= repo.getByCategoryName(categoryName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        view.getByCategoryName(mealResponse.getMeal());

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
    public void getIngredient() {
        Observable<AllMealResponse> observable= repo.getIngredients();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllMealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull AllMealResponse mealResponse) {
                        view.getIngredident(mealResponse.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "onError: Ingredient error ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getIngredientByName(String ingredientName) {
        Observable <MealResponse> observable= repo.getByIngredientName(ingredientName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        view.getByIngredient(mealResponse.getMeal());
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
