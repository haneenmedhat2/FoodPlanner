package com.example.foodplanner.model;

import java.util.List;

public class CountryResponse {
    private List<Country> countries;

    public List<Country> getCountries(){
        return countries;
    }
    public void setCountries(List<Country> countries){
        this.countries=countries;
    }
}
