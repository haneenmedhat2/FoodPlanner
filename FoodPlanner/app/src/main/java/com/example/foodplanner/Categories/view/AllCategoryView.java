package com.example.foodplanner.Categories.view;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meals;

import java.util.List;

public interface AllCategoryView {

    public void getByCategoryName(List<Meals> meals);
    public void addData(Meals meals);
}
