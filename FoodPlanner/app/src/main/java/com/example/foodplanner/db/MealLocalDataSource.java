package com.example.foodplanner.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {

    //meals
    Completable insertMeal(Meals meals);
    Completable deleteMeal(Meals meals);
    Flowable<List<Meals>> getAllStoredMeals();


    //plans
    Completable inserPlans(Plan plan);
    Completable deletePlans(Plan plan);
    Flowable<List<Plan>> getPlanByName() ;

}
