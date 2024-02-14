package com.example.foodplanner.model;

public class IngredientItem {
    private String ingredientName;
    private String ingredientImage;

    public IngredientItem(String ingredientName,String ingredientImage) {
        this.ingredientName = ingredientName;
        this.ingredientImage= ingredientImage;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientImage() {
        return ingredientImage;
    }

    public void setIngredientImage(String ingredientImage) {
        this.ingredientImage = ingredientImage;
    }
}
