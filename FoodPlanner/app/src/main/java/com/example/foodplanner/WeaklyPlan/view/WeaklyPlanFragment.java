package com.example.foodplanner.WeaklyPlan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.WeaklyPlan.presenter.PlanPresenterImp;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.model.Plan;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.ArrayList;
import java.util.List;


public class WeaklyPlanFragment extends Fragment implements PlanView, OnPlanClickListener {

    PlanPresenterImp presenterImp;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    PlanAdapter adapter;
    String dayName;

    RecyclerView sat,sun,mon,tus,wed,thur,fri;
    PlanAdapter satAdapter,sunAdapter,monAdapter,tusAdapter,wenAdapter,thurAdapter,friAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weakly_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

      /*  presenterImp= new PlanPresenterImp(Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()), MealLocalDataSourceImp.getInstance(getContext()) ,view.getContext()),this);

        recyclerView= view.findViewById(id);
        gridLayoutManager=new GridLayoutManager(getContext(),1,RecyclerView.HORIZONTAL,false);
        adapter=new PlanAdapter(getContext(),new ArrayList<>(),this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        presenterImp.showPlan();*/

        sat= view.findViewById(R.id.rvSat);
        sun= view.findViewById(R.id.rvSun);
        mon= view.findViewById(R.id.rvMon);
        tus= view.findViewById(R.id.rvTus);
        wed= view.findViewById(R.id.rvWed);
        thur= view.findViewById(R.id.rvThurs);
        fri= view.findViewById(R.id.rvFriday);

        satAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);
        sunAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);
        monAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);
        tusAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);
        wenAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);
        thurAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);
        friAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);

        //gridLayoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);



        sat.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        sat.setAdapter(satAdapter);

        sun.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        sun.setAdapter(sunAdapter);

        mon.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        mon.setAdapter(monAdapter);

        tus.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        tus.setAdapter(tusAdapter);

        wed.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        wed.setAdapter(wenAdapter);

        thur.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        thur.setAdapter(thurAdapter);

        fri.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false));
        fri.setAdapter(friAdapter);

        presenterImp= new PlanPresenterImp(Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()),
                MealLocalDataSourceImp.getInstance(getContext()) ,view.getContext()),this);

        presenterImp.showPlan();
    }

    @Override
    public void showPlans(List<Plan> list) {
        List<Plan> saturdayPlans = new ArrayList<>();
        List<Plan> sundayPlans = new ArrayList<>();
        List<Plan> mondayPlans = new ArrayList<>();
        List<Plan> tuesdayPlans = new ArrayList<>();
        List<Plan> wednesdayPlans = new ArrayList<>();
        List<Plan> thursdayPlans = new ArrayList<>();
        List<Plan> fridayPlans = new ArrayList<>();

        for (Plan plan : list) {
            String day = plan.getDay();
            switch (day) {
                case "Saturday":
                    saturdayPlans.add(plan);
                    break;
                case "Sunday":
                    sundayPlans.add(plan);
                    break;
                case "Monday":
                    mondayPlans.add(plan);
                    break;
                case "Tuesday":
                    tuesdayPlans.add(plan);
                    break;
                case "Wednesday":
                    wednesdayPlans.add(plan);
                    break;
                case "Thursday":
                    thursdayPlans.add(plan);
                    break;
                case "Friday":
                    fridayPlans.add(plan);
                    break;
            }
            setAdapterData(satAdapter, saturdayPlans);
            setAdapterData(sunAdapter, sundayPlans);
            setAdapterData(monAdapter, mondayPlans);
            setAdapterData(tusAdapter,tuesdayPlans);
            setAdapterData(wenAdapter, wednesdayPlans);
            setAdapterData(thurAdapter, thursdayPlans);
            setAdapterData(friAdapter, fridayPlans);
        }
    }

    @Override
    public void removePlan(Plan plan) {
        presenterImp.deletePlan(plan);
    }

    private void setAdapterData(PlanAdapter adapter, List<Plan> plans) {
        adapter.setPlans(plans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPlanClickRemove(Plan plan) {
        removePlan(plan);
    }
}