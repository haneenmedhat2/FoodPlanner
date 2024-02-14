package com.example.foodplanner.model;

import java.util.List;

public class IngredientResponse {
    private List<Ingredients> ingredientsList;

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
