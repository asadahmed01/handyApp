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


    }

    
}