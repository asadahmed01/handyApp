package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import Adapters.AllCategoryAdapter;
import Data.CategoryData;
import com.example.handyapp_v2.R;
import com.example.handyapp_v2.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AllCategoriesActivity extends AppCompatActivity {

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

        for (int i = 0; i < imgBg.length; i++) {
            CategoryData model = new CategoryData(imgBg[i], tvTitle[i]);
            categoryData.add(model);
        }
        allCategoryAdapter = new AllCategoryAdapter(AllCategoriesActivity.this, categoryData);
        recyclerView.setAdapter(allCategoryAdapter);

    }
}