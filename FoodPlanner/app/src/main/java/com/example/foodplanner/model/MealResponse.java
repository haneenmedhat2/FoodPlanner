package com.example.foodplanner.model;

import java.util.List;

public class MealResponse {
   private List<Meals> meals;

    public MealResponse(List<Meals> meals){
        this.meals=meals;
    }

    public List<Meals> getMeal(){
        return meals;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }
}
