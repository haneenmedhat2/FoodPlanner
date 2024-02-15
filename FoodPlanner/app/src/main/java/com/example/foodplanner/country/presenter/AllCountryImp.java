package com.example.foodplanner.country.presenter;

import com.example.foodplanner.country.view.AllCountryView;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.RepositoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class AllCountryImp implements AllCountryInterface{
    RepositoryInterface repo;
    AllCountryView view;

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
}
