package com.example.foodplanner.Categories.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Categories.presenter.AllCategoryImp;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesFragment extends Fragment implements AllCategoryView,OnCategoryclickListener {
RecyclerView recyclerView;
AllCategoriesAdapter adapter;
LinearLayoutManager linearLayoutManager;

GridLayoutManager gridLayoutManager;
AllCategoryImp categoryImp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.hide();
        String categoryName= getArguments().getString("key_string_value");
        categoryImp= new AllCategoryImp(Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()), MealLocalDataSourceImp.getInstance(getContext()) ,view.getContext()),this);
        categoryImp.getMealByCategory(categoryName);
        recyclerView= view.findViewById(R.id.rvAllCat);
        adapter=new AllCategoriesAdapter(view.getContext(),new ArrayList<>(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getByCategoryName(List<Meals> meals) {
        adapter.setAllCategory(meals);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void addData(Meals meals) {
        categoryImp.addToFav(meals);
        Toast.makeText(getContext(),"Data added successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(Meals meal) {
        addData(meal);
    }
}