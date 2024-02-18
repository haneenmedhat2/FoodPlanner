package com.example.foodplanner.model;

import java.util.List;

public class AllMealResponse {
    private List<AllMeals> meals;

    public AllMealResponse(List<AllMeals> meals) {
        this.meals = meals;
    }

    public List<AllMeals> getMeals() {
        return meals;
    }

    public void setMeals(List<AllMeals> meals) {
        this.meals = meals;
    }
}
