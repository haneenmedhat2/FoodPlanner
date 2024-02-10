package com.example.foodplanner.homeScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.homeScreen.presenter.HomePresenterImp;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class HomeFragment extends Fragment implements RandomView,onMealClickListener{

    RecyclerView recyclerView;
    RandomAdapter randomAdapter;
    HomePresenterImp presenterImp;

    LinearLayoutManager mealLayoutManager;


    private static final String TAG = "AllProduct";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvDaily);
        mealLayoutManager = new LinearLayoutManager(view.getContext());

        randomAdapter = new RandomAdapter(new ArrayList<>(),view.getContext(),this);
        presenterImp=new HomePresenterImp(this, Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()),
                view.getContext()));
        recyclerView.setLayoutManager(mealLayoutManager);
        recyclerView.setAdapter(randomAdapter);
        recyclerView.setHasFixedSize(true);
        mealLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        presenterImp.getRandomMeal();


    }

    @Override
    public void getRandomMeal(List<Meals> mealsList) {
        randomAdapter.setList(mealsList);
        randomAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(Meals meal) {

    }
}