package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.handyapp_v2.R;

public class PaymentActivity extends AppCompatActivity {

    CardView addPayment,creditcard;
    TextView amount, creditCardText, title;
    int REQUEST_CODE = 0077;
    Button checkout;
    String name, email, price;
    String sendermail;

    public String stripe_token = "pk_test_51KaeHVH2uQT2W8eXtF7YgUv6J1T617qWJ73hbQ3HxMpldJDD1H7CGvMplRiNhjq9UsY6B0NjlZuEJ40m8CL7vIGk00cWeYkRyz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        email = getIntent().getStringExtra("email");
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        sendermail = getIntent().getStringExtra("address");

        addPayment = (CardView) findViewById(R.id.addPayment);
        creditCardText = (TextView) findViewById(R.id.credit_card_text);
        title = (TextView) findViewById(R.id.credit_card);
        checkout = (Button) findViewById(R.id.checkout);

        title.setText("Sending "+price+" to "+name+" please add payment method to condirm;");

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now we need to send an email to the user.
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddPayment.class);
                startActivityForResult(intent,REQUEST_CODE);//depricated method until we find the right alternative.
            }
        });

    }
}