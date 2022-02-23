package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import Adapters.AllCategoryAdapter;
import CategoriesApiCalls.ApiClient;
import CategoriesApiCalls.CategoriesResponse;
import Data.CategoryData;


import LoginApiCalls.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.handyapp_v2.R;
import com.example.handyapp_v2.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesActivity extends AppCompatActivity {
    //list of all categories
    List<CategoriesResponse> categories = new ArrayList<>();
    BottomNavigationView bottomNav;
    Integer[] imgBg = {R.drawable.category_img1, R.drawable.category_img2,
            R.drawable.category_img3, R.drawable.category_img4,
            R.drawable.category_img5, R.drawable.category_img6,
            R.drawable.category_img7, R.drawable.category_img8,R.drawable.category_img1};
    String[] tvTitle = {"Plumbing","Cleaning","Photography","Car\nRepair",
            "Painting","House\nWork","Web\nDevlopment","Photo &\nVideo","MO"};

    private RecyclerView recyclerView;
    private AllCategoryAdapter allCategoryAdapter;
    private ArrayList<CategoryData> categoryData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_categories);

        ArrayList<CategoriesResponse> myList = (ArrayList<CategoriesResponse>) getIntent().getSerializableExtra("cat");
        Log.d("TAG", "response 33: " + myList.get(0).getCategoryName() );
        Toast.makeText( AllCategoriesActivity.this, "SIZE "+ myList.size() , Toast.LENGTH_LONG).show();
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener((item) ->{

            Intent intent;
            switch (item.getItemId())
            {
                case R.id.nav_home:

                    break;
                case R.id.nav_search:
                    intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);


                    break;
                case R.id.nav_profile:
                    intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);

                    break;


            }

            return true;
        } );

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        /*-----Choose Category RecyclerView Code Here-------*/



        recyclerView = findViewById(R.id.rvAllCategory);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(AllCategoriesActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        categoryData = new ArrayList<>();

        Toast.makeText( AllCategoriesActivity.this, "SIZE "+ myList.size() , Toast.LENGTH_LONG).show();
        for (int i = 0; i < categories.size(); i++) {
            CategoryData model = new CategoryData(imgBg[i], categories.get(i).getCategoryName());
            categoryData.add(model);
        }
        allCategoryAdapter = new AllCategoryAdapter(AllCategoriesActivity.this, categoryData);
        recyclerView.setAdapter(allCategoryAdapter);


    }


}