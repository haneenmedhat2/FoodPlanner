package com.example.foodplanner.Item.presenter;

import android.util.Log;

import com.example.foodplanner.Item.view.ItemView;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Plan;
import com.example.foodplanner.model.RepositoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ItemPresenterImp implements ItemPresenterInterface{
    RepositoryInterface repo;
    ItemView view;
    private static final String TAG = "ItemPresenterImp";

    public ItemPresenterImp(RepositoryInterface repo, ItemView view) {
        this.repo = repo;
        this.view = view;
    }


    @Override
    public void getByMealName( String mealName) {
         Observable<MealResponse> mealResponseObservable=repo.getByMealName(mealName);
        mealResponseObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        view.getByMealName(mealResponse.getMeal());

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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.i(TAG, "Meal added to favorites successfully");

                }, error -> {
                    Log.i(TAG, "Error adding Meal to favorites");
                });
    }

    @Override
    public void inserPlan(Plan plan) {
        repo.inserPlan(plan)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.i(TAG, "Plan added  successfully");

                }, error -> {
                    Log.i(TAG, "Error adding plan ");
                });
    }
}
