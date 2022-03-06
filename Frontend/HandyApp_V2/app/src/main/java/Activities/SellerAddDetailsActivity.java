package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.handyapp_v2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SellerAddDetailsActivity extends AppCompatActivity {

    EditText price,skills,description;
    String Price, uid;
    String category;
    Button upload;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String currentDate,currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_details);

        //find all elements by ID
        price = findViewById(R.id.price);
        skills = findViewById(R.id.skills);
        description = findViewById(R.id.description);
        Price = price.getText().toString();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        upload = findViewById(R.id.update);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDate();

            }
        });
    }

    private void uploadDate() {
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String instantkey = currentDate+" "+currentTime;
        Map<String,Object> map = new HashMap();
        map.put("category",category);
        map.put("price",price.getText().toString());
        map.put("skills",skills.getText().toString());
        map.put("description",description.getText().toString());
        map.put("uid",uid);
        map.put("kay",instantkey);
        map.put("date",currentDate);
        map.put("time",currentTime);

        firebaseFirestore.collection("data").document(instantkey)
                .set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SellerAddDetailsActivity.this, "Job has been posted", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(SellerAddDetailsActivity.this,SellerDashboardActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



    }
}