package Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.handyapp_v2.R;
import com.stripe.android.view.CardMultilineWidget;

public class AddPayment extends AppCompatActivity {

    CardMultilineWidget cardMultilineWidget;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        setContentView(R.layout.activity_add_payment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Payment");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
}