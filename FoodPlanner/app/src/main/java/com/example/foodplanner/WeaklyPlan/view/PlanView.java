package com.example.foodplanner.WeaklyPlan.view;

import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Plan;

import java.util.List;

public interface PlanView {
    public  void showPlans(List<Plan> list);
    public void removePlan(Plan plan);
}
