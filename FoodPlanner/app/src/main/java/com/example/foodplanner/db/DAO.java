package com.example.foodplanner.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DAO {

        //Meals
        @Query("select * from meal")
        Flowable<List<Meals>> getAllMeals();

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        Completable inserMeal(Meals meals);

        @Delete
        Completable deleteMeal(Meals meals);


}
