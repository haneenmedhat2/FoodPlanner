package com.example.foodplanner.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.NetworkCallback;
import com.example.foodplanner.network.RemoteDataSourceAPI;

import java.util.List;

public class Repository implements RepositoryInterface{
 //singleton//
    RemoteDataSourceAPI remoteDataSourceAPI;

    Context context;

    private static Repository repo=null;

    private Repository( RemoteDataSourceAPI remoteDataSourceAPI, Context context){
        this.remoteDataSourceAPI=remoteDataSourceAPI;
        this.context=context;
    }

    public static Repository getInstance( RemoteDataSourceAPI remoteDataSourceAPI, Context context){
        if(repo== null){
            repo= new Repository(remoteDataSourceAPI,context);
        }
        return repo;
    }


    @Override
    public void getRandomMeal(NetworkCallback networkCallback) {
        remoteDataSourceAPI.networkRandomMeals(networkCallback);
    }
}
