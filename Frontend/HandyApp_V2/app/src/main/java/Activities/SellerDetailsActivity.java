package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.handyapp_v2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SellerDetailsActivity extends AppCompatActivity {

    TextView name, skills, price, description, category, address, rate;
    FloatingActionButton floatingActionButton;
    private String proID = "";
    String sname;
    String suid;
    Button giveReview, makePayment;
    RelativeLayout loading;
    RecyclerView recyclerView;


    FirebaseFirestore firestore;
    String sprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_details);

        proID = getIntent().getStringExtra("pid");
        name = findViewById(R.id.seller_name);
        skills = findViewById(R.id.seller_skills);
        price = findViewById(R.id.sellerprice);
        description = findViewById(R.id.seller_description);
        category = findViewById(R.id.seller_category);
        address = findViewById(R.id.seller_address);
        loading = findViewById(R.id.loading);
        makePayment = findViewById(R.id.make_payment);
        loading.setVisibility(View.VISIBLE);
        giveReview = findViewById(R.id.give_review);
        recyclerView = findViewById(R.id.sellerRecyclerList);
        rate = findViewById(R.id.rate);
        firestore = FirebaseFirestore.getInstance();
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerDetailsActivity.this, MessagesActivity.class);
                intent.putExtra("userid", suid);
                startActivity(intent);
            }
        });

    }

    private void getdetails(String proID) {
        FirebaseFirestore proRef = FirebaseFirestore.getInstance();

        proRef.collection("data").document(proID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot dataSnapshot = task.getResult();
                if (dataSnapshot.exists()) {
                    FirebaseUser firebaseUser;
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String id = firebaseUser.getUid();

                    final String sskills = dataSnapshot.get("skills").toString();
                    sprice = dataSnapshot.get("price").toString();
                    final String pcategory = dataSnapshot.get("category").toString();
                    final String sdescription = dataSnapshot.get("description").toString();
                    final String uid = dataSnapshot.get("uid").toString();

                    if (!id.equals(uid)){
                        floatingActionButton.setVisibility(View.VISIBLE);
                        giveReview.setVisibility(View.VISIBLE);

                    }
                    else {
                        floatingActionButton.setVisibility(View.GONE);
                        giveReview.setVisibility(View.GONE);


                    }
                    try {

                        final String rate = dataSnapshot.get("rate").toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        final String rate ="0";


                    }

                    suid = dataSnapshot.get("uid").toString();
                    rate.setText(rate.getText().toString());
                    skills.setText("Skills: "+sskills);
                    price.setText("Price: "+sprice+"$/hr");
                    category.setText(pcategory);
                    description.setText("Description: "+sdescription);

                    //payment
                }
            }
        });



    }
}