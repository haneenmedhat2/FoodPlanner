package com.example.foodplanner.db;

import android.content.Context;

import com.example.foodplanner.model.Meals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSourceImp implements MealLocalDataSource{

    private  DAO dao;
    private  static MealLocalDataSourceImp dataSourceImp=null;

    private MealLocalDataSourceImp(Context context){
        MealDatabase db= MealDatabase.getInstance(context.getApplicationContext());
        dao= db.getAllMeals();
    }

    public static MealLocalDataSourceImp getInstance(Context context){
        if(dataSourceImp==null){
            dataSourceImp=new MealLocalDataSourceImp(context);
        }
        return dataSourceImp;
    }

    @Override
    public Completable insertMeal(Meals meals) {
        return  dao.inserMeal(meals).subscribeOn(Schedulers.io());

    }

    @Override
    public Completable deleteMeal(Meals meals) {
        return  dao.deleteMeal(meals).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Meals>> getAllStoredMeals() {
        return dao.getAllMeals().subscribeOn(Schedulers.io());
    }


}
