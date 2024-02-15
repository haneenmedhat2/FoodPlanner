package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.Meals;

@Database(entities = {Meals.class},version = 1)
public abstract class MealDatabase extends RoomDatabase{
        private static MealDatabase appDatabase= null;

        public abstract  DAO getAllMeals();

        public static synchronized MealDatabase getInstance(Context context){
            if(appDatabase== null){
                appDatabase= Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class,"meal_database").build();

            }
            return appDatabase;
        }
}
