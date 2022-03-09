package Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.handyapp_v2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WriteReviewActivity extends AppCompatActivity {

    TextView rateCount;
    RatingBar ratingBar;
    float rateValue;
    Button submit;
    EditText Review;
    private String proID = "",uid;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        proID = getIntent().getStringExtra("proid");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        rateCount = findViewById(R.id.rate_count);
        ratingBar = findViewById(R.id.ratingbar);
        submit = findViewById(R.id.submit_review);
        Review = findViewById(R.id.editText);
        uid = firebaseAuth.getCurrentUser().getUid();

        rateValue = ratingBar.getRating();
        rateCount.setText(""+rateValue);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();
                rateCount.setText(""+rateValue);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = Review.getText().toString();
                String count = rateCount.getText().toString();
                UoloadView(proID,review,count,uid);
            }
        });
    }

    private void UoloadView(String proID, String review, String count, String uid) {
        Map<String,Object> map = new HashMap();
        map.put("review",review);
        map.put("keyid",proID);
        map.put("rate",count);
        map.put("uid",uid);

        firebaseFirestore.collection("data").document(proID).collection("Reviews")
                .document(uid).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(WriteReviewActivity.this, "Review Uploaded", Toast.LENGTH_SHORT).show();
                finish();
//                Intent intent  = new Intent(WriteReviewActivity.this,SellerDashboardActivity.class);
//                startActivity(intent);
            }
        });

    }
}