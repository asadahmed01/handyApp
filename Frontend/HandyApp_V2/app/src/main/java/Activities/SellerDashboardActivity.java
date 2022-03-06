package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.handyapp_v2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SellerDashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private FrameLayout frameLayout;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        frameLayout = findViewById(R.id.framlayout);
        tabLayout = findViewById(R.id.tablayout);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        //checkUsername();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabLayout.getTabAt(2).getIcon().setTintList(ColorStateList.valueOf(Color.parseColor("#BDBDBD")));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setFragment(tab.getPosition());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tab.getIcon().setTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_d86041)));
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tab.getIcon().setTintList(ColorStateList.valueOf(Color.parseColor("#BDBDBD")));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabLayout.getTabAt(0).getIcon().setTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_light_ffe0de)));
        }
        setFragment(0);


    }

    public void setFragment(int position) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(frameLayout.getId(), fragmentList.get(position));
        fragmentTransaction.commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}