package com.example.handyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }



    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId())
            {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_inbox:
                    selectedFragment = new InboxFragment();
                    break;
                case R.id.nav_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };



    public void whichTextClicked(View view)
    {
        Fragment childFragment = null;
        switch (view.getId())
        {
            case R.id.carRental:
                //Toast.makeText(getApplicationContext(), "Car Rental is clicked", Toast.LENGTH_SHORT).show();
                childFragment = new CarRental();
                break;


            case R.id.housePainting:
                //Toast.makeText(getApplicationContext(), "House painting is clicked", Toast.LENGTH_SHORT).show();
                childFragment = new housePainting();
                break;


            case R.id.foodService:
                childFragment = new FoodService();
                break;


            case R.id.cleaningService:
                childFragment = new CleaningService();
                break;


            case R.id.photography:
                childFragment = new Photography();
                break;


            case R.id.plumbing:
                childFragment = new Plumbing();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,childFragment).commit();
    }

}