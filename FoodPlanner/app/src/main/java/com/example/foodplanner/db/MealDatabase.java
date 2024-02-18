package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Plan;

@Database(entities = {Meals.class, Plan.class},version = 2)
public abstract class MealDatabase extends RoomDatabase{
        private static MealDatabase appDatabase= null;

        public abstract  DAO getAllMeals();
    public abstract  PlanDAO getPlan();

        public static synchronized MealDatabase getInstance(Context context){
            if(appDatabase== null){
                appDatabase= Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class,"meal_database")
                        .fallbackToDestructiveMigration()
                        .build();

            }
            return appDatabase;
        }
}
