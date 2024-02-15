package com.example.foodplanner.Item.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Item.presenter.ItemPresenterImp;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSourceImp;
import com.example.foodplanner.model.IngredientItem;
import com.example.foodplanner.model.Meals;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.RemoteDataSourceAPI;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemActivity extends AppCompatActivity implements ItemView{
    ItemPresenterImp presenterImp;
    String mealName;
    IngredientAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    TextView itemName,itemPageMealSteps;
    CircleImageView image;
    WebView webView;

    private static final String TAG = "ItemActivity";

    String[] videoSplit;
    String videoId;
    YouTubePlayerView videoView ;
    List<IngredientItem> ingredientItems;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Bundle getData= getIntent().getExtras();
        if(getData != null){
            mealName=getData.getString("NAME_OF_MEAL");
        }

        itemName= findViewById(R.id.itemName);
        itemPageMealSteps=findViewById(R.id.itemPageMealSteps);
        image= findViewById(R.id.image);
        //webView= findViewById(R.id.videoView);
        videoView=findViewById(R.id.videoView);

        recyclerView= findViewById(R.id.ingredientRecyclerView);
        linearLayoutManager= new LinearLayoutManager(this);
        adapter= new IngredientAdapter(new ArrayList<>(),this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        presenterImp= new ItemPresenterImp(Repository.getInstance(RemoteDataSourceAPI.getInstance(this), MealLocalDataSourceImp.getInstance(this),ItemActivity.this),this);
        presenterImp.getByMealName(mealName);

        ingredientItems= new ArrayList<>();

        actionBar= getSupportActionBar ();
        actionBar.hide();

    }


    @Override
    public void getByMealName(List<Meals> list) {
        Meals meals= list.get(0);

        itemName.setText(meals.getStrMeal());
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

    }
}