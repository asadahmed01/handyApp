package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.handyapp_v2.R;
import com.example.handyapp_v2.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {


    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




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

    }
}