package com.example.foodplanner.WeaklyPlan.presenter;

import android.util.Log;

import com.example.foodplanner.WeaklyPlan.view.PlanView;
import com.example.foodplanner.model.Plan;
import com.example.foodplanner.model.RepositoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenterImp implements PlanPresenterInterface {

    RepositoryInterface repo;
    PlanView view;
    private static final String TAG = "PlanPresenterImp";

    public PlanPresenterImp(RepositoryInterface repo, PlanView view) {
        this.repo = repo;
        this.view = view;
    }


    @Override
    public void showPlan() {
        repo.getPlan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(plans -> {
                    view.showPlans(plans);
                    Log.i(TAG, "showPlan: success");
                });
    }

    @Override
    public void deletePlan(Plan plan) {
        repo.deletePlan(plan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.removePlan(plan),
                        error -> Log.i(TAG, "removeFrom plan: Error")
                );
    }
}
