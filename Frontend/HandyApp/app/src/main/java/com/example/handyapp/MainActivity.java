package com.example.handyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Stack <Fragment> stack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stack = new Stack<Fragment>();
        setContentView(R.layout.activity_main);






        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();



    }

    public void pushFragment(Fragment fragment, boolean add) {
        FragmentTransaction transation = getSupportFragmentManager()
                .beginTransaction();
        if (add)
            stack.push(fragment);
        transation.replace(R.id.fragment_container, fragment);
        transation.commit();
    }

    public void popFragment() {
        if (!stack.isEmpty()) {
            Fragment fragment = stack.elementAt(stack.size() - 2);
            stack.pop();
            pushFragment(fragment, false);
        } else
            super.onBackPressed();
        //drawerToggler.setDrawerIndicatorEnabled(stack.size() == 1);
    }

    public void clearBackStack() {
        stack.clear();
    }


    public void makingFragment(Fragment fragment)
    {

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container,fragment);
        t.setReorderingAllowed(true);
        t.addToBackStack("Fragmito");
        t.commit();

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
        //Fragment childFragment = null;

        switch (view.getId())
        {
            case R.id.carRental:
                //Toast.makeText(getApplicationContext(), "Car Rental is clicked", Toast.LENGTH_SHORT).show();
                CarRental c = new CarRental();
                makingFragment(c);
                break;


            case R.id.housePainting:
                //Toast.makeText(getApplicationContext(), "House painting is clicked", Toast.LENGTH_SHORT).show();
                housePainting h = new housePainting();
                makingFragment(h);
                break;


            case R.id.foodService:
                FoodService f = new FoodService();
                makingFragment(f);
                break;


            case R.id.cleaningService:
                CleaningService cs = new CleaningService();
                makingFragment(cs);
                break;


            case R.id.photography:
                Photography ph = new Photography();
                makingFragment(ph);
                break;


            case R.id.plumbing:
                Plumbing pl = new Plumbing();
                makingFragment(pl);
                break;
        }

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,childFragment).commit();
    }

}