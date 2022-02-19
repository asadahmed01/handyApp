package com.example.handyapp_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

import Activities.AllCategoriesActivity;
import Activities.ProfileActivity;
import Adapters.AllCategoryAdapter;
import Data.CategoryData;
import Fragments.SellerDetailsFragment;

public class SearchActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    String[] tvModeling = {"Sketch","Modeling","UI/UX","Camera"};
    EditText editText;

    private RecyclerView recyclerView;


    /*-----Choose Category RecyclerView Data Here-------*/
    Integer[] imgBg = {R.drawable.category_img1, R.drawable.category_img2,
            R.drawable.category_img3, R.drawable.category_img4,
            R.drawable.category_img5, R.drawable.category_img6,R.drawable.category_img1};

    String[] tvTitle = {"Plumbing","Cleaning","Photography","Car\nRepair",
            "Painting","House\nWork","Web\nDevlopment","Photo &\nVideo","MO"};

    private RecyclerView rvCategory;
    private AllCategoryAdapter allCategoryAdapter;
    private ArrayList<CategoryData> chooseCategoryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.searchBar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        /*-----Choose Category RecyclerView Code Here-------*/
        rvCategory = findViewById(R.id.rvCategories);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(SearchActivity.this,2);
        rvCategory.setLayoutManager(layoutManager1);
        chooseCategoryModels = new ArrayList<>();

        for (int i = 0; i < imgBg.length; i++) {
            CategoryData model = new CategoryData(imgBg[i], tvTitle[i]);
            chooseCategoryModels.add(model);
        }
        allCategoryAdapter = new AllCategoryAdapter(SearchActivity.this, chooseCategoryModels);
        rvCategory.setAdapter(allCategoryAdapter);


        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener((item) ->{

            Intent intent;
            switch (item.getItemId())
            {
                case R.id.nav_home:
                    intent = new Intent(getApplicationContext(), AllCategoriesActivity.class);
                    startActivity(intent);

                    break;
                case R.id.nav_search:

                    break;
                case R.id.nav_profile:
                    intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);


                    break;


            }

            return true;
        } );



    }



    private void filter(String text)
    {
        ArrayList<CategoryData> filterList = new ArrayList<>();

        for(CategoryData item: chooseCategoryModels)
        {
            if(item.getTvCategory().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }

        }
        allCategoryAdapter.filterList(filterList);
    }
}