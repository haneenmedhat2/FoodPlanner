package com.example.foodplanner.Item.presenter;

import com.example.foodplanner.Item.view.ItemView;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.RepositoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class ItemPresenterImp implements ItemPresenterInterface{
    RepositoryInterface repo;
    ItemView view;

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
}
