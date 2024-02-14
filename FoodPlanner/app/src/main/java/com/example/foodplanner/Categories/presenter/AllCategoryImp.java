package com.example.foodplanner.Categories.presenter;

import com.example.foodplanner.Categories.view.AllCategoryView;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.RepositoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class AllCategoryImp implements AllCategoryInterface{

   RepositoryInterface repo;
    AllCategoryView view;

    public AllCategoryImp(RepositoryInterface repo,AllCategoryView view) {
        this.repo = repo;
        this.view= view;
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
}
