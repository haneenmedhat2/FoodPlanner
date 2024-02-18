package com.example.foodplanner.model;

import java.util.List;

public class CategoriesResponse {

    private List<Categories> categories;


    public CategoriesResponse(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Categories> getCategories(){
        return categories;
    }

    public void setCategories(List<Categories> list){
        categories=list;
    }

}
