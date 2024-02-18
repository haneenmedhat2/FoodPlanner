package com.example.foodplanner.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlanDAO {

    //Plans
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable inserPlan(Plan plan);


    @Delete
    Completable deletePlan(Plan plan);

        @Query("SELECT * from `plan`")
    Flowable<List<Plan>> getPlan() ;


}
