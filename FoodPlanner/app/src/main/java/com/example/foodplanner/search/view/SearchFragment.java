package com.example.foodplanner.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Categories.presenter.AllCategoryImp;
import com.example.foodplanner.Categories.view.AllCategoriesAdapter;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.favorite.view.FavoriteAdapter;
import com.example.foodplanner.homeScreen.presenter.HomePresenterImp;
import com.example.foodplanner.homeScreen.view.RandomAdapter;
import com.example.foodplanner.model.AllMeals;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSourceAPI;
import com.example.foodplanner.search.presenter.SearchPresenterImp;
import com.google.android.material.search.SearchBar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements OnSearchClickListener,Searchview{

    SearchPresenterImp presenterImp;

    List<Meals> emptyNameList;
    List<Meals> filterNameList;
    String search;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    AllMealAdapter adapter;
    SearchCategoryAdapter categoryAdapter;

    SearchAllCatAdapter allCatadapter;

    SearchCountryAdapter countryAdapter;

    SearchAllCountryAdapter allCountryAdapter;

    SearchIngredientAdapter ingredientAdapter;

    SearchAllIngredient allIngredientAdapter;


    TextInputEditText text;
    RadioButton category;
    RadioButton country;
    RadioButton ingredient;
    RadioGroup group ;

    boolean isCategory= false;
    boolean isCountry= false;
    boolean isIngredient= false;
    List<Meals> categoryList= new ArrayList<>();
    List<Meals> countryList= new ArrayList<>();
    List<Meals> ingredientList= new ArrayList<>();






    private static final String TAG = "SearchFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenterImp=new SearchPresenterImp(this, Repository.getInstance(RemoteDataSourceAPI.getInstance(getContext()),
                MealLocalDataSourceImp.getInstance(getContext()),view.getContext()));
        emptyNameList = new ArrayList<>();
        filterNameList = new ArrayList<>();

        category = view.findViewById(R.id.radioCategory);
        country = view.findViewById(R.id.radioCountry);
        ingredient = view.findViewById(R.id.radioIngredients);
        group = view.findViewById(R.id.groupSearch);

        recyclerView= view.findViewById(R.id.rcSearch);
        gridLayoutManager=new GridLayoutManager(getContext(),1,RecyclerView.VERTICAL,false);
        adapter=new AllMealAdapter(emptyNameList,getContext(),this);
        recyclerView.setLayoutManager(gridLayoutManager);

        categoryAdapter=new SearchCategoryAdapter(view.getContext(),new ArrayList<>(),this);
        allCatadapter=new SearchAllCatAdapter(view.getContext(),new ArrayList<>(),this);
        countryAdapter=new SearchCountryAdapter(new ArrayList<>(),view.getContext(),this);
        allCountryAdapter= new SearchAllCountryAdapter(view.getContext(),new ArrayList<>(),this);
        ingredientAdapter= new SearchIngredientAdapter(new ArrayList<>(),view.getContext(),this);
        allIngredientAdapter= new SearchAllIngredient(view.getContext(),new ArrayList<>(),this);

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterImp.getCategories();

            }
        });

        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterImp.getCountry();
            }
        });

        ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterImp.getIngredient();
            }
        });


        text=view.findViewById(R.id.search_bar);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                search = s.toString().toLowerCase();
                if (isCategory == false && isCountry ==false && isIngredient==false) {
                    presenterImp.getByLetter(search);
                }
                if(isCategory){
                    filterCategoryMeals(search);
                }
                if(isCountry){
                    filterCountryMeals(search);
                }
                if (isIngredient){
                    filterIngredientMeals(search);
                }



            }
        });
    }

    @Override
    public void getByLetter(List<Meals> list) {
        filterNameList=list;
        Observable.fromIterable(filterNameList)
                .filter(meals -> meals.getStrMeal().toLowerCase().startsWith(search))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filteredNames -> {
                    emptyNameList.clear();
                    emptyNameList.addAll(filteredNames);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                });
    }

    public void filterCategoryMeals(String searchQuery) {

        Observable.fromIterable(categoryList)
                .filter(meal -> meal.getStrMeal().toLowerCase().contains(searchQuery))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filteredMeals -> {
                    allCatadapter.setAllCategory(filteredMeals);
                    allCatadapter.notifyDataSetChanged();
                });
    }

    public void filterCountryMeals(String searchQuery) {

        Observable.fromIterable(countryList)
                .filter(meal -> meal.getStrMeal().toLowerCase().contains(searchQuery))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filteredMeals -> {
                    allCountryAdapter.setMeals(filteredMeals);
                    allCountryAdapter.notifyDataSetChanged();
                });
    }
    public void filterIngredientMeals(String searchQuery) {

        Observable.fromIterable(ingredientList)
                .filter(meal -> meal.getStrMeal().toLowerCase().contains(searchQuery))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filteredMeals -> {
                    allIngredientAdapter.setMeals(filteredMeals);
                    allIngredientAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void getCategories(List<Categories> list) {
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setList(list);
        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void getByCategoryName(List<Meals> meals) {
        categoryList=meals;
        recyclerView.setAdapter(allCatadapter);
      allCatadapter.setAllCategory(meals);
      allCatadapter.notifyDataSetChanged();

    }

    @Override
    public void addData(Meals meals) {
        presenterImp.addMeals(meals);
    }

    @Override
    public void getCountry(List<Meals> countryList) {
        recyclerView.setAdapter(countryAdapter);
        countryAdapter.setList(countryList);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCountryName(List<Meals> list) {
        countryList=list;
        recyclerView.setAdapter(allCountryAdapter);
        allCountryAdapter.setMeals(list);
        allCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getIngredident(List<AllMeals> list) {
        recyclerView.setAdapter(ingredientAdapter);
        ingredientAdapter.setIngredient(list);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void getByIngredient(List<Meals> list) {
        ingredientList=list;
        recyclerView.setAdapter(allIngredientAdapter);
        allIngredientAdapter.setMeals(list);
        allIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(Meals meal) {
      addData(meal);
    }

    @Override
    public void categoryOnClick(String category) {
        presenterImp.getMealByCategory(category);
        isCategory=true;

    }

    @Override
    public void countryOnClick(String country) {
        presenterImp.getCountryName(country);
        isCountry=true;
    }

    @Override
    public void IngredientyOnClick(String ingredient) {
        presenterImp.getIngredientByName(ingredient);
        isIngredient=true;
    }

    @Override
    public void getAllMeals(List<Meals> list) {

    }


}