package com.example.foodplanner.Item.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Item.presenter.ItemPresenterImp;
import com.example.foodplanner.R;
import com.example.foodplanner.WeaklyPlan.view.DayChooserDialog;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.model.IngredientItem;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Plan;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSourceAPI;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemActivity extends AppCompatActivity implements ItemView,OnIngredientClickListener{
    ItemPresenterImp presenterImp;
    String mealName;
    IngredientAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    TextView itemName,itemPageMealSteps,countryName;
    CircleImageView image;

    ImageButton button;


    private static final String TAG = "ItemActivity";

    String[] videoSplit;
    String videoId;
    YouTubePlayerView videoView ;
    List<IngredientItem> ingredientItems;
    ActionBar actionBar;

    Meals meals;
    Meals myMeal;

    ImageButton btnCalender;

    OnIngredientClickListener listener;

    Plan plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Bundle getData= getIntent().getExtras();
        if(getData != null){
            mealName=getData.getString("NAME_OF_MEAL");
        }
        presenterImp= new ItemPresenterImp(Repository.getInstance(RemoteDataSourceAPI.getInstance(this), MealLocalDataSourceImp.getInstance(this),ItemActivity.this),this);
        presenterImp.getByMealName(mealName);
        listener=this;

        myMeal= new Meals();
        itemName= findViewById(R.id.itemName);
        countryName= findViewById(R.id.countryName);
        itemPageMealSteps=findViewById(R.id.itemPageMealSteps);
        image= findViewById(R.id.image);
        videoView=findViewById(R.id.videoView);

        recyclerView= findViewById(R.id.ingredientRecyclerView);
        linearLayoutManager= new LinearLayoutManager(this);
        adapter= new IngredientAdapter(new ArrayList<>(),this,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);



        plan= new Plan();

        ingredientItems= new ArrayList<>();

        actionBar= getSupportActionBar ();
        actionBar.hide();

        button=findViewById(R.id.btnAddFav);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMealClick(myMeal);
                Toast.makeText(v.getContext(),"Data added successfully",Toast.LENGTH_SHORT).show();
            }
        });

        btnCalender= findViewById(R.id.btnAddCalender);
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDayChooserDialog();
                Log.i(TAG, "onClick: Button calender");
            }
        });
    }

    private void showDayChooserDialog() {
        DayChooserDialog dialog = new DayChooserDialog();
        dialog.setOnDaySelectedListener(new DayChooserDialog.OnDaySelectedListener() {
            @Override
            public void onDaySelected(String selectedDay) {
                Toast.makeText(ItemActivity.this, "Selected Day: " + selectedDay, Toast.LENGTH_SHORT).show();
                plan.setDay(selectedDay);
                presenterImp.inserPlan(plan);
            }
        });
        dialog.show(getSupportFragmentManager(), "DayChooserDialog");
    }

    @Override
    public void getByMealName(List<Meals> list) {
         meals= list.get(0);
         myMeal=meals;

        itemName.setText(meals.getStrMeal());
        countryName.setText(meals.getStrArea());
        itemPageMealSteps.setText(meals.getStrInstructions());
        Glide.with(this).load(meals.getStrMealThumb())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(image);



        Log.i(TAG, "getByMealName: "+meals.getStrYoutube() );

        if(!meals.getStrYoutube().equals("")) {
            videoSplit = meals.getStrYoutube().split("=");
            videoId = videoSplit[1];
            Log.i(TAG, "getByMealName: " + videoId);
            System.out.println(videoId);
            getLifecycle().addObserver(videoView);

            videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        } else{
                Toast.makeText(this,"Error In Youtube Video!",Toast.LENGTH_SHORT).show();
            }


       String img1="https://www.themealdb.com/images/ingredients/"+meals.getStrIngredient1()+ ".png";
        String img2="https://www.themealdb.com/images/ingredients/"+meals.getStrIngredient2()+ ".png";
        String img3="https://www.themealdb.com/images/ingredients/"+meals.getStrIngredient3()+ ".png";
        String img4="https://www.themealdb.com/images/ingredients/"+meals.getStrIngredient4()+ ".png";
        String img5="https://www.themealdb.com/images/ingredients/"+meals.getStrIngredient5()+ ".png";
        String img6="https://www.themealdb.com/images/ingredients/"+meals.getStrIngredient6()+ ".png";
        String img7="https://www.themealdb.com/images/ingredients/"+meals.getStrIngredient7()+ ".png";

        ingredientItems.add(new IngredientItem( meals.getStrIngredient1(),  img1) );
        ingredientItems.add(new IngredientItem( meals.getStrIngredient2(),  img2) );
        ingredientItems.add(new IngredientItem( meals.getStrIngredient3(),  img3) );
        ingredientItems.add(new IngredientItem( meals.getStrIngredient4(),  img4) );
        ingredientItems.add(new IngredientItem( meals.getStrIngredient5(),  img5) );
        ingredientItems.add(new IngredientItem( meals.getStrIngredient6(),  img6) );
        ingredientItems.add(new IngredientItem( meals.getStrIngredient7(),  img7) );
        adapter.setIngredient(ingredientItems);
        adapter.notifyDataSetChanged();





        plan.setIdMeal(meals.getIdMeal());
        plan.setStrMeal(meals.getStrMeal()) ;
        plan.setStrMealThumb(meals.getStrMealThumb());
        plan.setStrArea(meals.getStrArea());
        plan.setStrYoutube(meals.getStrYoutube());
        plan.setStrInstructions(meals.getStrInstructions());
       plan.setStrIngredient1(meals.getStrIngredient1());
        plan.setStrIngredient2(meals.getStrIngredient2());
       plan.setStrIngredient3( meals.getStrIngredient3());
      plan.setStrIngredient4(meals.getStrIngredient4());
      plan.setStrIngredient5(meals.getStrIngredient5());
       plan.setStrIngredient6(meals.getStrIngredient6());
         plan.setStrIngredient7(meals.getStrIngredient7());





    }

    @Override
    public void addData(Meals meals) {
        presenterImp.addToFav(meals);
        Toast.makeText(this,"Data added successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(Meals meal) {
        addData(meal);
    }



}



